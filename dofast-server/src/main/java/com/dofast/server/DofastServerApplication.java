package com.dofast.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;






/**
 * 项目的启动类
 *
 * 如果你碰到启动的问题，请认真阅读 https://doc.huizhizao.vip/quick-start/ 文章
 * 如果你碰到启动的问题，请认真阅读 https://doc.huizhizao.vip/quick-start/ 文章
 * 如果你碰到启动的问题，请认真阅读 https://doc.huizhizao.vip/quick-start/ 文章
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${dofast.info.base-package}
@EnableAsync(proxyTargetClass = true)
//@EnableCaching
//@SpringBootApplication(scanBasePackages = {"${dofast.info.base-package}.server", "${dofast.info.base-package}.module"})
@SpringBootApplication(scanBasePackages = {"${dofast.info.base-package}.module"},exclude= {DataSourceAutoConfiguration.class})
@ComponentScan("com.dofast")
//@SpringBootApplication(scanBasePackages = {"${dofast.info.base-package}"})
public class DofastServerApplication {

    public static void main(String[] args) {
        // 如果你碰到启动的问题，请认真阅读 https://doc.huizhizao.vip/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://doc.huizhizao.vip/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://doc.huizhizao.vip/quick-start/ 文章

        SpringApplication.run(DofastServerApplication.class, args);
//        new SpringApplicationBuilder(DofastServerApplication.class)
//                .applicationStartup(new BufferingApplicationStartup(20480))
//                .run(args);

        // 如果你碰到启动的问题，请认真阅读 https://doc.huizhizao.vip/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://doc.huizhizao.vip/quick-start/ 文章
        // 如果你碰到启动的问题，请认真阅读 https://doc.huizhizao.vip/quick-start/ 文章
    }

}