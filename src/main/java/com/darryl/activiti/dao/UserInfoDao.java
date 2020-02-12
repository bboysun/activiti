package com.darryl.activiti.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.darryl.activiti.entity.UserInfoBean;
import org.springframework.stereotype.Component;

/**
 * @Auther: Darryl
 * @Description: user_info table DAO
 * @Date: created in 2020/2/7 22:27
 */
@Component
public interface UserInfoDao extends BaseMapper<UserInfoBean> {

}
