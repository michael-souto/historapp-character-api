package com.detrasoft.character.api.controllers;

import com.detrasoft.character.api.assemblers.CharacterAssembler;
import com.detrasoft.character.api.converters.HistoricalDateConverter;
import com.detrasoft.character.api.dtos.CharacterDTO;
import com.detrasoft.character.api.dtos.HistoricalDateDTO;
import com.detrasoft.character.domain.crud.CharacterCRUDService;
import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/character")
public class CharacterDearhDateController {
    @Autowired
    HistoricalDateConverter historicalDateConverter;
    @Autowired
    CharacterCRUDService service;
    @Autowired
    CharacterAssembler assembler;

    @JsonView(ResponseView.put.class)
    @PutMapping(value = "/{id}/death-date")
    public ResponseEntity<CharacterDTO> addDeathDate(@PathVariable Long id, @RequestBody @Valid HistoricalDateDTO dto) {
        return ResponseEntity.ok().body(assembler.toModel(
                ((CharacterCRUDService) service).setDeathDate(
                        id, historicalDateConverter.toEntity(dto)),
                true));
    }

    @DeleteMapping(value = "/{id}/death-date")
    public ResponseEntity<Void> deleteDeathDate(@PathVariable Long id) {
        ((CharacterCRUDService) service).setDeathDate(id, null);
        return ResponseEntity.noContent().build();
    }
}
