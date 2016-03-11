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
	
	@Column(name = "creationYear")
	private String creationYear;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "isAnime", nullable=false)
	private boolean isAnime;
	
	@Column(name = "path", nullable=false)
	private String path;
	
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
		return new EqualsBuilder().append(this.getName(), s.getName())
				.append(this.getCreationYear(), s.getCreationYear())
				.append(this.getDescription(), s.getDescription())
				.append(this.getGenre(), s.getGenre())
				.append(this.isAnime(), s.isAnime()).isEquals();
	}
	
	@Override
	public int hashCode() {
	return new HashCodeBuilder(17, 31).append(this.getName())
			.append(this.getCreationYear())
			.append(this.getDescription())
			.append(this.getGenre())
			.append(this.isAnime())
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
	public String getCreationYear() {
		return creationYear;
	}
	public void setCreationYear(String creationYear) {
		this.creationYear = creationYear;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
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
}
