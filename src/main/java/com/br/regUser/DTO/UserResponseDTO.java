package com.br.regUser.DTO;

import com.br.regUser.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class UserResponseDTO {
    private Long cdUser;
    private String name;
    private String image;
    private LocalDate birthday;
    public UserResponseDTO(User user){
        this.name = user.getName();
        this.image = user.getImage();
        this.cdUser = user.getCdUser();
        this.birthday = user.getBirthday();
    }
}
