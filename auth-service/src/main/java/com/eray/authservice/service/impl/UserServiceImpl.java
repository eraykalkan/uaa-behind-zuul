package com.eray.authservice.service.impl;

import com.eray.authservice.dto.user.CreateUserDTO;
import com.eray.authservice.entity.User;
import com.eray.authservice.mapper.role.RoleMapper;
import com.eray.authservice.mapper.user.UserMapper;
import com.eray.authservice.repository.UserRepository;
import com.eray.authservice.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private RoleMapper roleMapper=new RoleMapper();
    private UserMapper userMapper=new UserMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public CreateUserDTO createUser(CreateUserDTO createUserDTO) {

        CreateUserDTO createdUser = new CreateUserDTO();

        User user=findUserByUserName(createUserDTO.getUserName());

        if(user==null) {
            user = new User();
            user.setUserName(createUserDTO.getUserName());
            user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
            user.setEnabled(true);

        }

        user.setRoles(createUserDTO.getRoleList()
                .stream()
                .map(roleDTO -> roleMapper.roleDTO2Role(roleDTO))
                .collect(Collectors.toList())
        );
        user=userRepository.save(user);

        createdUser=userMapper.user2CreateUserDTO(user);

        return createdUser;
    }

    @Override
    public User findUserByUserName(String userName) {
        User user=new User();
        try {
            user=userRepository.findByUserName(userName);
        }catch (Exception ex) {
            ex.printStackTrace();
            return user;
        }

        return user;
    }
}
