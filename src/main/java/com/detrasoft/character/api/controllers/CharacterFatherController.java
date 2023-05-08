package com.detrasoft.character.api.controllers;

import com.detrasoft.character.api.assemblers.CharacterAssembler;
import com.detrasoft.character.api.dtos.CharacterDTO;
import com.detrasoft.character.domain.crud.CharacterCRUDService;
import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/character")
public class CharacterFatherController {
    @Autowired
    CharacterCRUDService service;
    @Autowired
    CharacterAssembler assembler;

    @JsonView(ResponseView.put.class)
    @PutMapping(value = "/{id}/father/{idFather}")
    public ResponseEntity<CharacterDTO> addFather(
            @PathVariable Long id, @PathVariable Long idFather) {
        return ResponseEntity.ok().body(assembler.toModel(
                service.setFather(id, idFather),true));
    }

    @DeleteMapping(value = "/{id}/father")
    public ResponseEntity<Void> deleteFather(@PathVariable Long id) {
        service.setFather(id, null);
        return ResponseEntity.noContent().build();
    }
}
