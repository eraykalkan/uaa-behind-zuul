package com.eray.authservice.core.init;

import com.eray.authservice.dto.user.CreateUserDTO;
import com.eray.authservice.entity.Role;
import com.eray.authservice.mapper.role.RoleMapper;
import com.eray.authservice.service.api.RoleService;
import com.eray.authservice.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class InitialUserDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    private final RoleService roleService;

    private boolean isAlreadyLoaded;

    @Autowired
    public InitialUserDataLoader(UserService userService, RoleService roleService) {
        this.userService=userService;
        this.roleService=roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if(isAlreadyLoaded)
            return;

        RoleMapper roleMapper=new RoleMapper();

        Role userRole=roleService.createRole("ROLE_USER");
        Role adminRole=roleService.createRole("ROLE_ADMIN");

        CreateUserDTO user=new CreateUserDTO();
        user.setUserName("user");
        user.setPassword("password");
        user.setRoleList(Arrays.asList(userRole)
                .stream()
                .map(role -> roleMapper.role2RoleDTO(role))
                .collect(Collectors.toList()));
        user=userService.createUser(user);

        CreateUserDTO admin=new CreateUserDTO();
        admin.setUserName("admin");
        admin.setPassword("password");
        admin.setRoleList(Arrays.asList(adminRole)
                .stream()
                .map(role -> roleMapper.role2RoleDTO(role))
                .collect(Collectors.toList()));
        admin=userService.createUser(admin);

        isAlreadyLoaded=true;
    }
}
