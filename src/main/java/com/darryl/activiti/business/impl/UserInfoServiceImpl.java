package com.darryl.activiti.business.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darryl.activiti.business.UserInfoService;
import com.darryl.activiti.dao.UserInfoDao;
import com.darryl.activiti.entity.UserInfoBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Darryl
 * @Description: user info service implments
 * @Date: created in 2020/2/7 22:35
 */
@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoBean> implements UserInfoService {

}
