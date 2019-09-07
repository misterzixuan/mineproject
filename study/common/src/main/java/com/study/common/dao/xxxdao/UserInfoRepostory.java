package com.study.common.dao.xxxdao;

import com.study.common.pojo.xxxdo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepostory extends JpaRepository<UserInfo,Integer> {
}
