package com.detrasoft.characterapi.domain.crud;

import com.detrasoft.characterapi.domain.entities.Note;
import com.detrasoft.characterapi.domain.repositories.NoteRepository;
import com.detrasoft.framework.crud.services.crud.GenericCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NoteCRUDService extends GenericCRUDService<Note> {

    @Autowired
    public NoteCRUDService(NoteRepository repository) {
        super(repository);
    }

    @Override
    protected void beforeInsert(Note entity) {
        entity.setCreateAt(Instant.now());
        super.beforeInsert(entity);
    }

    @Override
    public void beforeUpdate(Note entity) {
        entity.setUpdateAt(Instant.now());
        super.beforeUpdate(entity);
    }
}
