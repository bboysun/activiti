package com.darryl.activiti.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.darryl.activiti.business.UserInfoService;
import com.darryl.activiti.entity.UserInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Darryl
 * @Description: user info controller
 * @Date: created in 2020/2/7 22:39
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUserInfo")
    public UserInfoBean getUserInfo(Long id) {
        UserInfoBean record = userInfoService.getById(id);
        System.out.println(record.getName());
        return record;
    }

    @GetMapping("/getInfoListPage")
    public IPage<UserInfoBean> getInfoListPage(){
        //需要在Config配置类中配置分页插件
        IPage<UserInfoBean> page = new Page<>();
        page.setCurrent(5); //当前页
        page.setSize(2);    //每页条数
        page = userInfoService.page(page);
        return page;
    }

}
