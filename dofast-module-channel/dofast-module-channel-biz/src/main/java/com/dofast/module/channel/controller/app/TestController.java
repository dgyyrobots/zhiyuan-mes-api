package com.dofast.module.channel.controller.app;


import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.module.member.api.user.MemberUserApi;
import javax.annotation.security.PermitAll;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    private MemberUserApi memberUserApi;

    @RequestMapping("/pass")
    @PermitAll
    public String password() {
        String pass = memberUserApi.encodePass("123456");
        return pass;

    }
}
