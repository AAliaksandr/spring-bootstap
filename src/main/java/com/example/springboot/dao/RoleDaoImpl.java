package com.example.springboot.dao;

import com.example.springboot.model.User;
import org.springframework.stereotype.Repository;
import com.example.springboot.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Role findRoleByRoleName(String role) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.role = :role", Role.class)
                .setParameter("role", role);
        return query.getSingleResult();
    }
    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("from Role", Role.class)
                .getResultList();
    }
}
