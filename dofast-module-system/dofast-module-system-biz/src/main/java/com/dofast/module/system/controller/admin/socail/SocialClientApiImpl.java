package com.dofast.module.system.controller.admin.socail;

import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.dofast.module.system.api.social.SocialClientApi;
import com.dofast.module.system.api.social.dto.SocialWxJsapiSignatureRespDTO;
import com.dofast.module.system.api.social.dto.SocialWxPhoneNumberInfoRespDTO;
import com.dofast.module.system.convert.social.SocialClientConvert;
import com.dofast.module.system.service.social.SocialClientService;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 社交应用的 API 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class SocialClientApiImpl implements SocialClientApi {

    @Resource
    private SocialClientService socialClientService;

    @Override
    public String getAuthorizeUrl(Integer type, Integer userType, String redirectUri) {
        return socialClientService.getAuthorizeUrl(type, userType, redirectUri);
    }

    @Override
    public SocialWxJsapiSignatureRespDTO createWxMpJsapiSignature(Integer userType, String url) {
        WxJsapiSignature signature = socialClientService.createWxMpJsapiSignature(userType, url);
        return SocialClientConvert.INSTANCE.convert(signature);
    }

    @Override
    public SocialWxPhoneNumberInfoRespDTO getWxMaPhoneNumberInfo(Integer userType, String phoneCode) {
        WxMaPhoneNumberInfo info = socialClientService.getWxMaPhoneNumberInfo(userType, phoneCode);
        return SocialClientConvert.INSTANCE.convert(info);
    }

}
