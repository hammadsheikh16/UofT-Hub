package com.campushub.service;


import com.campushub.dto.HubUserDto;
import com.campushub.exceptions.HubUserNotFoundException;
import com.campushub.model.HubUser;
import com.campushub.repository.HubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HubUserService {

    private final HubUserRepository userRepository;

    @Autowired
    public HubUserService(HubUserRepository userRepository){
        this.userRepository = userRepository;
    }


    public HubUserDto createUser(HubUserDto userDto) {
        HubUser user = new HubUser();
        user.setName(userDto.getName());
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAge(userDto.getAge());

        HubUser newUser = userRepository.save(user);

        HubUserDto userResponse = new HubUserDto();
        userResponse.setId(newUser.getId());
        userResponse.setAge(newUser.getAge());
        userResponse.setName(newUser.getName());
        userResponse.setPassword(newUser.getPassword());
        userResponse.setEmail(newUser.getEmail());
        return userResponse;
    }

    public List<HubUserDto> getAllHubUser(){
        List<HubUser> hubUsers = userRepository.findAll();
        return hubUsers.stream().map((u)->mapToDto(u)).collect(Collectors.toList());
    }

    private HubUserDto mapToDto(HubUser user){
        HubUserDto userDto = new HubUserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    private HubUser mapToEntity(HubUserDto userDto){
        HubUser user = new HubUser();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public HubUserDto getUserById(int id){
        HubUser user = userRepository.findById(id)
                .orElseThrow(() -> new HubUserNotFoundException("User could not be found by id"));
        return mapToDto(user);
    }

    public HubUserDto updateUser(HubUserDto userDto, int id){
        HubUser user = userRepository.findById(id)
                .orElseThrow(()-> new HubUserNotFoundException("User to update by id not found"));

        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setProfileImageId(userDto.getProfileImageId());

        HubUser updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }

    public void deleteUserId(int id){
        HubUser user = userRepository
                .findById(id)
                .orElseThrow(() -> new HubUserNotFoundException("User to delete not found by id"));
        userRepository.delete(user);
    }
}
