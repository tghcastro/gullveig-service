package com.tghcastro.gullveig.companies.service.domain.services;

import com.tghcastro.gullveig.companies.service.domain.exceptions.SectorAlreadyExistentException;
import com.tghcastro.gullveig.companies.service.domain.exceptions.SectorInUseException;
import com.tghcastro.gullveig.companies.service.domain.exceptions.SectorNotFoundException;
import com.tghcastro.gullveig.companies.service.domain.interfaces.repositories.SectorsRepository;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.CompaniesService;
import com.tghcastro.gullveig.companies.service.domain.interfaces.services.SectorsService;
import com.tghcastro.gullveig.companies.service.domain.models.Sector;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorsDomainService implements SectorsService {

    private final SectorsRepository sectorsRepository;
    private final CompaniesService companiesService;
    private final Counter sectorsCreatedCounter;
    private final Counter sectorsUpdatedCounter;
    private final MeterRegistry meterRegistry;
    private final Counter sectorsDeletedCounter;

    public SectorsDomainService(SectorsRepository sectorsRepository, CompaniesService companiesService, MeterRegistry meterRegistry) {
        this.sectorsRepository = sectorsRepository;
        this.companiesService = companiesService;
        this.meterRegistry = meterRegistry;

        //TODO: Better place to put this (metrics initializer)
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
        sectorsRepository
                .getByName(sector.getName())
                .ifPresent(foundSector -> {
                    throw new SectorAlreadyExistentException(foundSector);
                });


        Sector createdSector = sectorsRepository.saveAndFlush(sector);
        if (createdSector != null) {
            sectorsCreatedCounter.increment();
        }
        return createdSector;
    }

    @Override
    public void delete(Long id) {
        Sector sectorToDelete = this.getById(id);

        this.companiesService.getBySectorId(id)
                .ifPresent(foundCompany -> {
                    throw new SectorInUseException(foundCompany, sectorToDelete);
                });

        sectorToDelete.setEnabled(false);
        Sector deletedSector = this.update(id, sectorToDelete);
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
