package com.example.springboot.dao;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;

import java.util.List;

public interface RoleDao {
    Role findRoleByRoleName(String role);
    List<Role> getAllRoles();
}
