package com.tghcastro.gullveig.companiesservice.services;

import com.tghcastro.gullveig.companiesservice.exceptions.SectorNotFoundException;
import com.tghcastro.gullveig.companiesservice.models.Sector;
import com.tghcastro.gullveig.companiesservice.repositories.SectorsRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorsServiceImpl implements SectorsService {

    private final SectorsRepository sectorsRepository;
    private final Counter sectorsCreatedCounter;
    private final Counter sectorsUpdatedCounter;
    private final MeterRegistry meterRegistry;
    private final Counter sectorsDeletedCounter;

    public SectorsServiceImpl(SectorsRepository sectorsRepository, MeterRegistry meterRegistry) {
        this.sectorsRepository = sectorsRepository;
        this.meterRegistry = meterRegistry;
        sectorsCreatedCounter = this.meterRegistry.counter("sectors.created");
        sectorsUpdatedCounter = this.meterRegistry.counter("sectors.updated");
        sectorsDeletedCounter = this.meterRegistry.counter("sectors.deleted");
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
        Sector createdSector = sectorsRepository.saveAndFlush(sector);
        if (createdSector != null) {
            sectorsCreatedCounter.increment();
        }
        return createdSector;
    }

    @Override
    public void delete(Long id) {
        Sector sectorToDelete = this.getById(id);
        sectorToDelete.setEnabled(false);
        Sector deletedSector = sectorsRepository.saveAndFlush(sectorToDelete);
        if (deletedSector != null) {
            sectorsDeletedCounter.increment();
        }
    }

    @Override
    public Sector update(Long id, Sector sector) {
        Sector existentSector = this.getById(id);
        BeanUtils.copyProperties(sector, existentSector, "id");
        Sector updatedSector = sectorsRepository.saveAndFlush(existentSector);
        if (updatedSector != null) {
            sectorsUpdatedCounter.increment();
        }
        return updatedSector;
    }
}
