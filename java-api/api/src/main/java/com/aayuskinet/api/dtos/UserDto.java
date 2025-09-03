package com.aayuskinet.api.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String email;
    private String displayName;
    private String token;

    public UserDto(String email, String displayName, String token) {
        this.email = email;
        this.displayName = displayName;
        this.token = token;
    }
}
