package com.campushub.controllers;


import com.campushub.dto.HubUserDto;
import com.campushub.exceptions.HubUserNotFoundException;
import com.campushub.model.HubUser;
import com.campushub.service.HubUserService;
import org.apache.coyote.Response;
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
    public ResponseEntity<List<HubUserDto>> getUsers() {
        return new ResponseEntity<>(userService.getAllHubUser(), HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<HubUserDto> userDetail(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("user/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HubUserDto> createUser(@RequestBody HubUserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("user/{id}/update")
    public ResponseEntity<HubUserDto> updateUser(@RequestBody HubUserDto userDto,
                                              @PathVariable("id") int userId){

        HubUserDto res = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("user/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId){
        userService.deleteUserId(userId);
        return new ResponseEntity<>("User delete", HttpStatus.OK);
    }
}
