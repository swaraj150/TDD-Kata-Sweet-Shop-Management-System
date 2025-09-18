package com.sweetshop.server.service;

import com.sweetshop.server.dto.sweet.request.CreateSweetRequest;
import com.sweetshop.server.dto.sweet.request.UpdateSweetRequest;
import com.sweetshop.server.dto.sweet.response.SweetResponse;
import com.sweetshop.server.dto.user.response.UserResponse;
import com.sweetshop.server.entity.Sweet;
import com.sweetshop.server.entity.UserAuthority;
import com.sweetshop.server.exception.UnauthorizedAccessException;
import com.sweetshop.server.repository.SweetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
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
            throw new UnauthorizedAccessException("User does not have the required authority");
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
            throw new UnauthorizedAccessException("User does not have the required authority");
        }
        sweetRepository.deleteById(id);
    }

    public void purchaseSweet(Long id){
        UserResponse user=userService.loadCurrentUser();
        if(!user.getRole().hasAuthority(UserAuthority.PURCHASE_SWEET)){
            throw new UnauthorizedAccessException("User does not have the required authority");
        }
        Sweet sweet=loadSweetById(id);
        if(sweet.getStockCount()>0){
            sweet.setStockCount(sweet.getStockCount()-1);
            sweetRepository.save(sweet);
        }else{
            throw new IllegalStateException("No sweets left");
        }
    }
    public void restockSweet(Long id,Integer stock){
        UserResponse user=userService.loadCurrentUser();
        if(!user.getRole().hasAuthority(UserAuthority.UPDATE_INVENTORY)){
            throw new UnauthorizedAccessException("User does not have the required authority");
        }
        sweetRepository.updateStockCount(id,stock);
    }

    public Set<SweetResponse> loadAllSweets(){
        return sweetRepository.findAll()
                .stream()
                .map(SweetResponse::toSweetResponse)
                .collect(Collectors.toSet());
    }
    public Set<SweetResponse> loadSweetsByName(String name){
        return sweetRepository.findByName(name)
                .stream()
                .map(SweetResponse::toSweetResponse)
                .collect(Collectors.toSet());
    }
    public Set<SweetResponse> loadSweetsByCategory(String category){
        return sweetRepository.findByCategory(category)
                .stream()
                .map(SweetResponse::toSweetResponse)
                .collect(Collectors.toSet());
    }
    public Set<SweetResponse> loadSweetsByPriceRange(Double p1,Double p2){
        return sweetRepository.findByPriceRange(p1,p2)
                .stream()
                .map(SweetResponse::toSweetResponse)
                .collect(Collectors.toSet());
    }



}
