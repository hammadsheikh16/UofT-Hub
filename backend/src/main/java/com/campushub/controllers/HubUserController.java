package com.campushub.controllers;


import com.campushub.dto.HubUserDto;
import com.campushub.model.HubUser;
import com.campushub.service.HubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class HubUserController {

    private HubUserService userService;

    @Autowired
    public HubUserController(HubUserService userService) {
        this.userService = userService;
    }

    @GetMapping("user")
    public ResponseEntity<List<HubUser>> getUsers() {
        List<HubUser> users = new ArrayList<HubUser>();
        users.add(new HubUser(1, "testget", "test", 12, "testpassword"));
        return ResponseEntity.ok(users);
    }

    @GetMapping("user/{id}")
    public HubUser userDetail(@PathVariable int id){
        return new HubUser(id, "return", "return", 10, "password");
    }

    @PostMapping("user/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HubUserDto> createUser(@RequestBody HubUserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("user/{id}/update")
    public ResponseEntity<HubUser> updateUser(@RequestBody HubUser user,
                                              @PathVariable("id") int userId){

        System.out.println(user.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping("user/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId){
        System.out.println(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
