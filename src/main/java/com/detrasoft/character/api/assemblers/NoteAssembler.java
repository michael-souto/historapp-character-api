package com.detrasoft.character.api.assemblers;

import com.detrasoft.character.api.controllers.NoteController;
import com.detrasoft.character.api.dtos.NoteDTO;
import com.detrasoft.character.domain.entities.Note;
import com.detrasoft.framework.api.dto.converters.GenericRepresentationModelDTOAssembler;
import com.detrasoft.character.domain.entities.Character;
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
