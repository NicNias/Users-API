package com.app.users.mappers;

import com.app.users.dto.AdmDto;
import com.app.users.entity.AdmEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdmMapper {
    AdmEntity toModel(AdmDto admDto);
    AdmDto toDto(AdmEntity admEntity);

    List<AdmDto> toDtoList(List<AdmEntity> adms);
}
