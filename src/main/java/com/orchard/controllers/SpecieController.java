package com.orchard.controllers;

import com.orchard.models.dto.SpecieDto;
import com.orchard.service.SpecieService;
import com.orchard.models.Specie;
import com.orchard.service.mapper.SpecieDtoMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specie")
public class SpecieController {
    private final SpecieService specieService;
    private final SpecieDtoMapper mapper;

    public SpecieController(SpecieService specieService, SpecieDtoMapper mapper) {
        this.specieService = specieService;
        this.mapper = mapper;
    }

    @PostMapping
    public Specie addSpecie(@RequestBody SpecieDto dto) {
        return specieService.add(mapper.getSpecieFromDto(dto));
    }

    @GetMapping
    public SpecieDto getSpecie(@RequestParam Long id) {
        return mapper.getDtoFromSpecie(specieService.getById(id));
    }

    @GetMapping("/all")
    public List<SpecieDto> getAllSpecies(){
        return specieService.getAll().stream()
                .map(mapper::getDtoFromSpecie)
                .collect(Collectors.toList());
    }

    @PutMapping
    public Specie updateSpecie(@RequestParam Long id, @RequestBody SpecieDto dto) {
        return specieService.update(id, mapper.getSpecieFromDto(dto));
    }

    @DeleteMapping
    public Boolean deleteSpecie(@RequestParam Long id) {
        return specieService.remove(id);
    }
}
