package com.detrasoft.character.api.assemblers;

import com.detrasoft.character.api.controllers.CharacterController;
import com.detrasoft.character.api.converters.HistoricalDateConverter;
import com.detrasoft.character.api.dtos.CharacterDTO;
import com.detrasoft.framework.api.dto.converters.GenericRepresentationModelDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.detrasoft.character.domain.entities.Character;

@Component
public class CharacterAssembler extends GenericRepresentationModelDTOAssembler<Character, CharacterDTO> {

    @Autowired
    private HistoricalDateConverter historicalDateConverter;

    public CharacterAssembler() {
        super(CharacterController.class, CharacterDTO.class);
    }

    @Override
    protected void copyEntityToDto(Character obj, CharacterDTO dto) {
        super.copyEntityToDto(obj, dto);
        if (obj.getBirthDate() != null) {
            dto.setBirthDate(historicalDateConverter.toDto(obj.getBirthDate()));
        }
        if (obj.getDeathDate() != null) {
            dto.setDeathDate(historicalDateConverter.toDto(obj.getDeathDate()));
        }
    }

    @Override
    protected void copyDtoToEntity(CharacterDTO dto, Character character) {
        super.copyDtoToEntity(dto, character);
        if (dto.getBirthDate() != null) {
            character.setBirthDate(historicalDateConverter.toEntity(dto.getBirthDate()));
        }
        if (dto.getDeathDate() != null) {
            character.setDeathDate(historicalDateConverter.toEntity(dto.getDeathDate()));
        }
    }
}
