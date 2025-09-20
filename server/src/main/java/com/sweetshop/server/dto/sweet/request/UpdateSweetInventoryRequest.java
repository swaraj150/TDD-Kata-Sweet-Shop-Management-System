package com.sweetshop.server.dto.sweet.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateSweetInventoryRequest {
    private Integer stock;
}
