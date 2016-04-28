package com.businessLogic;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.database.Dao;
import com.omertron.thetvdbapi.TheTVDBApi;
import com.pojos.Show;

@javax.ws.rs.Path("/datasystem")
public class FileSystem {
	
	private static final String TVDB_API_KEY = "12927F74E735767F";
	private TheTVDBApi tvDB = new TheTVDBApi(TVDB_API_KEY);
	
	private Dao database;
	
	public FileSystem() {
		database = new Dao();
	}
	
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
					
					// Check si la série est présente en base.
					Show s = database.getShowByName(showName);
					
					if(s == null) {
						listShow.setListSeries(tvDB.searchSeries(showName, "en"));
						scan.getListShows().put(showName, listShow);
					}
				}
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
	@javax.ws.rs.Path("/testpost")
	@Produces(MediaType.APPLICATION_JSON)
	public Response db2(){
		Response res = Response.ok("{msg : erreur quelque part !!}", MediaType.APPLICATION_JSON).build();
		return res;
	}
	
//	@GET
//	@javax.ws.rs.Path("/tvdb")
//	@Produces(MediaType.TEXT_PLAIN)
//	public void tvdb(){
////		TheTVDBApi tvDB = new TheTVDBApi(TVDB_API_KEY);
//		try {
//			List<Series> results = tvDB.searchSeries("Hannibal", "en");
//			int toto = 1;
////			Series series = tvDB.getSeries("259063", "fr");
////			return series.toString();
//		} catch (TvDbException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
////			return null;
//		}
//	}
}
