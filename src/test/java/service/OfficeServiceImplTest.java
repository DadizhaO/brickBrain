package service;

import model.Office;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import repository.OfficeRepository;
import service.impl.OfficeServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class OfficeServiceImplTest {

    @Spy
    @InjectMocks
    private OfficeService officeService = new OfficeServiceImpl();

    @Mock
    private OfficeRepository officeRepository;

    private Office office1 = new Office();
    private Office office2 = new Office();

    @Test
    public void testGetAllOffices() {
        List<Office> offices = Arrays.asList(office1, office2);
        doReturn(offices).when(officeRepository).findAll();
        Set<Office> result = officeService.getAllOffices();
        assertTrue(offices.containsAll(result) && result.containsAll(offices));
    }

    @Test
    public void testFindOrderById() {
        doReturn(Optional.of(office1)).when(officeRepository).findById(any());
        Office result = officeService.findOfficeById(BigDecimal.ONE);
        assertEquals(office1, result);
        System.out.println(result);
    }

    @Test
    public void testFindByCityIgnoreCase() {
        List<Office> offices = Arrays.asList(office1, office2);
        doReturn(offices).when(officeRepository).findByCityStartingWithIgnoreCase(anyString());
        Set<Office> result = officeService.findByCityStartingWithIgnoreCase("city");
        assertEquals(offices, result);
    }

    @Test
    public void testInsertOrder() {
        doReturn(office1).when(officeRepository).saveAndFlush(any());
        officeService.insertOffice(office1);
        verify(officeRepository, times(1)).saveAndFlush(any());
    }

    @Test
    public void testUpdateOrder() {
        doReturn(office1).when(officeRepository).saveAndFlush(any());
        officeService.updateOffice(office1);
        verify(officeRepository, times(1)).saveAndFlush(any());
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(officeRepository).deleteById(any());
        officeService.deleteOffice(office1.getOfficeId());
        verify(officeRepository, times(1)).deleteById(any());
    }

}
