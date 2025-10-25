package com.campushub.service;


import com.campushub.dto.HubUserDto;
import com.campushub.model.HubUser;
import com.campushub.repository.HubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        user.setAge(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        HubUser newUser = userRepository.save(user);

        HubUserDto userResponse = new HubUserDto();
        userResponse.setId(newUser.getId());
        userResponse.setAge(newUser.getAge());
        userResponse.setName(newUser.getName());
        userResponse.setPassword(newUser.getPassword());
        userResponse.setEmail(newUser.getEmail());
        return userResponse;
    }
}
