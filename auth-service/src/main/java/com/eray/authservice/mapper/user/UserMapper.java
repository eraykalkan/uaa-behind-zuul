package com.eray.authservice.mapper.user;

import com.eray.authservice.dto.user.CreateUserDTO;
import com.eray.authservice.entity.User;
import com.eray.authservice.mapper.role.RoleMapper;

import java.util.stream.Collectors;

public class UserMapper {

    private RoleMapper roleMapper=new RoleMapper();

    public User createUserDTO2User(CreateUserDTO createUserDTO) {
        return null;
    }

    public CreateUserDTO user2CreateUserDTO(User user) {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUserName(user.getUserName());
        createUserDTO.setPassword(user.getPassword());
        createUserDTO.setRoleList(user.getRoles()
                .stream()
                .map(role -> roleMapper.role2RoleDTO(role))
                .collect(Collectors.toList())
        );

        return createUserDTO;
    }

}
