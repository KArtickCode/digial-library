package com.example.minor_project1.dtos;

import com.example.minor_project1.models.Admin;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateAdminRequest {

    @NotBlank
    private String name;

    private String username;
    private String password;

    public Admin convertTo(){

        Admin admin = new Admin();
        admin.setName(this.name);

        return admin;
    }
}
