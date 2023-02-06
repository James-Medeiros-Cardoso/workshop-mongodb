package com.jameseng.workshopmongodb.services;

import com.jameseng.workshopmongodb.domain.User;
import com.jameseng.workshopmongodb.dto.UserDTO;
import com.jameseng.workshopmongodb.repositories.UserRepository;
import com.jameseng.workshopmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /*public List<User> findAll() {
        return userRepository.findAll();
    }*/

    public List<UserDTO> findAll() {
        List<User> user = userRepository.findAll();
        return user.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    public UserDTO findById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "UserService/Entity not found. Id = " + id));
        return new UserDTO(user);
    }
}
