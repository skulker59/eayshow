package com.database;

import java.util.Collection;

import com.pojos.Episode;
import com.pojos.Show;

public class Dao {
	
	private DatabaseEM dbem;
	
	public Dao() {
		dbem = new DatabaseEM();
	}
	
	public void callDBEM() {
//		Show s = new Show();
//		s.setName("serie1");
//		s.setOverview("desc");
//		s.setAnime(true);
//		
//		Episode ep = new Episode();
//		ep.setName("episode1");
//		ep.setEpSeason(1);
//		ep.setEpNumber(1);
//		ep.setEpAbsolute(-1);
//		ep.setDescription("descEp");
//		ep.setAiredDate("2016-05-15");
//		ep.setShow(s);
//		
//		Episode ep2 = new Episode();
//		ep2.setName("episode2");
//		ep2.setEpSeason(1);
//		ep2.setEpNumber(2);
//		ep2.setEpAbsolute(-1);
//		ep2.setDescription("descEp");
//		ep2.setAiredDate("2016-05-30");
//		ep2.setShow(s);
//		
//		s.addEpisode(ep);
//		s.addEpisode(ep2);
//		
//		int idShow = dbem.addShow(s);
////		dbem.listShows();
////		dbem.addShow("serie2", "2016", "desc", "horror", true);
//		dbem.listAllShows();
//		
////		dbem.deleteAllEpisodeFromShow(idShow);
////		dbem.listAllShows();
//		dbem.shutdown();
	}
	
    /**
     * Ajoute une série.
     * @param show Série à ajouter.
     * @return L'id de la série ajoutée.
     */
	public int addShow(Show show) {
		return dbem.addShow(show);
	}
	
	public Collection<Show> getAllShows()
	{
		return dbem.listAllShows();
	}
	
	public Show getShowById(int id)
	{
		return dbem.getShowById(id);
	}
	
	public Show getShowWithEpisodesById(int id)
	{
		return dbem.getShowWithEpisodeById(id);
	}
	
	public Show getShowByName(String dbName) {
		return dbem.getShowByName(dbName);
	}
	
    /**
     * Supprime une serie.
     * @param id Id de la serie à supprimer.
     * @return L'id de la série supprimée.
     */
    public boolean deleteShow(int id) {
    	return dbem.deleteShow(id);
    }
    
    /**
     * Ajoute un épisode lié à une série.
     * @param ep Episode à ajouter.
     * @return L'id de l'épisode ajouté.
     */
    public int addEpisode(Episode ep) {
    	return dbem.addEpisode(ep);
    }
}
