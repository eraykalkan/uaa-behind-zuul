package com.eray.authservice.service.api;

import com.eray.authservice.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    Role createRole(String roleName);
    Role findRoleByRoleName(String roleName);

}
