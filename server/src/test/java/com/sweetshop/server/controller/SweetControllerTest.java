package com.sweetshop.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweetshop.server.dto.sweet.request.CreateSweetRequest;
import com.sweetshop.server.dto.sweet.request.UpdateSweetRequest;
import com.sweetshop.server.dto.sweet.response.SweetResponse;
import com.sweetshop.server.service.SweetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SweetController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@WithMockUser(username = "admin@abc.com", roles = {"ADMIN"})
class SweetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private SweetService sweetService;

    private CreateSweetRequest createSweetRequest;
    private UpdateSweetRequest updateSweetRequest;
    private SweetResponse sweetResponse;
    @BeforeEach
    void setUp() {
        createSweetRequest = new CreateSweetRequest();
        createSweetRequest.setName("Gulab Jamun");
        createSweetRequest.setCategory("Indian");
        createSweetRequest.setPrice(120.0);
        createSweetRequest.setStockCount(50);

        updateSweetRequest = new UpdateSweetRequest();
        updateSweetRequest.setName("Updated Gulab Jamun");
        updateSweetRequest.setPrice(150.0);

        sweetResponse = SweetResponse.builder()
                .id(1L)
                .name("Gulab Jamun")
                .category("Indian")
                .price(120.0)
                .stockCount(50)
                .build();
    }
    @Test
    void shouldCreateSweet() throws Exception {
        when(sweetService.createSweet(any(CreateSweetRequest.class))).thenReturn(sweetResponse);

        mockMvc.perform(
                post("/api/v1/sweets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Gulab Jamun",
                                  "category": "Indian",
                                  "price": 120.0,
                                  "stockCount": 50
                                }
                        """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gulab Jamun"))
                .andExpect(jsonPath("$.category").value("Indian"))
                .andExpect(jsonPath("$.price").value(120.0))
                .andExpect(jsonPath("$.stockCount").value(50));
    }
    @Test
    void shouldUpdateSweet() throws Exception {
        SweetResponse updatedResponse = SweetResponse.builder()
                .id(1L)
                .name("Updated Gulab Jamun")
                .category("Indian")
                .price(150.0)
                .stockCount(50)
                .build();

        when(sweetService.updateSweet(any(UpdateSweetRequest.class), eq(1L))).thenReturn(updatedResponse);

        mockMvc.perform(put("/api/v1/sweets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "name": "Updated Gulab Jamun",
                          "price": 150.0
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Gulab Jamun"))
                .andExpect(jsonPath("$.price").value(150.0));
    }
    @Test
    void shouldDeleteSweet() throws Exception {
        doNothing().when(sweetService).deleteSweet(1L);

        mockMvc.perform(delete("/api/v1/sweets/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sweet deleted successfully"));
    }



    @Test
    void searchSweets_byNameAndCategory_returnsResults() throws Exception {
        SweetResponse sweet1 = new SweetResponse(1L, "Ladoo", "Indian", 100.0,100);
        SweetResponse sweet2 = new SweetResponse(2L, "Barfi", "Indian", 150.0,200);

        when(sweetService.searchSweets(null, "Indian", null, null))
                .thenReturn(Set.of(sweet1, sweet2));

        mockMvc.perform(get("/api/v1/sweets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ladoo"))
                .andExpect(jsonPath("$[1].name").value("Barfi"))
                .andExpect(jsonPath("$[0].category").value("Indian"))
                .andExpect(jsonPath("$[1].category").value("Indian"));


    }
}

