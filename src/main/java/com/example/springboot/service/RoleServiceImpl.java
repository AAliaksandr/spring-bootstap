package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.dao.RoleDao;
import com.example.springboot.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

//    @Override
//    public Role findRoleByRoleName(String[] role) {
//        Role roles = null;
//        for (String s : role) {
//            roles = roleDao.findRoleByRoleName(s);
//        }
//        return roles;
//    }
    @Override
    public Role findRoleByRoleName(String role) {

        return roleDao.findRoleByRoleName(role);
    }

    @Override
    public List<Role> getAllRoles() {
       return roleDao.getAllRoles();
    }
}
