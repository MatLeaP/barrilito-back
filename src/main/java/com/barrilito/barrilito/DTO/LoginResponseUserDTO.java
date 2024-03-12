package com.barrilito.barrilito.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseUserDTO {
    
    private String userName;
    private List<String> roles;
}
