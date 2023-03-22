package ru.az.mz.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class AuthDtoV1 {

    String token;
    @JsonProperty("user")
    UserDtoV1 userDtoV1;

}
