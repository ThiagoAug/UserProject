package com.br.regUser.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String name;
    private MultipartFile image;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    public MultipartFile getImage() {
        return this.image;
    }

    public void setFile(MultipartFile image) {
        this.image = image;
    }
}