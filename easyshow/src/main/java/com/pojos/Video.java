package com.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "EPISODE")
public class Video {

	@Id @GeneratedValue
	private int id;
	
	@Column(name = "hash")
	private String hash;
	
	@ManyToOne
	private Episode episode;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video v = (Video) obj;
		return new EqualsBuilder().append(this.getHash(), v.getHash())
				.isEquals();
	}
	
	@Override
	public int hashCode() {
	return new HashCodeBuilder(17, 31).append(this.getHash())
			.toHashCode();
	}
}
