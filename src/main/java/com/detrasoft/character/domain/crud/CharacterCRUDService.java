package com.detrasoft.character.domain.crud;

import com.detrasoft.character.domain.entities.Character;
import com.detrasoft.character.domain.entities.HistoricalDate;
import com.detrasoft.character.domain.repositories.CharacterRepository;
import com.detrasoft.character.domain.repositories.HistoricalDateRepository;
import com.detrasoft.framework.crud.library.GeneralFunctionsCRUD;
import com.detrasoft.framework.crud.services.crud.GenericCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CharacterCRUDService extends GenericCRUDService<Character> {

    @Autowired
    public HistoricalDateRepository historicalDateRepository;

    @Autowired
    public CharacterCRUDService(CharacterRepository repository) {
        super(repository);
    }

    public Character setDeathDate(Long idCharacter, HistoricalDate historicalDate) {
        var characterFound = this.findById(idCharacter);
        var date = characterFound.getDeathDate();
        characterFound.setDeathDate(updateDate(historicalDate, characterFound.getDeathDate()));
        beforeUpdate(characterFound);
        characterFound = repository.save(characterFound);

        if (GeneralFunctionsCRUD.checkEmptyOrNull(historicalDate) && (date != null && date.getId() != null)) {
            historicalDateRepository.delete(date);
        }
        return characterFound;
    }
    public Character setBirthDate(Long idCharacter, HistoricalDate historicalDate) {
        var characterFound = this.findById(idCharacter);
        var date = characterFound.getBirthDate();
        characterFound.setBirthDate(updateDate(historicalDate, characterFound.getBirthDate()));
        beforeUpdate(characterFound);
        characterFound = repository.save(characterFound);

        if (GeneralFunctionsCRUD.checkEmptyOrNull(historicalDate) && (date != null && date.getId() != null)) {
            historicalDateRepository.delete(date);
        }
        return characterFound;
    }

    public HistoricalDate updateDate(HistoricalDate newHistoricalDate, HistoricalDate oldHistoricalDate) {
        if (!GeneralFunctionsCRUD.checkEmptyOrNull(newHistoricalDate)) {
            if (oldHistoricalDate != null && oldHistoricalDate.getId() != null) {
                oldHistoricalDate.setDate(newHistoricalDate.getDate());
                oldHistoricalDate.getDatesCalendars().clear();
                if (newHistoricalDate.getDatesCalendars() != null
                        && newHistoricalDate.getDatesCalendars().size() > 0) {
                    oldHistoricalDate.getDatesCalendars().addAll(newHistoricalDate.getDatesCalendars());
                }
                return oldHistoricalDate;
            } else {
                return (newHistoricalDate);
            }
        } else {
            return (null);
        }
    }

    protected void beforeSave(Character entity) {
        if (entity.getBirthDate() != null
            && entity.getBirthDate().getDatesCalendars() != null
            && entity.getBirthDate().getDatesCalendars().size() > 0) {
            entity.getBirthDate().getDatesCalendars().forEach(x-> x.setHistoricalDate(entity.getBirthDate()));
        }
        if (entity.getDeathDate() != null
                && entity.getDeathDate().getDatesCalendars() != null
                && entity.getDeathDate().getDatesCalendars().size() > 0) {
            entity.getDeathDate().getDatesCalendars().forEach(x-> x.setHistoricalDate(entity.getDeathDate()));
        }
    }
    @Override
    protected void beforeInsert(Character entity) {
        beforeSave(entity);
        entity.setCreateAt(Instant.now());
        super.beforeInsert(entity);
    }

    @Override
    public void beforeUpdate(Character entity) {
        beforeSave(entity);
        entity.setUpdateAt(Instant.now());
        super.beforeUpdate(entity);
    }
}
