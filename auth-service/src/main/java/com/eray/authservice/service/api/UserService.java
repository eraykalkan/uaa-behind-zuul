package com.eray.authservice.service.api;

import com.eray.authservice.dto.user.CreateUserDTO;
import com.eray.authservice.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    CreateUserDTO createUser(CreateUserDTO createUserDTO);
    User findUserByUserName(String userName);

}
