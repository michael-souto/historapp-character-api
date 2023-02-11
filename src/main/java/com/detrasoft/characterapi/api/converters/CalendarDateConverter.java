package com.detrasoft.characterapi.api.converters;

import com.detrasoft.characterapi.api.dtos.CalendarDateDTO;
import com.detrasoft.characterapi.domain.entities.CalendarDate;
import com.detrasoft.framework.api.dto.converters.GenericEntityDTOConverter;
import org.springframework.stereotype.Component;

@Component
public class CalendarDateConverter extends GenericEntityDTOConverter<CalendarDate, CalendarDateDTO> {
}
