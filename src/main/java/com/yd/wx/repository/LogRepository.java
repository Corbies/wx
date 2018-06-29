package com.yd.wx.repository;

import com.yd.wx.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wuyd
 * @date 2018/06/27
 */
public interface LogRepository extends JpaRepository<Log, Long>  {
}
