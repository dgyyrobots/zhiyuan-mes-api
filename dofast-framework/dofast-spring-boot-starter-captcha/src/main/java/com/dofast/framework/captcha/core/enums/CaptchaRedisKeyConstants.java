package com.dofast.framework.captcha.core.enums;

import com.dofast.framework.redis.core.RedisKeyDefine;
import com.xingyuv.captcha.model.vo.PointVO;

import java.time.Duration;

import static com.dofast.framework.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * 验证码 Redis Key 枚举类
 *
 * @author 芋道源码
 */
public interface CaptchaRedisKeyConstants {

    /*RedisKeyDefine AJ_CAPTCHA_REQ_LIMIT = new RedisKeyDefine("验证码的请求限流",
            "AJ.CAPTCHA.REQ.LIMIT-%s-%s",
            STRING, Integer.class, Duration.ofSeconds(60)); // 例如说：验证失败 5 次，get 接口锁定

    RedisKeyDefine AJ_CAPTCHA_RUNNING = new RedisKeyDefine("验证码的坐标",
            "RUNNING:CAPTCHA:%s", // AbstractCaptchaService.REDIS_CAPTCHA_KEY
            STRING, PointVO.class, Duration.ofSeconds(120)); // {"secretKey":"PP1w2Frr2KEejD2m","x":162,"y":5}*/
    /**
     * 验证码的请求限流
     *
     * KEY 格式：AJ.CAPTCHA.REQ.LIMIT-%s-%s
     * VALUE 数据类型：String // 例如说：验证失败 5 次，get 接口锁定
     * 过期时间：60 秒
     */
    String AJ_CAPTCHA_REQ_LIMIT = "AJ.CAPTCHA.REQ.LIMIT-%s-%s";

    /**
     * 验证码的坐标
     *
     * KEY 格式：RUNNING:CAPTCHA:%s // AbstractCaptchaService.REDIS_CAPTCHA_KEY
     * VALUE 数据类型：String // PointVO.class {"secretKey":"PP1w2Frr2KEejD2m","x":162,"y":5}
     * 过期时间：120 秒
     */
    String AJ_CAPTCHA_RUNNING = "RUNNING:CAPTCHA:%s";

}
