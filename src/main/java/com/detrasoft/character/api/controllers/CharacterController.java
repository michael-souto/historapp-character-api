package com.detrasoft.character.api.controllers;

import com.detrasoft.character.api.assemblers.CharacterAssembler;
import com.detrasoft.character.api.converters.HistoricalDateConverter;
import com.detrasoft.character.api.dtos.CharacterDTO;
import com.detrasoft.character.api.dtos.HistoricalDateDTO;
import com.detrasoft.character.domain.crud.CharacterCRUDService;
import com.detrasoft.character.domain.repositories.CharacterRepository;
import com.detrasoft.framework.api.controllers.hateoas.GenericHateoasCRUDController;
import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/character")
public class CharacterController extends GenericHateoasCRUDController<CharacterDTO> {

    @Autowired
    private HistoricalDateConverter historicalDateConverter;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    public CharacterController(CharacterCRUDService service, CharacterAssembler converter) {
        super(service, converter);
    }

    @JsonView(ResponseView.put.class)
    @PutMapping(value = "/{id}/birth-date")
    public ResponseEntity<CharacterDTO> addBirthDate(@PathVariable Long id, @RequestBody @Valid HistoricalDateDTO dto) {
        return ResponseEntity.ok().body(assembler.toModel(
                ((CharacterCRUDService) service).setBirthDate(
                        id, historicalDateConverter.toEntity(dto)),
                true));
    }

    @DeleteMapping(value = "/{id}/birth-date")
    public ResponseEntity<Void> deleteBirthDate(@PathVariable Long id) {
        ((CharacterCRUDService) service).setBirthDate(id, null);
        return ResponseEntity.noContent().build();
    }

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

    @JsonView(ResponseView.findAll.class)
    @PostMapping(value = "/by-list-id")
    public ResponseEntity<List<CharacterDTO>> findByListId(@RequestBody List<Long> body) {
        return ResponseEntity.ok(
                characterRepository.findByIdIn(body)
                        .stream()
                        .map(obj -> assembler.toModel(obj, true)).toList()
        );
    }
}
