package com.pojos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "EPISODE")
public class Episode {
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "epSeason")
	private int epSeason;

	@Column(name = "epNumber")
	private int epNumber;

	@Column(name = "epAbsolute")
	private int epAbsolute;

	@Column(name = "description")
	private String description;

	@Column(name = "airedDate")
	private String airedDate;

	@Column(name = "status")
	private String status;
	
	@ManyToOne
	private Show show;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "episode", orphanRemoval = true)
	private List<Video> listVideos;

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public void addVideo(Video v) {
		if (!listVideos.contains(v)) {
			listVideos.add(v);
		}
	}

	public List<Video> getListVideos() {
		return listVideos;
	}

	public void setListVideos(List<Video> listVideos) {
		this.listVideos = listVideos;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Episode ep = (Episode) obj;
		return new EqualsBuilder().append(this.getName(), ep.getName())
				.append(this.getEpAbsolute(), ep.getEpAbsolute())
				.append(this.getEpSeason(), ep.getEpSeason())
				.append(this.getEpNumber(), ep.getEpNumber())
				.append(this.getDescription(), ep.getDescription())
				.append(this.getAiredDate(), ep.getAiredDate()).isEquals();
	}
	
	@Override
	public int hashCode() {
	return new HashCodeBuilder(17, 31).append(this.getName())
			.append(this.getEpAbsolute())
			.append(this.getEpSeason())
			.append(this.getEpNumber())
			.append(this.getDescription()).append(this.getAiredDate())
			.toHashCode();
	}
	
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEpSeason() {
		return epSeason;
	}

	public void setEpSeason(int epSeason) {
		this.epSeason = epSeason;
	}

	public int getEpNumber() {
		return epNumber;
	}

	public void setEpNumber(int epNumber) {
		this.epNumber = epNumber;
	}

	public int getEpAbsolute() {
		return epAbsolute;
	}

	public void setEpAbsolute(int epAbsolute) {
		this.epAbsolute = epAbsolute;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAiredDate() {
		return airedDate;
	}

	public void setAiredDate(String airedDate) {
		this.airedDate = airedDate;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
