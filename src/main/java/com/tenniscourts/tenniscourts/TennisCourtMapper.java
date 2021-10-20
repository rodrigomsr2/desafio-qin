package com.tenniscourts.tenniscourts;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.tenniscourts.tenniscourts.dto.TennisCourtDTO;
import com.tenniscourts.tenniscourts.dto.TennisCourtIdDTO;

@Mapper(componentModel = "spring")
public interface TennisCourtMapper {
    TennisCourtDTO map(TennisCourt source);

    @InheritInverseConfiguration
    TennisCourt map(TennisCourtDTO source);
    
    TennisCourt map(TennisCourtIdDTO source);
    
}
