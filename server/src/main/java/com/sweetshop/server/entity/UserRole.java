package com.sweetshop.server.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;
//@Getter
//@AllArgsConstructor
public enum UserRole {
    ADMIN(EnumSet.of(
            UserAuthority.CREATE_SWEET,
            UserAuthority.UPDATE_SWEET,
            UserAuthority.DELETE_SWEET,
            UserAuthority.UPDATE_INVENTORY,
            UserAuthority.RETRIEVE_SWEET,
            UserAuthority.PURCHASE_SWEET
    )),
    USER(EnumSet.of(
            UserAuthority.RETRIEVE_SWEET,
            UserAuthority.PURCHASE_SWEET
    ));

    private final Set<UserAuthority> authorities;
    UserRole(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }
    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }
    public boolean hasAuthority(UserAuthority authority) {
        return authorities.contains(authority);
    }
}
