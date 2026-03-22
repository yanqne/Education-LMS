package com.GiangTruong.LearningLMS.center.dto.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserReq {
    private String username;
    private String password;
    private Long roleId;
}
