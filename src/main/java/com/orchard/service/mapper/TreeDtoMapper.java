package com.orchard.service.mapper;

import com.orchard.models.dto.TreeRequestDto;
import com.orchard.models.dto.TreeResponseDto;
import com.orchard.models.Tree;
import com.orchard.service.SpecieService;
import org.springframework.stereotype.Component;

@Component
public class TreeDtoMapper {
    private final SpecieService specieService;

    public TreeDtoMapper(SpecieService specieService) {
        this.specieService = specieService;
    }

    public Tree mapDtoToTree(TreeRequestDto dto) {
        Tree tree = new Tree();
        tree.setAge(dto.getAge());
        tree.setVariety(dto.getVariety());
        tree.setSpecie(specieService.getById(dto.getSpecieId()));
        return tree;
    }

    public TreeResponseDto mapTreeToDto(Tree tree) {
        TreeResponseDto dto = new TreeResponseDto();
        dto.setSpecieId(tree.getSpecie().getId());
        dto.setVariety(tree.getVariety());
        dto.setAge(tree.getAge());
        return dto;
    }
}
