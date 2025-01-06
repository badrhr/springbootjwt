package com.xproce.authapi.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
    private String email;
    private String password;
}