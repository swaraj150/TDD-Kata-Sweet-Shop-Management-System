package com.sweetshop.server.dto.sweet.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SweetDashboardResponse {
    Set<SweetResponse> sweets;
    Set<String> categories;
}
