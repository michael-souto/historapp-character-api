package com.detrasoft.character.api.converters;

import com.detrasoft.character.api.dtos.CalendarDateDTO;
import com.detrasoft.character.domain.entities.CalendarDate;
import com.detrasoft.framework.api.dto.converters.GenericEntityDTOConverter;
import org.springframework.stereotype.Component;

@Component
public class CalendarDateConverter extends GenericEntityDTOConverter<CalendarDate, CalendarDateDTO> {
}
