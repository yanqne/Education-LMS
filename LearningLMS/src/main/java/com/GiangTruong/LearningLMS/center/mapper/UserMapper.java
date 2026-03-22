package com.GiangTruong.LearningLMS.center.mapper;

import com.GiangTruong.LearningLMS.center.dto.User.UserReq;
import com.GiangTruong.LearningLMS.center.dto.User.UserRes;
import com.GiangTruong.LearningLMS.center.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    public User toEntity(UserReq req){
        User entity = new User();
        entity.setUsername(req.getUsername());
        entity.setPassword(req.getPassword());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }
    public UserRes toResponse(User entity){
        UserRes res = new UserRes();
        res.setId(entity.getId());
        res.setUsername(entity.getUsername());
        res.setRoleName(
                entity.getRole() != null ? entity.getRole().getName() : null
        );
        return res;
    }
}
