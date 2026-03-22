package com.GiangTruong.LearningLMS.center.service.User;

import com.GiangTruong.LearningLMS.center.dto.User.UserReq;
import com.GiangTruong.LearningLMS.center.dto.User.UserRes;
import com.GiangTruong.LearningLMS.center.entity.Role;
import com.GiangTruong.LearningLMS.center.entity.User;
import com.GiangTruong.LearningLMS.center.mapper.UserMapper;
import com.GiangTruong.LearningLMS.center.repository.RoleRepository;
import com.GiangTruong.LearningLMS.center.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRes create(UserReq req) {

        // ❌ username trùng
        userRepository.findByUsername(req.getUsername())
                .ifPresent(u -> {
                    throw new RuntimeException("Username already exists");
                });

        // 🔥 lấy role
        Role role = roleRepository.findById(req.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = userMapper.toEntity(req);
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        // ⚠️ TEMP: chưa hash (JWT sẽ làm sau)
        user.setRole(role);

        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public List<UserRes> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }
}
