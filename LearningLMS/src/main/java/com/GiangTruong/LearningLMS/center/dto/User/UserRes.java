package com.GiangTruong.LearningLMS.center.dto.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRes {
    private Long id;
    private String username;
    private String RoleName;
}
