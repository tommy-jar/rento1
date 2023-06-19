package com.upc.rento1.services;

import com.upc.rento1.entity.Role;

import java.util.List;

public interface IRoleService {
    public void insert(Role role);

    List<Role> list();

}
