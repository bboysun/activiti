package com.darryl.activiti.business.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.darryl.activiti.business.UserInfoService;
import com.darryl.activiti.dao.UserInfoDao;
import com.darryl.activiti.entity.UserInfoBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: Darryl
 * @Description: user info service implments
 * @Date: created in 2020/2/7 22:35
 */
@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoBean> implements UserInfoService {


    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    private static final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    public static void main(String[] args) {
        System.out.println(0 & ((1 << (Integer.SIZE - 3)) - 1));
        System.out.println(RUNNING);
        System.out.println(new AtomicInteger(ctlOf(RUNNING, 0)));
        System.out.println(ctl.get());
    }

    private static int ctlOf(int rs, int wc) { return rs | wc; }

}
