package com.dofast.framework.banner.core;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.ClassUtils;

import java.util.concurrent.TimeUnit;

/**
 * 项目启动成功后，提供文档相关的地址
 *
 * @author 芋道源码
 */
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThreadUtil.execute(() -> {
            ThreadUtil.sleep(1, TimeUnit.SECONDS); // 延迟 1 秒，保证输出到结尾
            log.info("\n----------------------------------------------------------\n\t" +
                            "项目启动成功！\n\t" +
                            "接口文档: \t{} \n\t" +
                            "开发文档: \t{} \n\t" +
                            "视频教程: \t{} \n\t" +
                            "源码解析: \t{} \n" +
                            "----------------------------------------------------------",
                    "https://doc.huizhizao.vip/api-doc/",
                    "https://doc.huizhizao.vip",
                    "https://t.zsxq.com/02Yf6M7Qn",
                    "https://t.zsxq.com/02B6ujIee");

            // 数据报表
            if (isNotPresent("com.dofast.module.report.framework.security.config.SecurityConfiguration")) {
                System.out.println("[报表模块 dofast-module-report - 已禁用][参考 https://doc.huizhizao.vip/report/ 开启]");
            }

            // 工作流
            if (isNotPresent("com.dofast.framework.flowable.config.DofastFlowableConfiguration")) {
                System.out.println("[工作流模块 dofast-module-bpm - 已禁用][参考 https://doc.huizhizao.vip/bpm/ 开启]");
            }
            // 微信公众号
            if (isNotPresent("com.dofast.module.mp.framework.mp.config.MpConfiguration")) {
                System.out.println("[微信公众号 dofast-module-mp - 已禁用][参考 https://doc.huizhizao.vip/mp/build/ 开启]");
            }
            // 数据报表
            if (isNotPresent("com.dofast.module.report.framework.security.config.SecurityConfiguration")) {
                System.out.println("[报表模块 dofast-module-report - 已禁用][参考 https://doc.iocoder.cn/report/ 开启]");
            }
            // 工作流
            if (isNotPresent("com.dofast.framework.flowable.config.DofastFlowableConfiguration")) {
                System.out.println("[工作流模块 dofast-module-bpm - 已禁用][参考 https://doc.iocoder.cn/bpm/ 开启]");
            }
            // 微信公众号
            if (isNotPresent("com.dofast.module.mp.framework.mp.config.MpConfiguration")) {
                System.out.println("[微信公众号 dofast-module-mp - 已禁用][参考 https://doc.iocoder.cn/mp/build/ 开启]");
            }
            // 商城系统
            if (isNotPresent("com.dofast.module.trade.framework.web.config.TradeWebConfiguration")) {
                System.out.println("[商城系统 dofast-module-mall - 已禁用][参考 https://doc.iocoder.cn/mall/build/ 开启]");
            }
            // 支付平台
            if (isNotPresent("com.dofast.module.pay.framework.pay.config.PayConfiguration")) {
                System.out.println("[支付系统 dofast-module-pay - 已禁用][参考 https://doc.iocoder.cn/pay/build/ 开启]");
            }
        });
    }


    private static boolean isNotPresent(String className) {
        return !ClassUtils.isPresent(className, ClassUtils.getDefaultClassLoader());
    }

}
