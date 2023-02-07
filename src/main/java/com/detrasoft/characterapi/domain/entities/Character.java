package com.detrasoft.characterapi.domain.entities;

import com.detrasoft.framework.crud.entities.AuditedGenericEntity;
import com.detrasoft.historapp.entities.calendar.HistoricalDate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "character")
public class Character extends AuditedGenericEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private String birthPlace;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant birthDate;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant deathDate;
	
	@ManyToMany
	@JoinTable(name = "character_birth_date", 
		joinColumns = { @JoinColumn(name = "character_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "historical_date_id") })
	private List<HistoricalDate> birthDateCalendars;
	
	@ManyToMany
	@JoinTable(name = "character_death_date", 
		joinColumns = { @JoinColumn(name = "character_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "historical_date_id") })
	private List<HistoricalDate> deathDateCalendars;

	@Override
	public String toString() {
		return "Character{" +
				"name='" + name + '\'' +
				'}';
	}
}
