package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private String DBPath = "";
    private Connection connection = null;
    private Statement statement = null;
 
    public Database(String dBPath) {
        DBPath = dBPath;
    }
 
    /**
     * Ouvre de la connexion à la base de données.
     */
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connexion (not found)");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connexion (sql exception)");
        }
    }
 
    /**
     * Ferme de la connexion à la base de données.
     */
    public void close() {
        try {
        	if(statement != null)
        		statement.close();
        	if(connection != null)
        		connection.close();
            System.out.println("Connexion db fermée");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Ajoute une série.
     * @param name Nom de la serie.0
     * @param creationYear Annee de creation.
     * @param description Description de la serie.
     * @param genre Genre (comedie, horreur, ...).
     * @param isAnime Indication pour la gestion des épisodes.
     * @return L'id de la série ajoutée.
     */
    public int addShow(String name, String creationYear, String description, String genre, boolean isAnime) {
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO SHOW VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, creationYear);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, genre);
            preparedStatement.setString(6, null);
            
            int flagAnime = (isAnime == true ? 1 : 0);
            preparedStatement.setInt(7, flagAnime);
            int idRow = preparedStatement.executeUpdate();
            System.out.println("LIGNE AJOUTE : " + idRow);
            return idRow;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public void getShow(int id) {
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM SHOW WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("LIGNE SELECTIONNEE : " + rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Supprime une serie.
     * @param id Id de la serie à supprimer.
     * @return L'id de la série supprimée.
     */
    public int deleteShow(int id) {
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM SHOW WHERE id = ?");
            preparedStatement.setInt(1, id);
            int idRow = preparedStatement.executeUpdate();
            System.out.println("LIGNE SUPPRIME : " + idRow);
            return idRow;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * Ajoute un épisode lié à une série.
     * @param name Nom de l'épisode.
     * @param numSeason Numéro de saison.
     * @param numEpisode Numéro d'épisode.
     * @param description Description de l'épisode.
     * @param airedDate Date de sortie.
     * @param idShow Id de la série liée à l'épisode.
     * @param numEpAbsolute Numéro absolu de l'épisode.
     * @return L'id de l'épisode ajouté.
     */
    public int addEpisode(String name, int numSeason, int numEpisode, String description, String airedDate, int idShow, int numEpAbsolute) {
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO EPISODE VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, numSeason);
            preparedStatement.setInt(4, numEpisode);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, airedDate);
            preparedStatement.setInt(7, idShow);
            preparedStatement.setInt(8, numEpAbsolute);
    
            int idRow = preparedStatement.executeUpdate();
            System.out.println("LIGNE AJOUTE : " + idRow);
            return idRow;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * Supprime un épisode lié à une série.
     * @param id Id de l'épisode à supprimer.
     * @return L'id de l'épisode supprimé.
     */
    public int deleteEpisode(int id) {
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM EPISODE WHERE id = ?");
            preparedStatement.setInt(1, id);
            int idRow = preparedStatement.executeUpdate();
            System.out.println("LIGNE SUPPRIME : " + idRow);
            return idRow;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * Supprime tous les épisodes liés à une série.
     * @param idShow Id de la série.
     * @return true si l'opération s'est bien passé sinon false.
     */
    public boolean deleteAllEpisodesFromShow(int idShow) {
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM EPISODE WHERE idShow = ?");
            preparedStatement.setInt(1, idShow);
            preparedStatement.executeUpdate();
            System.out.println("EPISODES SUPPRIMES");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
