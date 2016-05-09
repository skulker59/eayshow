package com.businessLogic;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.database.Dao;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.omertron.thetvdbapi.TheTVDBApi;
import com.omertron.thetvdbapi.TvDbException;
import com.omertron.thetvdbapi.model.Episode;
import com.pojos.Show;

@javax.ws.rs.Path("/shows")
public class ShowResource {

	private static final String TVDB_API_KEY = "12927F74E735767F";
	private TheTVDBApi tvDB = new TheTVDBApi(TVDB_API_KEY);
	
	private Dao database;
	
	public ShowResource(){
		database = new Dao();
	}
	
	@POST
	@javax.ws.rs.Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addShows(List<ScannedShow> body) {
//		Series series = tvDB.getSeries("73739", "en");
		
		// Chemin vers le dossier contenant toutes les séries.
		Path DDPath = Paths.get("D:/SeriesTest");
		
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(DDPath)) {

			Iterator<Path> iterator = stream.iterator();
			int nbBodyShows = 0;
			for (ScannedShow show : body) {
				if(show.getProperties() != null) {
					nbBodyShows++;
				}
			}
			int nbTreatedShows = 0;
			
			// Itérations sur les dossiers séries.
			while (iterator.hasNext() && nbTreatedShows < nbBodyShows) {
				Path pathShow = iterator.next();
				
				// Test si le fichier est bien un dossier.
				if(pathShow.toFile().isDirectory()) {
					String fileName = pathShow.getFileName().toString();
					
					// Itérations sur les shows à ajouter.
					Iterator<ScannedShow> iteratorShows = body.iterator();
					boolean showFound = false;
					while(iteratorShows.hasNext() && showFound == false) {
						ScannedShow bodyShow = iteratorShows.next();
						if(fileName.equals(bodyShow.getName())){
							showFound = true;
							
							// Création du nouveau show.
							Show newShow = new Show();
							newShow.setDbName(bodyShow.getName());
							newShow.setName(bodyShow.getProperties().getSeriesName());
							newShow.setOverview(bodyShow.getProperties().getOverview());
							newShow.setAnime(false);
							newShow.setPath(pathShow.toAbsolutePath().toString());
							newShow.setIdTVDB(bodyShow.getProperties().getId());
							
							// Récupération du poster.
							newShow.setUrlPoster(tvDB.getBanners(newShow.getIdTVDB()).getPosterList().get(0).getUrl());
							
							// Récupération et ajout des épisodes de la série.
							List<Episode> episodes = tvDB.getAllEpisodes(newShow.getIdTVDB(), "en");
							FileVisitorImpl FV = new FileVisitorImpl();
							Files.walkFileTree(pathShow, FV);
							
							// Création d'une map afin de vérifier rapidement la présence ou non d'épisodes.
//							Map<EpisodeId, Episode> episodesMap = episodes.stream().filter(e -> e.getSeasonNumber() > 0).collect(Collectors.toMap(e -> new EpisodeId(e.getSeasonNumber(), e.getEpisodeNumber()), e -> e));
							
							for(Episode episode : episodes)
							{
								com.pojos.Episode epDatabase = new com.pojos.Episode();
								epDatabase.setName(episode.getEpisodeName());
								epDatabase.setEpSeason(episode.getSeasonNumber());
								epDatabase.setEpNumber(episode.getEpisodeNumber());
								epDatabase.setEpAbsolute(-1);
								epDatabase.setDescription(episode.getOverview());
								epDatabase.setAiredDate(episode.getFirstAired());
								
								EpisodeId epId = new EpisodeId(episode.getSeasonNumber(), episode.getEpisodeNumber());
								boolean presence = FV.getEpisodesSet().contains(epId);
								epDatabase.setStatus((presence == true ? "FOUND" : "NOT_FOUND"));
								
								// Association de l'épisode à la série.
								newShow.addEpisode(epDatabase);
							}
							
							database.addShow(newShow);
							nbTreatedShows++;
						}
					}
				}
			}
		} catch(IOException io) {
			io.printStackTrace();
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		} catch (TvDbException e) {
			e.printStackTrace();
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		} catch(Exception ex) {
			ex.printStackTrace();
			return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
		
		return Response.ok("TEST MSG", MediaType.TEXT_PLAIN).build();
	}
	
	@GET
	@javax.ws.rs.Path("/get/all")
	@Produces(MediaType.APPLICATION_JSON)
	public HomeShows getShows() {
		HomeShows HShows = new HomeShows();
		HShows.setListShows(new Dao().getAllShows());
		return HShows;
	}
	
	static class EpisodeId {
		int season;
		int episode;
		
		public EpisodeId(int season, int episode) {
			this.season = season;
			this.episode = episode;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EpisodeId s = (EpisodeId) obj;
			return new EqualsBuilder().append(this.episode, s.episode)
					.append(this.season, s.season).isEquals();
		}
		
		@Override
		public int hashCode() {
		return new HashCodeBuilder(17, 31).append(this.episode)
				.append(this.season)
				.toHashCode();
		}
		
		@Override
		public String toString() {
			return "S" + season + "E" + episode;
		}
	}
}
