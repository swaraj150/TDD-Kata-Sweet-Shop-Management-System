package com.sweetshop.server.service;

import com.sweetshop.server.dto.sweet.request.CreateSweetRequest;
import com.sweetshop.server.dto.sweet.request.UpdateSweetInventoryRequest;
import com.sweetshop.server.dto.sweet.request.UpdateSweetRequest;
import com.sweetshop.server.dto.sweet.response.SweetDashboardResponse;
import com.sweetshop.server.dto.sweet.response.SweetResponse;
import com.sweetshop.server.dto.user.response.UserResponse;
import com.sweetshop.server.entity.Sweet;
import com.sweetshop.server.entity.UserAuthority;
import com.sweetshop.server.exception.AccessDeniedException;
import com.sweetshop.server.exception.UnauthorizedAccessException;
import com.sweetshop.server.repository.SweetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SweetService {
    private final UserService userService;
    private final SweetRepository sweetRepository;

    public SweetResponse createSweet(CreateSweetRequest request){
        UserResponse user=userService.loadCurrentUser();
        if(!user.getRole().hasAuthority(UserAuthority.CREATE_SWEET)){
            throw new UnauthorizedAccessException("User does not have the required authority");
        }
        Sweet sweet=new Sweet();
        sweet.setName(request.getName());
        sweet.setCategory(request.getCategory());
        sweet.setPrice(request.getPrice());
        sweet.setStockCount(request.getStockCount());
        sweetRepository.save(sweet);
        return SweetResponse.toSweetResponse(sweet);
    }
    public SweetResponse loadSweetResponseById(Long id){
        return SweetResponse.toSweetResponse(loadSweetById(id));
    }

    public Sweet loadSweetById(Long id){
        return sweetRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Sweet Not found"));
    }

    public SweetResponse updateSweet(UpdateSweetRequest request, Long id){
        UserResponse user=userService.loadCurrentUser();
        if(!user.getRole().hasAuthority(UserAuthority.UPDATE_SWEET)){
            throw new AccessDeniedException("User does not have the required authority");
        }
        Sweet sweet=loadSweetById(id);
        Optional.ofNullable(request.getName())
                .ifPresent(sweet::setName);
        Optional.ofNullable(request.getCategory())
                .ifPresent(sweet::setCategory);
        Optional.ofNullable(request.getPrice())
                .ifPresent(sweet::setPrice);
        Optional.ofNullable(request.getStockCount())
                .ifPresent(sweet::setStockCount);

        sweetRepository.save(sweet);
        return SweetResponse.toSweetResponse(sweet);
    }

    public void deleteSweet(Long id){
        UserResponse user=userService.loadCurrentUser();
        if(!user.getRole().hasAuthority(UserAuthority.DELETE_SWEET)){
            throw new AccessDeniedException("User does not have the required authority");
        }
        sweetRepository.deleteById(id);
    }


    public SweetResponse updateInventory(Long id,Integer stock,int mode){
        UserResponse user=userService.loadCurrentUser();
        UserAuthority authority=mode==1?UserAuthority.PURCHASE_SWEET:UserAuthority.UPDATE_INVENTORY;
        if(!user.getRole().hasAuthority(authority)){
            throw new AccessDeniedException("User does not have the required authority");
        }
        Sweet sweet=loadSweetById(id);
        if(sweet.getStockCount()+stock>=0){
            sweet.setStockCount(sweet.getStockCount()+stock);
            sweetRepository.save(sweet);
            return SweetResponse.toSweetResponse(sweet);
        }else{
            throw new IllegalStateException("No sweets left");
        }
    }

    public Set<SweetResponse> loadAllSweets(){
        return sweetRepository.findAll()
                .stream()
                .map(SweetResponse::toSweetResponse)
                .collect(Collectors.toSet());
    }

    public Set<String> loadAllCategories(){
        return sweetRepository.findAll()
                .stream()
                .map(Sweet::getCategory)
                .collect(Collectors.toSet());
    }

    public SweetDashboardResponse loadDashboard(){
        return sweetRepository.findAll()
                .stream()
                .collect(Collectors.teeing(
                        Collectors.mapping(SweetResponse::toSweetResponse, Collectors.toSet()),
                        Collectors.mapping(Sweet::getCategory, Collectors.toSet()),
                        SweetDashboardResponse::new
                ));
    }

    public Set<SweetResponse> searchSweets(String name, String category, Double minPrice, Double maxPrice){
        String nameSearch=(name == null) ? null : "%" + name.toLowerCase() + "%";
        return sweetRepository.search(nameSearch, category, minPrice, maxPrice)
                .stream()
                .map(SweetResponse::toSweetResponse)
                .collect(Collectors.toSet());
    }



}
