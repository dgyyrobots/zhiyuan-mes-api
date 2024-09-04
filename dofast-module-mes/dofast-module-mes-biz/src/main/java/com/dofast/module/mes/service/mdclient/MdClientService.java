package com.dofast.module.mes.service.mdclient;

import java.util.*;
import javax.validation.*;
import com.dofast.module.mes.controller.admin.mdclient.vo.*;
import com.dofast.module.mes.dal.dataobject.mdclient.MdClientDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 客户 Service 接口
 *
 * @author 芋道源码
 */
public interface MdClientService {
    public String checkClientCodeUnique(MdClientBaseVO mdClient);

    public String checkClientNameUnique(MdClientBaseVO mdClient);

    public String checkClientNickUnique(MdClientBaseVO mdClient);
    /**
     * 创建客户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMdClient(@Valid MdClientCreateReqVO createReqVO);

    /**
     * 更新客户
     *
     * @param updateReqVO 更新信息
     */
    void updateMdClient(@Valid MdClientUpdateReqVO updateReqVO);

    /**
     * 删除客户
     *
     * @param id 编号
     */
    void deleteMdClient(Long id);

    /**
     * 获得客户
     *
     * @param id 编号
     * @return 客户
     */
    MdClientDO getMdClient(Long id);

    /**
     * 获得客户列表
     *
     * @param ids 编号
     * @return 客户列表
     */
    List<MdClientDO> getMdClientList(Collection<Long> ids);

    /**
     * 获得客户分页
     *
     * @param pageReqVO 分页查询
     * @return 客户分页
     */
    PageResult<MdClientDO> getMdClientPage(MdClientPageReqVO pageReqVO);

    /**
     * 获得客户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 客户列表
     */
    List<MdClientDO> getMdClientList(MdClientExportReqVO exportReqVO);

}
