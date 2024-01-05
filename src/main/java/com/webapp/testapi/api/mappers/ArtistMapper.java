package com.webapp.testapi.api.mappers;

import com.webapp.testapi.api.dto.ArtistDTO;
import com.webapp.testapi.domain.model.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArtistMapper {

    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    ArtistDTO toDTO(Artist artist);

    Artist toEntity(ArtistDTO artistDTO);

}