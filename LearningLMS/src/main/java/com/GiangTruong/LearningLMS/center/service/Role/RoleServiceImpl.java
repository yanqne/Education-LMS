package com.GiangTruong.LearningLMS.center.service.Role;

import com.GiangTruong.LearningLMS.center.config.BadRequestException;
import com.GiangTruong.LearningLMS.center.dto.Role.RoleReq;
import com.GiangTruong.LearningLMS.center.dto.Role.RoleRes;
import com.GiangTruong.LearningLMS.center.entity.Role;
import com.GiangTruong.LearningLMS.center.mapper.RoleMapper;
import com.GiangTruong.LearningLMS.center.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleRes create(RoleReq req) {

        // ❌ tránh trùng role
        roleRepository.findByName(req.getName())
                .ifPresent(r -> {
                    throw new BadRequestException("Role already exists");
                });

        Role role = roleMapper.toEntity(req);

        return roleMapper.toResponse(roleRepository.save(role));
    }

    @Override
    public List<RoleRes> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponse)
                .toList();
    }
}
