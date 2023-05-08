package com.detrasoft.character.api.controllers;

import com.detrasoft.character.api.assemblers.CharacterAssembler;
import com.detrasoft.character.api.dtos.CharacterDTO;
import com.detrasoft.character.domain.entities.Character;
import com.detrasoft.character.domain.repositories.CharacterRepository;
import com.detrasoft.character.domain.specifications.CharacterSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/character/search")
public class CharacterSearchController {

    @Autowired
    private CharacterRepository _characterRepository;

    @Autowired
    private CharacterAssembler _characterAssembler;

    @GetMapping(value = "")
    public ResponseEntity<Page<CharacterDTO>> search(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "id-mother", required = false) Long idMother,
            Pageable pageable) {
        Page<Character> list = _characterRepository.findAll(CharacterSpecs.byName(name), pageable);

        Page<CharacterDTO> resultList = new PageImpl<CharacterDTO>(
                list.getContent().stream()
                        .map(obj -> _characterAssembler.toModel(obj)).toList(), pageable, list.getTotalElements());
        return ResponseEntity.ok().body(resultList);
    }
}
