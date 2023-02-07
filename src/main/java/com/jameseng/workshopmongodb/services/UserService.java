package com.jameseng.workshopmongodb.services;

import com.jameseng.workshopmongodb.domain.User;
import com.jameseng.workshopmongodb.dto.UserDTO;
import com.jameseng.workshopmongodb.repositories.UserRepository;
import com.jameseng.workshopmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /*public List<User> findAll() {
        return userRepository.findAll();
    }*/

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> user = userRepository.findAll();
        return user.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "UserService/Entity not found. Id = " + id));
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO insert(UserDTO userDto) {
        User user = new User();
        copyDtoToEntity(userDto, user);
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO update(String id, UserDTO userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "UserService/Entity not found. Id = " + id));
        copyDtoToEntity(userDto, user);
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public void delete(String id) {
        userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "UserService/Entity not found. Id = " + id));
        userRepository.deleteById(id);
    }

    private void copyDtoToEntity(UserDTO userDto, User user) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
    }
}
