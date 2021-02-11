package com.example.springboot.service;

import com.example.springboot.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role findRoleByRoleName(String role);
    List<Role> getAllRoles();
}
