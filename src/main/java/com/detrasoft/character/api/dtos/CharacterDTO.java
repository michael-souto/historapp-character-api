package com.detrasoft.character.api.dtos;

import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.detrasoft.framework.api.dto.GenericRepresentationModelDTO;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "characters")
public class CharacterDTO extends GenericRepresentationModelDTO<CharacterDTO> {

    @JsonView({ResponseView.findAndPersist.class })
    private Long id;

    @NotBlank
    @JsonView(ResponseView.findAndPersist.class)
    private String name;

    @JsonView(ResponseView.findAndPersist.class)
    private String tags;

    @JsonView({ResponseView.findById.class, ResponseView.persist.class})
    private String comments;

    @NotBlank
    @JsonView(ResponseView.findAndPersist.class)
    private String sex;

    @JsonView({ResponseView.findById.class, ResponseView.findAll.class})
    private CharacterDTO father;

    @JsonView({ResponseView.findById.class, ResponseView.findAll.class})
    private CharacterDTO mother;

    @JsonView(ResponseView.findById.class)
    private HistoricalDateDTO birthDate;

    @JsonView(ResponseView.findById.class)
    private HistoricalDateDTO deathDate;

    @JsonView({ ResponseView.post.class, ResponseView.find.class})
    private Instant createAt;

    @JsonView({ ResponseView.put.class, ResponseView.find.class})
    private Instant updateAt;

}
