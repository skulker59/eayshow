package com.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "SHOW")
public class Show {
	@Id @GeneratedValue
	private int id;
	
	@Column(name = "name", unique=true, nullable=false)
	private String name;
	
	@Column(name = "dbName", unique=true, nullable=false)
	private String dbName;
	
	@Column(length= 1000 , name = "overview")
	private String overview;
	
	@Column(length= 200 , name = "urlPoster")
	private String urlPoster;
	
	@Column(name = "isAnime", nullable=false)
	private boolean isAnime;
	
	@Column(name = "path", nullable=false)
	private String path;
	
	@Column(name = "idTVDB", nullable=false)
	private String idTVDB;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "show", orphanRemoval = true)
	private List<Episode> listEpisodes = new ArrayList<>();
	
	public void addEpisode(Episode ep) {
		if(!listEpisodes.contains(ep)) {
			listEpisodes.add(ep);
		}
	}
	public List<Episode> getListEpisodes() {
		return listEpisodes;
	}
	public void setListEpisodes(List<Episode> listEpisodes) {
		this.listEpisodes = listEpisodes;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Show s = (Show) obj;
		return new EqualsBuilder().append(this.getDbName(), s.getDbName())
				.append(this.getName(), s.getName())
				.append(this.getOverview(), s.getOverview())
				.append(this.getUrlPoster(), s.getUrlPoster())
				.append(this.isAnime(), s.isAnime())
				.append(this.getIdTVDB(), s.getIdTVDB()).isEquals();
	}
	
	@Override
	public int hashCode() {
	return new HashCodeBuilder(17, 31).append(this.getName())
			.append(this.getOverview())
			.append(this.getUrlPoster())
			.append(this.isAnime())
			.append(this.getIdTVDB())
			.toHashCode();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getUrlPoster() {
		return urlPoster;
	}
	public void setUrlPoster(String urlPoster) {
		this.urlPoster = urlPoster;
	}
	public boolean isAnime() {
		return isAnime;
	}
	public void setAnime(boolean isAnime) {
		this.isAnime = isAnime;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIdTVDB() {
		return idTVDB;
	}
	public void setIdTVDB(String idTVDB) {
		this.idTVDB = idTVDB;
	}
}
