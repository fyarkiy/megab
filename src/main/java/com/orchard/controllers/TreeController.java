package com.orchard.controllers;

import com.orchard.models.dto.TreeRequestDto;
import com.orchard.models.dto.TreeResponseDto;
import com.orchard.service.TreeService;
import com.orchard.service.mapper.TreeDtoMapper;
import com.orchard.models.Tree;
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
@RequestMapping("/tree")
public class TreeController {
    private final TreeService treeService;
    private final TreeDtoMapper mapper;

    public TreeController(TreeService treeService, TreeDtoMapper mapper) {
        this.treeService = treeService;
        this.mapper = mapper;
    }

    @PostMapping
    public Tree addTree(@RequestBody TreeRequestDto requestDto) {
        return treeService.add(mapper.mapDtoToTree(requestDto));
    }

    @GetMapping
    public TreeResponseDto getTree(@RequestParam Long id) {
        return mapper.mapTreeToDto(treeService.getById(id));
    }

    @GetMapping("/all")
    public List<TreeResponseDto> getAllTree() {
        return treeService.getAll().stream()
                .map(mapper::mapTreeToDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    public Tree updateTree(@RequestParam Long id, @RequestBody TreeRequestDto dto) {
        return treeService.update(id, mapper.mapDtoToTree(dto));
    }

    @DeleteMapping
    public boolean deleteTree(@RequestParam Long id) {
        return treeService.remove(id);
    }
}
