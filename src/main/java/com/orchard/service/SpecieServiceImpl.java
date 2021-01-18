package com.orchard.service;

import com.orchard.models.Specie;
import com.orchard.repository.SpecieRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SpecieServiceImpl implements SpecieService {
    private final SpecieRepository specieRepository;

    public SpecieServiceImpl(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }

    @Override
    public Specie add(Specie specie) {
        specie.setDeleted(false);
        return specieRepository.add(specie);
    }

    @Override
    public List<Specie> getAll() {
        return specieRepository.getAll();
    }

    @Override
    public Specie getById(Long id) {
        return specieRepository.getById(id);
    }

    @Override
    public boolean remove(Long id) {
        Specie specie = specieRepository.getById(id);
        specie.setDeleted(true);
        return specieRepository.remove(specie);
    }

    @Override
    public Specie update(Long id, Specie specie) {
        Specie updatedSpecie = new Specie();
        updatedSpecie.setId(id);
        updatedSpecie.setName(specie.getName());
        if (specie.getDeleted() == null) {
            updatedSpecie.setDeleted(false);
        } else {
            updatedSpecie.setDeleted(specie.getDeleted());
        }
        return specieRepository.update(updatedSpecie);
    }
}
