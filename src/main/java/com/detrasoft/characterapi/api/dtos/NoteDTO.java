package com.detrasoft.characterapi.api.dtos;

import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.detrasoft.framework.api.dto.GenericRepresentationModelDTO;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "notes")
public class NoteDTO extends GenericRepresentationModelDTO<NoteDTO> {

    @JsonView(ResponseView.findAndPersist.class)
    private Long id;

    @JsonView(ResponseView.findAndPersist.class)
    private String tags;

    @JsonView(ResponseView.findAndPersist.class)
    private String title;

    @JsonView(ResponseView.findAndPersist.class)
    private String annotation;

    @JsonView({ ResponseView.post.class, ResponseView.find.class})
    private Instant createAt;

    @JsonView({ ResponseView.put.class, ResponseView.find.class})
    private Instant updateAt;

    @JsonView(ResponseView.ignore.class)
    private Long idDetail;
}
