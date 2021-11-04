package com.smoothstack.utopia.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.utopia.user.entity.UserRole;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, Integer> {

}
