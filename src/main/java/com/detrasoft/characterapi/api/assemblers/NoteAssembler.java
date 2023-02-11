package com.detrasoft.characterapi.api.assemblers;

import com.detrasoft.characterapi.api.controllers.NoteController;
import com.detrasoft.characterapi.api.dtos.NoteDTO;
import com.detrasoft.characterapi.domain.entities.Note;
import com.detrasoft.framework.api.dto.converters.GenericRepresentationModelDTOAssembler;
import com.detrasoft.characterapi.domain.entities.Character;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NoteAssembler extends GenericRepresentationModelDTOAssembler<Note, NoteDTO> {
    public NoteAssembler() {
        super(NoteController.class, NoteDTO.class);
    }

    @Override
    public Note toEntity(NoteDTO dto) {

        var entity = new Note();
        BeanUtils.copyProperties(dto, entity);

        entity.setCharacter(
                Character.builder().id(dto.getIdDetail()).build()
        );

        return entity;
    }
}
