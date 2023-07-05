package com.br.regUser.entity;

import com.br.regUser.DTO.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "user", schema = "public")
@Entity(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cdUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CD_USER", unique = true)
    private Long cdUser;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    public User(UserRequestDTO foodRequestDTO){
        this.name = foodRequestDTO.getName();
        this.image = foodRequestDTO.getImage() == null ?
                "https://static.vecteezy.com/system/resources/previews/005/337/799/original/icon-image-not-found-free-vector.jpg"
                : "C:/Users/thiag/Documents/CARDAPIO_DIGITAL/imagens-cardapio/" + foodRequestDTO.getImage().getOriginalFilename();
        this.birthday = foodRequestDTO.getBirthday();
    }

    public User(UserRequestDTO foodRequestDTO, String filePath){
        this.name = foodRequestDTO.getName();
        this.image = filePath;
        this.birthday = foodRequestDTO.getBirthday();
    }
}
