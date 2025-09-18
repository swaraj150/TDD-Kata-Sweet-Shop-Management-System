package com.sweetshop.server.service;

import com.sweetshop.server.dto.sweet.request.CreateSweetRequest;
import com.sweetshop.server.dto.sweet.request.UpdateSweetRequest;
import com.sweetshop.server.dto.user.response.UserResponse;
import com.sweetshop.server.entity.Sweet;
import com.sweetshop.server.entity.UserRole;
import com.sweetshop.server.repository.SweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class SweetServiceTest {
    @InjectMocks
    private SweetService sweetService;

    @Mock
    private UserService userService;

    @Mock
    private SweetRepository sweetRepository;


    private UserResponse adminUser;
    private UserResponse normalUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        // Admin with all authorities
        adminUser = new UserResponse();
        adminUser.setRole(UserRole.ADMIN);

        // Normal user with limited authorities
        normalUser = new UserResponse();
        normalUser.setRole(UserRole.USER);

        when(userService.loadCurrentUser()).thenReturn(adminUser);


    }

    @Test
    void createSweet_shouldReturnSweetResponse_whenUserHasAuthority() {
//        when(userService.loadCurrentUser()).thenReturn(adminUser);

        CreateSweetRequest request = new CreateSweetRequest();
        request.setName("Chocolate");
        request.setCategory("Candy");
        request.setPrice(10.0);
        request.setStockCount(100);

        Sweet savedSweet = new Sweet();
        savedSweet.setId(1L);
        savedSweet.setName(request.getName());
        savedSweet.setCategory(request.getCategory());
        savedSweet.setPrice(request.getPrice());
        savedSweet.setStockCount(request.getStockCount());

        when(sweetRepository.save(any(Sweet.class))).thenReturn(savedSweet);

        var response = sweetService.createSweet(request);

        assertNotNull(response);
        assertEquals("Chocolate", response.getName());
        verify(sweetRepository, times(1)).save(any(Sweet.class));
    }

    @Test
    void createSweet_shouldThrowUnauthorized_whenUserLacksAuthority() {
        when(userService.loadCurrentUser()).thenReturn(normalUser);

        CreateSweetRequest request = new CreateSweetRequest();
        Exception ex = assertThrows(RuntimeException.class, () -> sweetService.createSweet(request));
        assertTrue(ex.getMessage().contains("User does not have the required authority"));
        verify(sweetRepository, never()).save(any());
    }

    @Test
    void updateSweet_shouldUpdateFields_whenUserHasAuthority() {
        when(userService.loadCurrentUser()).thenReturn(adminUser);

        Sweet sweet = new Sweet();
        sweet.setId(1L);
        sweet.setName("OldName");
        when(sweetRepository.findById(1L)).thenReturn(Optional.of(sweet));

        UpdateSweetRequest request = new UpdateSweetRequest();
        request.setName("NewName");

        var response = sweetService.updateSweet(request, 1L);

        assertEquals("NewName", response.getName());
        verify(sweetRepository, times(1)).save(sweet);
    }

    @Test
    void updateSweet_shouldThrowUnauthorized_whenUserLacksAuthority() {
        when(userService.loadCurrentUser()).thenReturn(normalUser);

        UpdateSweetRequest request = new UpdateSweetRequest();
        Exception ex = assertThrows(RuntimeException.class, () -> sweetService.updateSweet(request, 1L));
        assertTrue(ex.getMessage().contains("User does not have the required authority"));
    }

    @Test
    void purchaseSweet_shouldDecreaseStock_whenStockAvailable() {
        when(userService.loadCurrentUser()).thenReturn(adminUser);

        Sweet sweet = new Sweet();
        sweet.setId(1L);
        sweet.setStockCount(5);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(sweet));

        sweetService.updateInventory(1L,-1);

        assertEquals(4, sweet.getStockCount());
        verify(sweetRepository, times(1)).save(sweet);
    }

    @Test
    void purchaseSweet_shouldThrowException_whenStockZero() {
        when(userService.loadCurrentUser()).thenReturn(adminUser);

        Sweet sweet = new Sweet();
        sweet.setId(1L);
        sweet.setStockCount(0);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(sweet));

        assertThrows(IllegalStateException.class, () -> sweetService.updateInventory(1L,-1));
    }

    @Test
    void deleteSweet_shouldCallRepository_whenUserHasAuthority() {
        when(userService.loadCurrentUser()).thenReturn(adminUser);
        doNothing().when(sweetRepository).deleteById(1L);

        sweetService.deleteSweet(1L);

        verify(sweetRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteSweet_shouldThrowUnauthorized_whenUserLacksAuthority() {
        when(userService.loadCurrentUser()).thenReturn(normalUser);

        assertThrows(RuntimeException.class, () -> sweetService.deleteSweet(1L));
        verify(sweetRepository, never()).deleteById(any());
    }

    @Test
    void loadAllSweets_shouldReturnSetOfSweetResponses() {
        Sweet sweet1 = new Sweet();
        sweet1.setName("Candy");
        Sweet sweet2 = new Sweet();
        sweet2.setName("Chocolate");

        when(sweetRepository.findAll()).thenReturn(List.of(sweet1, sweet2));
        var result = sweetService.loadAllSweets();
        assertEquals(2, result.size());
    }


}
