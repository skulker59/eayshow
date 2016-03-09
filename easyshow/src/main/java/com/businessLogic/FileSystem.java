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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.database.Dao;
import com.omertron.thetvdbapi.TheTVDBApi;
import com.omertron.thetvdbapi.TvDbException;
import com.omertron.thetvdbapi.model.Episode;
import com.omertron.thetvdbapi.model.Series;


//import javax.ws.rs.Path;

@javax.ws.rs.Path("/folders")
public class FileSystem {
	
	private static final String TVDB_API_KEY = "12927F74E735767F";
	TheTVDBApi tvDB = new TheTVDBApi(TVDB_API_KEY);
	
	@GET
	@javax.ws.rs.Path("/scan")
	@Produces(MediaType.APPLICATION_JSON)
	public Scan listFolders() throws IOException {
		Scan scan = new Scan();
		
		// Chemin vers le dossier contenant toutes les séries.
		Path DDPath = Paths.get("D:/SeriesTest");
		DirectoryStream<Path> stream = Files.newDirectoryStream(DDPath);
		try {
			Iterator<Path> iterator = stream.iterator();
			
			// Itérations sur les dossiers séries.
			while (iterator.hasNext()) {
				Path pathShow = iterator.next();
				ItemListScan listShow = new ItemListScan();
				
				// Test si le fichier est bien un dossier.
				if(pathShow.toFile().isDirectory()) {
					String showName = pathShow.getFileName().toString();
					listShow.setListSeries(tvDB.searchSeries(showName, "en"));
//					scan.getListShows().add(listShow);
					scan.getListShows().put(showName, listShow);
				}
				
				// Itération pour les épisodes de chaque série.
//				FileVisitorImpl FV = new FileVisitorImpl();
//				Files.walkFileTree(pathShow, FV);
//				for(String fileName : FV.getListFilesNames())
//				{
//					response += "\t" + fileName + "\n";
//				}
			}
		} catch(Exception e) {
			scan.setListShows(null);
		} finally {
			stream.close();
		}
		
		return scan;
	}
	
	@GET
	@javax.ws.rs.Path("/db")
	@Produces(MediaType.TEXT_PLAIN)
	public void db(){
		Dao dao = new Dao();
		//dao.connect();
		//dao.addShow("TATA", "2000", "TATA le retour", "Comedy");
		//dao.deleteShow(1);
		//dao.addEpisode("EpTATA", 1, 1, "Pouaf", "21/05/89", 3);
		try {
			dao.callDBEM();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		
		//dao.close();
	}
	
	@GET
	@javax.ws.rs.Path("/tvdb")
	@Produces(MediaType.TEXT_PLAIN)
	public void tvdb(){
//		TheTVDBApi tvDB = new TheTVDBApi(TVDB_API_KEY);
		try {
			List<Series> results = tvDB.searchSeries("Hannibal", "en");
			int toto = 1;
//			Series series = tvDB.getSeries("259063", "fr");
//			return series.toString();
		} catch (TvDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			return null;
		}
	}
	
	@GET
	@javax.ws.rs.Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String tvdb2(){
		return "toto";
	}
	
	@POST
	@javax.ws.rs.Path("/angu/{param}")
	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
	public void testAngu(@PathParam("param") String param) throws TvDbException{
//		Series series = tvDB.getSeries("73739", "en");
		List<Episode> episodes = tvDB.getAllEpisodes("73739", "en");
	}
}
