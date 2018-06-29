package com.yd.wx.repository;

import com.yd.wx.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wuyd
 * @date 2018/06/28
 */
public interface FileRepository extends JpaRepository<File, Long> {


    /**
     * 查询一个微信文件
     * @param type
     * @return file
     * */
    List<File> findAllByType(String type);
}
