package com.orchard.service;

import com.orchard.models.Tree;
import java.util.List;
import com.orchard.repository.TreeRepository;
import org.springframework.stereotype.Service;

@Service
public class TreeServiceImpl implements TreeService {
    private final TreeRepository treeRepository;

    public TreeServiceImpl(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public Tree add(Tree tree) {
        tree.setDeleted(false);
        return treeRepository.add(tree);
    }

    @Override
    public List<Tree> getAll() {
        return treeRepository.getAll();
    }

    @Override
    public Tree getById(Long id) {
        return treeRepository.getById(id);
    }

    @Override
    public boolean remove(Long id) {
        Tree tree = treeRepository.getById(id);
        tree.setDeleted(true);
        return treeRepository.remove(tree);
    }

    @Override
    public Tree update(Long id, Tree tree) {
        Tree updatedTree = new Tree();
        updatedTree.setTreeId(id);
        updatedTree.setAge(tree.getAge());
        updatedTree.setSpecie(tree.getSpecie());
        updatedTree.setVariety(tree.getVariety());
        if (tree.getDeleted() == null) {
            updatedTree.setDeleted(false);
        } else {
            updatedTree.setDeleted(tree.getDeleted());
        }
        return treeRepository.update(updatedTree);
    }
}
