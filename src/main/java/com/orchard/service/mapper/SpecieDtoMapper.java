package com.orchard.service.mapper;

import com.orchard.models.Specie;
import com.orchard.models.dto.SpecieDto;
import org.springframework.stereotype.Component;

@Component
public class SpecieDtoMapper {
    public Specie getSpecieFromDto(SpecieDto dto) {
        Specie specie = new Specie();
        specie.setName(dto.getSpecieName());
        specie.setDeleted(false);
        return specie;
    }

    public SpecieDto getDtoFromSpecie(Specie specie) {
        return new SpecieDto(specie.getName());
    }
}
