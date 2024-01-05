package com.webapp.testapi.api.mapper;

import com.webapp.testapi.api.dto.SongDTO;
import com.webapp.testapi.domain.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SongMapper {

    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(target = "artistId", source = "artist.id")
    SongDTO toDTO(Song song);

    @Mapping(target = "artist.id", source = "artistId")
    Song toEntity(SongDTO songDTO);

}