package com.detrasoft.characterapi.api.controllers;

import com.detrasoft.characterapi.api.assemblers.NoteAssembler;
import com.detrasoft.characterapi.api.dtos.NoteDTO;
import com.detrasoft.characterapi.domain.crud.NoteCRUDService;
import com.detrasoft.characterapi.domain.repositories.NoteRepository;
import com.detrasoft.framework.api.controllers.hateoas.GenericHateoasDetailController;
import com.detrasoft.framework.crud.entities.GenericEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "character/{idDetail}/notes")
public class NoteController extends GenericHateoasDetailController<NoteDTO> {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    public NoteController(NoteCRUDService service, NoteAssembler assembler) {
        super(service, assembler);
    }

    @Override
    protected void setIdSubDetailInDTO(Long idDetail, Long idSubDetail, NoteDTO dto) {
        dto.setIdDetail(idDetail);
    }

    @Override
    protected Page<? extends GenericEntity> findAllByIdDetail(Long idDetail, Pageable pageable) {
        return noteRepository.findByCharacterId(idDetail, pageable);
    }
}
