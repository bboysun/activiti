package com.darryl.activiti.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.darryl.activiti.business.UserInfoService;
import com.darryl.activiti.entity.UserInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/getFirstUserInfo")
    public UserInfoBean getFirstUserInfo() {
        return new UserInfoBean(1L, "小明", 20, "画画", "该学生在画画方面有一定天赋", 89L);
    }

    @PostMapping("/getInfoListPage")
    public IPage<UserInfoBean> getInfoListPage(Integer currentPage, Integer pageSize){
        //需要在Config配置类中配置分页插件
        IPage<UserInfoBean> page = new Page<>();
        page.setCurrent(currentPage); //当前页
        page.setSize(pageSize);    //每页条数
        page = userInfoService.page(page);
        return page;
    }

}
