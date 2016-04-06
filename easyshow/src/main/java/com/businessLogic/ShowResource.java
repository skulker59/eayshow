package com.businessLogic;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.database.Dao;
import com.omertron.thetvdbapi.TheTVDBApi;
import com.omertron.thetvdbapi.TvDbException;
import com.omertron.thetvdbapi.model.Episode;
import com.pojos.Show;

@javax.ws.rs.Path("/shows")
public class ShowResource {

	private static final String TVDB_API_KEY = "12927F74E735767F";
	private TheTVDBApi tvDB = new TheTVDBApi(TVDB_API_KEY);
	
	public ShowResource(){}
	
	@POST
	@javax.ws.rs.Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addShows(List<ScannedShow> body) {
//		Series series = tvDB.getSeries("73739", "en");

		
		// Chemin vers le dossier contenant toutes les séries.
		Path DDPath = Paths.get("D:/SeriesTest");
		
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(DDPath)) {
//			stream = Files.newDirectoryStream(DDPath);
			Iterator<Path> iterator = stream.iterator();
			int nbBodyShows = body.size();
			int nbTreatedShows = 0;
			
			// Itérations sur les dossiers séries.
			while (iterator.hasNext() || nbTreatedShows <= nbBodyShows) {
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
							newShow.setName(bodyShow.getProperties().getSeriesName());
							newShow.setOverview(bodyShow.getProperties().getOverview());
							newShow.setAnime(false);
							newShow.setPath(pathShow.toAbsolutePath().toString());
							newShow.setIdTVDB(bodyShow.getProperties().getId());
							
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
							
							Dao dao = new Dao();
							dao.addShow(newShow);
						}
					}
				}
			}
		} catch(IOException io) {
			String erreur = "";
		} catch (TvDbException e) {
			
		}
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
