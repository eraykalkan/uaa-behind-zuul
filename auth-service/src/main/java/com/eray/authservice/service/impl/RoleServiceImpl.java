package com.eray.authservice.service.impl;

import com.eray.authservice.entity.Role;
import com.eray.authservice.repository.RoleRepository;
import com.eray.authservice.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository=roleRepository;
    }

    @Override
    @Transactional
    public Role createRole(String roleName) {
        Role role;
        try {
            /*existingRole=Optional
                    .ofNullable(roleRepository.findById(role.getId()))
                    .map(r->r.get())
                    .orElse(new Role());*/

            role=findRoleByRoleName(roleName);

            if(role == null) {
                role = new Role();
                role.setRoleName(roleName);
                role=roleRepository.save(role);
            }

        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return role;
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        Role role=new Role();
        try {
            role=roleRepository.findRoleByRoleName(roleName);
        } catch (Exception ex) {
            ex.printStackTrace();
            return role;
        }
        return role;
    }

}
