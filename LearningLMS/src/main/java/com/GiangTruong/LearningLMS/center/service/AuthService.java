package com.GiangTruong.LearningLMS.center.service;

import com.GiangTruong.LearningLMS.center.config.BadRequestException;
import com.GiangTruong.LearningLMS.center.config.NotFoundException;
import com.GiangTruong.LearningLMS.center.dto.Login.LoginReq;
import com.GiangTruong.LearningLMS.center.dto.Login.LoginRes;
import com.GiangTruong.LearningLMS.center.entity.User;
import com.GiangTruong.LearningLMS.center.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public LoginRes login(LoginReq req){
        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));
        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())){
            throw new BadRequestException("invalid password");
        }
        String token = jwtService.generateToken(
                user.getUsername(),
                user.getRole().getName()
        );

        LoginRes res = new LoginRes();
        res.setToken(token);
        return res;
    }
}
