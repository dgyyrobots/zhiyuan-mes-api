package com.dofast.module.channel.dian3;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.module.channel.biz.Dian3;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dofast.framework.common.pojo.CommonResult.success;

@RestController
public class Test {
    @Autowired
    Dian3 dian3;

    @RequestMapping("/test/sign")
    public CommonResult<String> testsign(){
        HashMap paramMap = new HashMap();
        paramMap.put("test", "test");
        System.out.println(dian3.init("ds.omni.erp.third.order.query", paramMap).buildSign().doPost());
        String test = dian3.init("ds.omni.erp.third.order.query", paramMap).buildSign().doPost();
        return success(test);
    }

    
}
