package com.detrasoft.character.domain.repositories;

import com.detrasoft.character.domain.entities.Character;
import com.detrasoft.framework.crud.repositories.GenericCRUDRepository;

import java.util.List;

public interface CharacterRepository extends GenericCRUDRepository<Character> {
    List<Character> findByIdIn(List<Long> idList);
}
