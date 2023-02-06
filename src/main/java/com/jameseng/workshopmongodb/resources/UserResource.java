package com.jameseng.workshopmongodb.resources;

import com.jameseng.workshopmongodb.domain.User;
import com.jameseng.workshopmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    // @RequestMapping(method=RequestMethod.GET)
    /*@GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = new ArrayList<>();
        User maria = new User(1L, "Maria Brown", "maria@gmail.com");
        User alex = new User(2L, "Alex Green", "alex@gmail.com");
        list.addAll(Arrays.asList(maria, alex));
        return ResponseEntity.ok().body(list);
    }*/

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }


}