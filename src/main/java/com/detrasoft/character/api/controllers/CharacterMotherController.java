package com.detrasoft.character.api.controllers;

import com.detrasoft.character.api.assemblers.CharacterAssembler;
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
public class CharacterMotherController {
    @Autowired
    CharacterCRUDService service;
    @Autowired
    CharacterAssembler assembler;

    @JsonView(ResponseView.put.class)
    @PutMapping(value = "/{id}/mother/{idMother}")
    public ResponseEntity<CharacterDTO> addMother(
            @PathVariable Long id, @PathVariable Long idMother) {
        return ResponseEntity.ok().body(assembler.toModel(
                service.setMother(id, idMother),true));
    }

    @DeleteMapping(value = "/{id}/mother")
    public ResponseEntity<Void> deleteMother(@PathVariable Long id) {
        service.setMother(id, null);
        return ResponseEntity.noContent().build();
    }
}
