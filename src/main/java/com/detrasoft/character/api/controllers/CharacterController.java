package com.detrasoft.character.api.controllers;

import com.detrasoft.character.api.assemblers.CharacterAssembler;
import com.detrasoft.character.api.dtos.CharacterDTO;
import com.detrasoft.character.domain.crud.CharacterCRUDService;
import com.detrasoft.character.domain.repositories.CharacterRepository;
import com.detrasoft.framework.api.controllers.hateoas.GenericHateoasCRUDController;
import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/character")
public class CharacterController extends GenericHateoasCRUDController<CharacterDTO> {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    public CharacterController(CharacterCRUDService service, CharacterAssembler converter) {
        super(service, converter);
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

    @JsonView(ResponseView.findAll.class )
    @GetMapping(value = "/all")
    public ResponseEntity<List<CharacterDTO>> findAllCharacters() {
        return ResponseEntity.ok(
                characterRepository.findAll()
                        .stream()
                        .map(obj -> assembler.toModel(obj, true)).toList()
        );
    }
}
