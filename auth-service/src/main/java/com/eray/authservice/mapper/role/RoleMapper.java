package com.eray.authservice.mapper.role;

import com.eray.authservice.dto.role.RoleDTO;
import com.eray.authservice.entity.Role;

public class RoleMapper {

    public RoleDTO role2RoleDTO(Role role) {
        RoleDTO roleDTO=new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRoleName(role.getRoleName());
        return roleDTO;
    }

    public Role roleDTO2Role(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setRoleName(roleDTO.getRoleName());
        role.setId(role.getId());
        return role;
    }

}
