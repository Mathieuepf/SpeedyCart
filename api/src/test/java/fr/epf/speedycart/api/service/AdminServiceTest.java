package fr.epf.speedycart.api.service;

import fr.epf.speedycart.api.exception.UserNotFoundException;
import fr.epf.speedycart.api.model.Admin;
import fr.epf.speedycart.api.repository.AdminDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminDao adminDao;

    @Test
    public void saveAdminDataTest() {
        Admin admin = new Admin();
        admin.setId(0L);

        when(adminDao.save(admin)).thenReturn(admin);
        Admin saved = adminService.saveAdminData(admin);
        assertEquals(saved, admin);
    }

    @Test
    public void deleteAdminDataTest() {
        Long id = 0L;
        Admin admin = new Admin();
        admin.setId(id);

        when(adminDao.findById(id)).thenReturn(Optional.of(admin));
        adminService.deleteAdminData(id);
        verify(adminDao, times(1)).deleteById(id);
    }

    @Test
    public void deleteAdminDataNotFoundTest() {
        Long id = 0L;

        when(adminDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> adminService.deleteAdminData(id));
    }
}
