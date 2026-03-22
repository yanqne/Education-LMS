package com.GiangTruong.LearningLMS.center.service.Role;

import com.GiangTruong.LearningLMS.center.dto.Role.RoleReq;
import com.GiangTruong.LearningLMS.center.dto.Role.RoleRes;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {
    RoleRes create(RoleReq req);

    List<RoleRes> getAll();


}
