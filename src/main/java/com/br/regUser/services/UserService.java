package com.br.regUser.services;

import com.br.regUser.DTO.UserRequestDTO;
import com.br.regUser.entity.User;
import com.br.regUser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AmazonS3Service amazonS3Service;

    public User updateUser(Long id, final UserRequestDTO userRequestDTO) {

        User user = userRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id)
        );

        if(userRequestDTO.getName().isBlank()){
                user.setName(userRequestDTO.getName());
        }

        if(userRequestDTO.getImage() != null){
            String imagePath = amazonS3Service.uploadFileToS3(userRequestDTO.getImage());
            if(user.getImage() != null){
                amazonS3Service.deleteImageFromS3(user.getImage());
            }

            user.setImage(imagePath);
        }

        if(userRequestDTO.getBirthday() != null){
            user.setBirthday(userRequestDTO.getBirthday());
        }

        userRepository.save(user);
        return user;
    }
}
