package com.sweetshop.server.dto.sweet.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSweetRequest {
    private String name;
    private String category;
    private Double price;
    private Integer stockCount;
}
