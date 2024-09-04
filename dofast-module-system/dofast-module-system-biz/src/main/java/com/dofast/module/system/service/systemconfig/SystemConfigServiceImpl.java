package com.dofast.module.system.service.systemconfig;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONParser;
import com.dofast.module.system.dal.mysql.systemconfig.SystemConfigMapper;
import com.dofast.module.system.enums.social.SocialTypeEnum;
import com.fasterxml.jackson.databind.JsonNode;
import me.zhyd.oauth.config.AuthConfig;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.system.controller.admin.systemconfig.vo.*;
import com.dofast.module.system.dal.dataobject.systemconfig.SystemConfigDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.system.convert.systemconfig.SystemConfigConvert;
import com.dofast.module.system.dal.mysql.systemconfig.SystemConfigMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.system.enums.ErrorCodeConstants.*;

/**
 * 参数配置 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class SystemConfigServiceImpl implements SystemConfigService {

    @Resource
    private SystemConfigMapper systemConfigMapper;

    @Override
    public Integer createConfig(SystemConfigCreateReqVO createReqVO) {
        // 插入
        SystemConfigDO config = SystemConfigConvert.INSTANCE.convert(createReqVO);
        systemConfigMapper.insert(config);
        // 返回
        return config.getId();
    }

    @Override
    public void createAllConfig(Collection<SystemConfigDO> systemConfigDOS) {
        for (SystemConfigDO systemConfigDO : systemConfigDOS) {
            // 校验存在
            validateConfigExistsByCreate(systemConfigDO.getConfigKey());
        }
        systemConfigMapper.inserAllConfig(systemConfigDOS);
    }

    private void validateConfigExistsByCreate(String configKey) {
        if (systemConfigMapper.selectOneByConfigKey(configKey) != null) {
            if ("0".equals(systemConfigMapper.selectOneByConfigKey(configKey).getDeleted())){
                throw exception(CONFIG_IS_EXISTS);
            }
        }

    }

    @Override
    public void updateConfig(SystemConfigUpdateReqVO updateReqVO) {
        // 校验存在
        validateConfigExists(updateReqVO.getId());
        // 更新
        SystemConfigDO updateObj = SystemConfigConvert.INSTANCE.convert(updateReqVO);
        systemConfigMapper.updateById(updateObj);
    }

    @Override
    public void updateAllConfig(Collection<SystemConfigDO> updateReqVO) {
        for (SystemConfigDO systemConfigDO : updateReqVO) {
            // 校验存在
            validateConfigExists(systemConfigDO.getId());
        }
        systemConfigMapper.updateAllConfig(updateReqVO);
    }

    @Override
    public void deleteConfig(Integer id) {
        // 校验存在
        validateConfigExists(id);
        // 删除
        systemConfigMapper.deleteById(id);
    }

    private void validateConfigExists(Integer id) {
        if (systemConfigMapper.selectById(id) == null) {
            throw exception(CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public SystemConfigDO getConfig(Integer id) {
        return systemConfigMapper.selectById(id);
    }

    @Override
    public SystemConfigDO getConfigByKey(String key) {
        return systemConfigMapper.selectOne(SystemConfigDO::getConfigKey, key);
    }

    @Override
    public List<SystemConfigDO> getConfigList(Collection<Integer> ids) {
        return systemConfigMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SystemConfigDO> getConfigPage(SystemConfigPageReqVO pageReqVO) {
        return systemConfigMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SystemConfigDO> getConfigList(SystemConfigExportReqVO exportReqVO) {
        return systemConfigMapper.selectList(exportReqVO);
    }

    @Override
    public AuthConfig getAuthConfig(String configKey) {
        SystemConfigExportReqVO systemConfigExportReqVO = new SystemConfigExportReqVO();
        systemConfigExportReqVO.setConfigKey(configKey);
        List<SystemConfigDO> list = systemConfigMapper.selectList(systemConfigExportReqVO);
        if (list == null || list.size() == 0){
            throw exception(CONFIG_NOT_EXISTS);
        }
        JSONObject jsonObject = new JSONObject(list.get(0).getValue());
        AuthConfig authConfig = new AuthConfig();
        String clientId, clientSecret, agentId, appid, secret;
        switch (configKey) {
            case "DINGTALK":
                clientId = jsonObject.getStr("client-id");
                clientSecret = jsonObject.getStr("client-secret");
                authConfig.setClientId(clientId);
                authConfig.setClientSecret(clientSecret);
                authConfig.setIgnoreCheckRedirectUri(true);
                break;
            case "WECHAT_ENTERPRISE":
                clientId = jsonObject.getStr("client-id");
                clientSecret = jsonObject.getStr("client-secret");
                agentId = jsonObject.getStr("agent-id:");
                authConfig.setClientId(clientId);
                authConfig.setClientSecret(clientSecret);
                authConfig.setIgnoreCheckRedirectUri(true);
                authConfig.setAgentId(agentId);
                break;
            case "WECHAT_MINI_APP":
                appid = jsonObject.getStr("client-id");
                secret = jsonObject.getStr("client-id");
                break;
            default:
                break;
        }
        return authConfig;
    }

    @Override
    public SystemConfigDO getConfigByConfigKey(String configKey) {
        return systemConfigMapper.selectOneByConfigKey(configKey);
    }

}
