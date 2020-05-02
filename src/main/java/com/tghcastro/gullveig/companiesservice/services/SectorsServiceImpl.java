package com.tghcastro.gullveig.companiesservice.services;

import com.tghcastro.gullveig.companiesservice.exceptions.SectorNotFoundException;
import com.tghcastro.gullveig.companiesservice.models.Sector;
import com.tghcastro.gullveig.companiesservice.repositories.SectorsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorsServiceImpl implements SectorsService {

    private final SectorsRepository sectorsRepository;

    public SectorsServiceImpl(SectorsRepository sectorsRepository) {
        this.sectorsRepository = sectorsRepository;
    }

    @Override
    public Sector getById(Long id) {
        return sectorsRepository
                .findById(id)
                .orElseThrow(() -> new SectorNotFoundException(id));
    }

    @Override
    public List<Sector> getAll() {
        return sectorsRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Sector create(Sector sector) {
        return sectorsRepository.saveAndFlush(sector);
    }

    @Override
    public void delete(Long id) {
        Sector sectorToDelete = this.getById(id);
        sectorToDelete.setEnabled(false);
        sectorsRepository.saveAndFlush(sectorToDelete);
    }

    @Override
    public Sector update(Long id, Sector sector) {
        Sector existentSector = this.getById(id);
        BeanUtils.copyProperties(sector, existentSector, "id");
        return sectorsRepository.saveAndFlush(existentSector);
    }
}
