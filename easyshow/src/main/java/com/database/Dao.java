package com.database;

import com.pojos.Episode;
import com.pojos.Show;

public class Dao {
	
	private DatabaseEM dbem;
	
	public Dao() {
		dbem = new DatabaseEM();
	}
	
	public void callDBEM() {
		Show s = new Show();
		s.setName("serie1");
		s.setCreationYear("2016");
		s.setDescription("desc");
		s.setGenre("horror");
		s.setAnime(true);
		
		Episode ep = new Episode();
		ep.setName("episode1");
		ep.setEpSeason(1);
		ep.setEpNumber(1);
		ep.setEpAbsolute(-1);
		ep.setDescription("descEp");
		ep.setAiredDate("2016-05-15");
		ep.setShow(s);
		
		Episode ep2 = new Episode();
		ep2.setName("episode2");
		ep2.setEpSeason(1);
		ep2.setEpNumber(2);
		ep2.setEpAbsolute(-1);
		ep2.setDescription("descEp");
		ep2.setAiredDate("2016-05-30");
		ep2.setShow(s);
		
		s.addEpisode(ep);
		s.addEpisode(ep2);
		
		int idShow = dbem.addShow(s);
//		dbem.listShows();
//		dbem.addShow("serie2", "2016", "desc", "horror", true);
		dbem.listAllShows();
		
//		dbem.deleteAllEpisodeFromShow(idShow);
//		dbem.listAllShows();
		dbem.shutdown();
	}
	
    /**
     * Ajoute une série.
     * @param name Nom de la serie
     * @param creationYear Annee de creation
     * @param description Description de la serie
     * @param genre Genre (comedie, horreur, ...)
     * @param isAnime Indication pour la gestion des épisodes.
     * @return L'id de la série ajoutée.
     */
	public int addShow(String name, String creationYear, String description, String genre, boolean isAnime) {
		Show show = new Show();
		show.setName(name);
		show.setCreationYear(creationYear);
		show.setDescription(description);
		show.setGenre(genre);
		show.setAnime(isAnime);
		return dbem.addShow(show);
	}
	
	public void getShow(int id)
	{
		dbem.getShow(id);
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
     * @param name Nom de l'épisode.
     * @param numSeason Numéro de saison.
     * @param numEpisode Numéro d'épisode.
     * @param description Description de l'épisode.
     * @param airedDate Date de sortie.
     * @param idShow Id de la série liée à l'épisode.
     * @return L'id de l'épisode ajouté.
     */
    public int addEpisode(String name, int numSeason, int numEpisode, String description, String airedDate, int idShow, int numEpAbsolute) {
		Episode ep = new Episode();
		ep.setName(name);
		ep.setEpSeason(numSeason);
		ep.setEpNumber(numEpisode);
		ep.setEpAbsolute(numEpAbsolute);
		ep.setDescription(description);
		ep.setAiredDate(airedDate);
//		ep.setIdShow(idShow);
    	return dbem.addEpisode(ep);
    }
}
