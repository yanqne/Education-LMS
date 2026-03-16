package com.GiangTruong.LearningLMS.center.repository;

import com.GiangTruong.LearningLMS.center.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
