package com.GiangTruong.LearningLMS.center.dto.Login;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class LoginReq {
    private String username;
    private String password;
}
