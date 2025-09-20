package com.sweetshop.server.controller;

import com.sweetshop.server.dto.sweet.request.CreateSweetRequest;
import com.sweetshop.server.dto.sweet.request.UpdateSweetInventoryRequest;
import com.sweetshop.server.dto.sweet.request.UpdateSweetRequest;
import com.sweetshop.server.dto.sweet.response.SweetDashboardResponse;
import com.sweetshop.server.dto.sweet.response.SweetResponse;
import com.sweetshop.server.service.SweetService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/sweets")
@RequiredArgsConstructor
public class SweetController {
    private final SweetService sweetService;
    @PostMapping("")
    public ResponseEntity<SweetResponse> createSweet(@RequestBody CreateSweetRequest request){
        SweetResponse response=sweetService.createSweet(request);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SweetResponse> updateSweet(@PathVariable @NonNull Long id, @RequestBody UpdateSweetRequest request){
        SweetResponse response=sweetService.updateSweet(request,id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSweet(@PathVariable @NonNull Long id){
        sweetService.deleteSweet(id);
        return ResponseEntity.ok("Sweet deleted successfully");
    }
    @GetMapping("")
    public ResponseEntity<SweetDashboardResponse> getAllSweets(){
        return ResponseEntity.ok(sweetService.loadDashboard());
    }
    @GetMapping("/search")
    public ResponseEntity<Set<SweetResponse>> searchSweets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        Set<SweetResponse> results = sweetService.searchSweets(name, category, minPrice, maxPrice);
        return ResponseEntity.ok(results);
    }
    @PostMapping("/{id}/purchase")
    public ResponseEntity<SweetResponse> purchaseSweet(@PathVariable @NonNull Long id,@RequestBody @NonNull UpdateSweetInventoryRequest request){
        return ResponseEntity.ok(sweetService.updateInventory(id,request.getStock(),1));

    }
    @PostMapping("/{id}/restock")
    public ResponseEntity<SweetResponse> restockSweet(@PathVariable @NonNull Long id,@RequestBody @NonNull UpdateSweetInventoryRequest request){
        return ResponseEntity.ok(sweetService.updateInventory(id,request.getStock(),0));
    }
}
