package com.dofast.module.system.service.systemconfig;

import java.util.*;
import javax.validation.*;
import com.dofast.module.system.controller.admin.systemconfig.vo.*;
import com.dofast.module.system.dal.dataobject.systemconfig.SystemConfigDO;
import com.dofast.framework.common.pojo.PageResult;
import me.zhyd.oauth.config.AuthConfig;

/**
 * 参数配置 Service 接口
 *
 * @author 惠智造
 */
public interface SystemConfigService {

    /**
     * 创建参数配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createConfig(@Valid SystemConfigCreateReqVO createReqVO);

    /**
     * 批量创建参数配置
     * @param systemConfigDOS  创建信息集合
     */
    void createAllConfig(@Valid Collection<SystemConfigDO> systemConfigDOS);

    /**
     * 更新参数配置
     *
     * @param updateReqVO 更新信息
     */
    void updateConfig(@Valid SystemConfigUpdateReqVO updateReqVO);


    /**
     * 批量更新参数配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAllConfig(@Valid Collection<SystemConfigDO> updateReqVO);

    /**
     * 删除参数配置
     *
     * @param id 编号
     */
    void deleteConfig(Integer id);

    /**
     * 获得参数配置
     *
     * @param id 编号
     * @return 参数配置
     */
    SystemConfigDO getConfig(Integer id);

    /**
     * 获得租户配置
     *
     * @param key 配置
     * @return 租户配置
     */
    SystemConfigDO getConfigByKey(String key);

    /**
     * 获得租户配置列表
     *
     * @param ids 编号
     * @return 参数配置列表
     */
    List<SystemConfigDO> getConfigList(Collection<Integer> ids);

    /**
     * 获得参数配置分页
     *
     * @param pageReqVO 分页查询
     * @return 参数配置分页
     */
    PageResult<SystemConfigDO> getConfigPage(SystemConfigPageReqVO pageReqVO);

    /**
     * 获得参数配置列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 参数配置列表
     */
    List<SystemConfigDO> getConfigList(SystemConfigExportReqVO exportReqVO);

    /**
     * 获取租户三方登录配置
     *
     * @param configKey
     * @return
     */
    AuthConfig getAuthConfig(String configKey);

    SystemConfigDO getConfigByConfigKey(String configKey);
}
