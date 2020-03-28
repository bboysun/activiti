package com.darryl.activiti.spring.scope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Auther: Darryl
 * @Description: 老板有很多火车
 * @Date: created in 2020/3/18 22:24
 */
@Component
@Scope("prototype")
public class Boss {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
