package com.detrasoft.characterapi.domain.repositories;

import com.detrasoft.characterapi.domain.entities.Note;
import com.detrasoft.framework.crud.repositories.GenericCRUDRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteRepository extends GenericCRUDRepository<Note> {
    Page<Note> findByCharacterId(Long id, Pageable pageable);
}
