package com.database;

import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.pojos.Episode;
import com.pojos.Show;

public class DatabaseEM {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public DatabaseEM() {
		emf = Persistence.createEntityManagerFactory("easyshow-PS");
		em = emf.createEntityManager();
	}
	
	public void shutdown() {
		em.close();
		emf.close();
	}
	
    /**
     * Ajoute une série.
     * @param show Série à ajouter.
     * @return L'id de la série ajoutée.
     */
	public int addShow(Show show){
		int idShow = -1;
		try {
		em.getTransaction().begin();
		em.persist(show);
	
		idShow = show.getId();
		
		em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			idShow = -1;
		}
		
		return idShow;
	}
	
	/**
	 * Cherche en base la liste de toutes les séries.
	 * @return Une liste contenant toutes les séries.
	 */
	public List<Show> listAllShows(){
		List<Show> listShows = em.createQuery("SELECT s FROM Show s", Show.class).getResultList();
		
		System.out.println("DEBUT LISTAGE");
		for(Show s : listShows) {
			System.out.println(s.getName());
			for(Episode ep : s.getListEpisodes()) {
				System.out.println(ep.getName());
			}
		}
		return listShows;
	}
	
	/**
	 * Cherche en base une série spécifique.
	 * @param id Id de la série à retrouver.
	 * @return Une série.
	 */
	public Show getShow(int id) {
		Show show = em.find(Show.class, id);
		return show;
	}
	
    /**
     * Supprime une serie.
     * @param id Id de la serie à supprimer.
     * @return Indication si la suppression a réussi.
     */
    public boolean deleteShow(int id) {
    	try {
    		  Show show = em.find(Show.class, id);
    		  
    		  em.getTransaction().begin();
    		  em.remove(show);
    		  em.getTransaction().commit();
    		  return true;
        } catch (Exception e) {
        	em.getTransaction().rollback();
            return false;
        }
    }
    
    /**
     * Ajoute un épisode lié à une série.
     * @param ep Episode à ajouter.
     * @return L'id de l'épisode ajouté.
     */
    public int addEpisode(Episode ep) {
    	int idEpisode = -1;
		
		try {
		em.getTransaction().begin();
		em.persist(ep);
	
		idEpisode = ep.getId();
		
		em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			idEpisode = -1;
		}
		
		return idEpisode;
    }
    
	/**
	 * Récupère les épisodes associés à une série.
	 * @param idShow Id de la série.
	 * @return La liste des épisodes de la série.
	 */
	public List<Episode> listAllEpisodesFromShow(int idShow){
		Show show = em.find(Show.class, idShow);
		List<Episode> listEpisodes = show.getListEpisodes();
		return listEpisodes;
	}
	
	public List<Episode> listAllEpisodesFromSeason(int idShow, int season){
		TypedQuery<Episode> query = em.createQuery(
		        "SELECT ep FROM Episode ep WHERE ep.idShow = :idShow "
		        + "AND ep.epSeason = :epSeason", Episode.class);
		query.setParameter("idShow", idShow);
		query.setParameter("epSeason", season);
		List<Episode> listEpisodes = query.getResultList();
		return listEpisodes;
	}
	
	/**
	 * Cherche en base un épisode spécifique.
	 * @param id Id de l'épisode à retrouver.
	 * @return Un épisode.
	 */
	public Episode getEpisode(int id) {
		Episode ep = em.find(Episode.class, id);
		return ep;
	}
	
    /**
     * Supprime un épisode lié à une série.
     * @param id Id de l'épisode à supprimer.
     * @return L'id de l'épisode supprimé.
     */
    public boolean deleteEpisode(int id) {
    	try {
    		Episode ep = em.find(Episode.class, id);
  		  
    		em.getTransaction().begin();
    		em.remove(ep);
    		em.getTransaction().commit();
            return true;
        } catch (Exception e) {
        	em.getTransaction().rollback();
            return false;
        }
    }
    
    public boolean deleteAllEpisodeFromSeason(int idShow, final int season) {
    	try {
    		Show s = getShow(idShow);
  		  
    		em.getTransaction().begin();

    		Predicate<Episode> predicateSeaon = new Predicate<Episode>() {
				
				@Override
				public boolean test(Episode t) {
					return t.getEpSeason() == season;
				}
			};
    		s.getListEpisodes().removeIf(predicateSeaon);
    		
    		em.getTransaction().commit();
            return true;
        } catch (Exception e) {
        	em.getTransaction().rollback();
            return false;
        }
    }
    
    public boolean deleteAllEpisodeFromShow(int idShow) {
    	try {
    		Show s = getShow(idShow);
  		  	
    		em.getTransaction().begin();
    		s.getListEpisodes().clear();
    		em.getTransaction().commit();
            return true;
        } catch (Exception e) {
        	em.getTransaction().rollback();
            return false;
        }
    }
}
