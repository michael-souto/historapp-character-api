package com.detrasoft.character.domain.entities;

import com.detrasoft.framework.crud.entities.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "note")
public class Note extends GenericEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_character", nullable = false, foreignKey = @ForeignKey(name = "fk1_note"))
    private Character character;

    @Column(nullable = true)
    private String tags;

    @Column(nullable = true)
    private String title;

    @Column(nullable = false)
    private String annotation;

    @Column(nullable = false, updatable = false)
    private Instant createAt;

    @Column(nullable = true)
    private Instant updateAt;
}
