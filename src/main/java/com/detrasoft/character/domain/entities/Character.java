package com.detrasoft.character.domain.entities;

import com.detrasoft.framework.crud.entities.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "character")
public class Character extends GenericEntity implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String sex;

	@Column(nullable = true)
	private String tags;

	@Column(nullable = true)
	private String comments;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_historical_date_birth", nullable = true, foreignKey = @ForeignKey(name = "fk1_character"))
	private HistoricalDate birthDate;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_historical_date_death", nullable = true, foreignKey = @ForeignKey(name = "fk2_character"))
	private HistoricalDate deathDate;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_father", nullable = true, foreignKey = @ForeignKey(name = "fk3_character"))
	private Character father;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_mother", nullable = true, foreignKey = @ForeignKey(name = "fk4_character"))
	private Character mother;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "character")
	private List<Note> notes;

	@Column(nullable = false, updatable = false)
	private Instant createAt;

	@Column(nullable = true)
	private Instant updateAt;
}
