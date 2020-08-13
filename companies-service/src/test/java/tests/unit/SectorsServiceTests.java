package tests.unit;

import autofixture.publicinterface.Any;
import gullveig.companiesservice.exceptions.SectorAlreadyExistentException;
import gullveig.companiesservice.exceptions.SectorNotFoundException;
import gullveig.companiesservice.models.Sector;
import gullveig.companiesservice.repositories.SectorsRepository;
import gullveig.companiesservice.services.SectorsServiceImpl;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SectorsServiceTests {

    private SectorsServiceImpl sectorsService;
    private SectorsRepository sectorsRepository;
    private MeterRegistry meterRegistry;

    @BeforeEach
    void initUseCase() {
        sectorsRepository = Mockito.mock(SectorsRepository.class);
        meterRegistry = Mockito.mock(MeterRegistry.class);
        Counter someCounter = Mockito.mock(Counter.class);
        when(meterRegistry.counter(anyString())).thenReturn(someCounter);
        sectorsService = new SectorsServiceImpl(sectorsRepository, meterRegistry);
    }

    @Test
    public void test_getAll_shouldReturnAllSectors() {
        List<Sector> fakeSectors = Any.listOf(Sector.class);
        when(sectorsRepository.findAllByOrderByNameAsc()).thenReturn(fakeSectors);

        List<Sector> foundSectors = sectorsService.getAll();

        assertThat(foundSectors).isNotNull().isNotEmpty();
        assertThat(foundSectors.size()).isEqualTo(fakeSectors.size());
    }

    @Test
    public void test_getAll_shouldReturnEmpty_whenNoSectorsFound() {
        List<Sector> fakeSectors = new ArrayList<>();
        when(sectorsRepository.findAllByOrderByNameAsc()).thenReturn(fakeSectors);

        List<Sector> foundSectors = sectorsService.getAll();

        assertThat(foundSectors).isNotNull().isEmpty();
    }

    @Test
    public void test_getById_shouldReturnSector() {
        Sector fakeSector = Any.of(Sector.class);
        when(sectorsRepository.findById(fakeSector.getId())).thenReturn(Optional.of(fakeSector));

        Sector foundSector = sectorsService.getById(fakeSector.getId());

        assertThat(foundSector).isNotNull();
        assertThat(foundSector).isEqualTo(fakeSector);
    }

    //TODO: Refactor test and application for this scenario
    @Test
    public void test_getById_shouldThrowException_whenNoSectorFound() {
        long nonExistentSector = 1;
        when(sectorsRepository.findById(nonExistentSector)).thenThrow(SectorNotFoundException.class);

        assertThrows(SectorNotFoundException.class, () -> sectorsService.getById(nonExistentSector));
    }

    @Test
    public void test_create_shouldReturnCreatedSector() {
        Sector fakeSector = Any.of(Sector.class);
        when(sectorsRepository.saveAndFlush(fakeSector)).thenReturn(fakeSector);

        Sector createdSector = sectorsService.create(fakeSector);

        assertThat(createdSector).isEqualTo(fakeSector);
    }

    @Test
    public void test_create_shouldNotAllowSectorsWithSameName(){
        String sameName = "mysector";

        Sector sectorAlreadyExistent = Any.of(Sector.class);
        sectorAlreadyExistent.setName(sameName);

        Sector newSector = Any.of(Sector.class);
        newSector.setName(sameName);

        when(sectorsRepository.getByName(newSector.getName())).thenReturn(Optional.of(sectorAlreadyExistent));

        assertThrows(SectorAlreadyExistentException.class, () -> sectorsService.create(newSector));
    }

    @Test
    public void test_update_shouldReturnUpdatedSector() {
        Sector originalSector = new Sector("SomeSector");
        originalSector.setId((long)1);
        originalSector.setEnabled(true);

        Sector sectorToUpdate = new Sector("SomeSector");
        BeanUtils.copyProperties(originalSector, sectorToUpdate);
        sectorToUpdate.setName("New name");

        when(sectorsRepository.findById(originalSector.getId())).thenReturn(Optional.of(originalSector));
        when(sectorsRepository.saveAndFlush(sectorToUpdate)).thenReturn(sectorToUpdate);

        Sector updatedSector = sectorsService.update(sectorToUpdate.getId(), sectorToUpdate);

        assertThat(updatedSector).isEqualTo(sectorToUpdate);
    }

    @Test
    public void test_update_shouldThrowException_whenSectorDoesNotExist() {
        Sector nonExistentSector = Any.of(Sector.class);

        when(sectorsRepository.findById(nonExistentSector.getId())).thenThrow(SectorNotFoundException.class);

        assertThrows(SectorNotFoundException.class, () -> sectorsService.update(nonExistentSector.getId(), nonExistentSector));
    }

    @Test
    public void test_delete_shouldUpdateEnableToFalse_whenDeletingSector() {
        Sector originalSector = new Sector("SomeSector");
        originalSector.setId((long)1);
        originalSector.setEnabled(true);

        Sector deletedSector = new Sector();
        BeanUtils.copyProperties(originalSector, deletedSector);
        deletedSector.setEnabled(false);

        when(sectorsRepository.findById(originalSector.getId())).thenReturn(Optional.of(originalSector));
        when(sectorsRepository.saveAndFlush(deletedSector)).thenReturn(deletedSector);

        sectorsService.delete(deletedSector.getId());

        verify(sectorsRepository, times(1)).saveAndFlush(deletedSector);
    }

    @Test
    public void test_delete_shouldThrowException_whenSectorDoesNotExist() {
        Sector nonExistentSector = Any.of(Sector.class);

        when(sectorsRepository.findById(nonExistentSector.getId())).thenThrow(SectorNotFoundException.class);

        assertThrows(SectorNotFoundException.class, () -> sectorsService.delete(nonExistentSector.getId()));
    }
}
