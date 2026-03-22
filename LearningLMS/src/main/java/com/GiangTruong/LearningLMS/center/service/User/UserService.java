package com.GiangTruong.LearningLMS.center.service.User;

import com.GiangTruong.LearningLMS.center.dto.User.UserReq;
import com.GiangTruong.LearningLMS.center.dto.User.UserRes;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {
    UserRes create(UserReq req);

    List<UserRes> getAll();

}
