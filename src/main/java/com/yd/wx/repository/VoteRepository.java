package com.yd.wx.repository;

import com.yd.wx.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wuyd
 * @date 2018/06/27
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {

    /**
     * 查询一条
     * @param  name 用户名
     * @return 返回查询的一条
     */
    Vote findFirst1ByName(String name);
}
