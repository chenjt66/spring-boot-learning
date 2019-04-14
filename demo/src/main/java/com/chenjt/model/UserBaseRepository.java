package com.chenjt.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by chenjianting on 2019/4/15
 **/
@Repository
public interface UserBaseRepository extends JpaRepository<UserBasePO, Long> {
    UserBasePO findByPhoneAndIsActive(String phone, int is_active);
}
