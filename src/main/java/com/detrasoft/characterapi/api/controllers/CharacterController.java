package com.detrasoft.characterapi.api.controllers;

import com.detrasoft.characterapi.api.assemblers.CharacterAssembler;
import com.detrasoft.characterapi.api.converters.HistoricalDateConverter;
import com.detrasoft.characterapi.api.dtos.CharacterDTO;
import com.detrasoft.characterapi.api.dtos.HistoricalDateDTO;
import com.detrasoft.characterapi.domain.crud.CharacterCRUDService;
import com.detrasoft.framework.api.controllers.hateoas.GenericHateoasCRUDController;
import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/character")
public class CharacterController extends GenericHateoasCRUDController<CharacterDTO> {

    @Autowired
    private HistoricalDateConverter historicalDateConverter;

    @Autowired
    public CharacterController(CharacterCRUDService service, CharacterAssembler converter) {
        super(service, converter);
    }

    @JsonView(ResponseView.put.class)
    @PutMapping(value = "/{id}/birth-date")
    public ResponseEntity<CharacterDTO> addBirthDate(@PathVariable Long id, @RequestBody @Valid HistoricalDateDTO dto) {
        return ResponseEntity.ok().body(assembler.toModel(
                ((CharacterCRUDService)service).setBirthDate(
                        id, historicalDateConverter.toEntity(dto)),
                true));
    }

    @DeleteMapping(value = "/{id}/birth-date")
    public ResponseEntity<Void> deleteBirthDate(@PathVariable Long id) {
        ((CharacterCRUDService)service).setBirthDate(id, null);
        return ResponseEntity.noContent().build();
    }

    @JsonView(ResponseView.put.class)
    @PutMapping(value = "/{id}/death-date")
    public ResponseEntity<CharacterDTO> addDeathDate(@PathVariable Long id, @RequestBody @Valid HistoricalDateDTO dto) {
        return ResponseEntity.ok().body(assembler.toModel(
                ((CharacterCRUDService)service).setDeathDate(
                        id, historicalDateConverter.toEntity(dto)),
                true));
    }

    @DeleteMapping(value = "/{id}/death-date")
    public ResponseEntity<Void> deleteDeathDate(@PathVariable Long id) {
        ((CharacterCRUDService)service).setDeathDate(id, null);
        return ResponseEntity.noContent().build();
    }
}
