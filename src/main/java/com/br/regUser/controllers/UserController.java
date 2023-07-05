package com.br.regUser.controllers;

import com.br.regUser.DTO.UserRequestDTO;
import com.br.regUser.DTO.UserResponseDTO;
import com.br.regUser.entity.User;
import com.br.regUser.repository.UserRepository;
import com.br.regUser.services.AmazonS3Service;
import com.br.regUser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AmazonS3Service amazonS3Service;

    @PostMapping("/new")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<User> newUser(@ModelAttribute final UserRequestDTO userRequestDTO)  {

        String imagePath = amazonS3Service.uploadFileToS3(userRequestDTO.getImage());
        User user = new User(userRequestDTO, imagePath);
        userRepository.save(user);

        return ResponseEntity.ok(user);

    }

    @PutMapping("/{cdUser}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<User> updateUserById(@PathVariable final Long cdUser, @ModelAttribute final UserRequestDTO userRequestDTO) {
        User user = userService.updateUser(cdUser, userRequestDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{cdUser}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void deleteFoodById(@PathVariable final Long cdUser){
        User user = userRepository.findById(cdUser).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with code: " + cdUser)
        );

        if(user.getImage() != null){
            amazonS3Service.deleteImageFromS3(user.getImage());
        }

        userRepository.delete(user);
    }
    @GetMapping("/list")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<UserResponseDTO> getAllUsers(){
        List<UserResponseDTO> userList = userRepository.findAll().stream().map(UserResponseDTO::new).toList();

        return userList;
    }
}
