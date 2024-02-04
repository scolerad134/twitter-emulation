package com.example.twitter.models.dto;


import lombok.Data;


@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDto {

    private boolean success;
    private String challenge_ts;
    private String hostname;
}
