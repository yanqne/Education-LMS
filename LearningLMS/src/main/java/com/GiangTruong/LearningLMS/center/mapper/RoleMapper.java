package com.GiangTruong.LearningLMS.center.mapper;

import com.GiangTruong.LearningLMS.center.dto.Role.RoleReq;
import com.GiangTruong.LearningLMS.center.dto.Role.RoleRes;
import com.GiangTruong.LearningLMS.center.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role toEntity(RoleReq req){
        Role entity = new Role();
        entity.setName(req.getName());
        return entity;
    }
    public RoleRes toResponse(Role entity){
        RoleRes res = new RoleRes();
        res.setId(entity.getId());
        res.setName(entity.getName());
        return res;
    }
}
