package com.sweetshop.server.dto.sweet.response;

import com.sweetshop.server.dto.user.response.UserResponse;
import com.sweetshop.server.entity.Sweet;
import com.sweetshop.server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SweetResponse {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer stockCount;
    public static SweetResponse toSweetResponse(Sweet sweet){
        return SweetResponse.builder()
                .id(sweet.getId())
                .name(sweet.getName())
                .category(sweet.getCategory())
                .price(sweet.getPrice())
                .stockCount(sweet.getStockCount())
                .build();
    }
}
