package com.dofast.module.mes.service.mdclient;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.mdclient.vo.*;
import com.dofast.module.mes.dal.dataobject.mdclient.MdClientDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mdclient.MdClientConvert;
import com.dofast.module.mes.dal.mysql.mdclient.MdClientMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 客户 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdClientServiceImpl implements MdClientService {

    @Resource
    private MdClientMapper mdClientMapper;

    @Override
    public String checkClientCodeUnique(MdClientBaseVO mdClient) {
        MdClientDO client = mdClientMapper.checkClientCodeUnique(mdClient);
        Long clientId = mdClient.getId()==null?-1L:mdClient.getId();
        if(StrUtils.isNotNull(client) && client.getId().longValue() !=clientId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkClientNameUnique(MdClientBaseVO mdClient) {
        MdClientDO client = mdClientMapper.checkClientNameUnique(mdClient);
        Long clientId = mdClient.getId()==null?-1L:mdClient.getId();
        if(StrUtils.isNotNull(client) && client.getId().longValue() !=clientId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public String checkClientNickUnique(MdClientBaseVO mdClient) {
        MdClientDO client = mdClientMapper.checkClientNickUnique(mdClient);
        Long clientId = mdClient.getId()==null?-1L:mdClient.getId();
        if(StrUtils.isNotNull(client) && client.getId().longValue() !=clientId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createMdClient(MdClientCreateReqVO createReqVO) {
        // 插入
        MdClientDO mdClient = MdClientConvert.INSTANCE.convert(createReqVO);
        mdClientMapper.insert(mdClient);
        // 返回
        return mdClient.getId();
    }

    @Override
    public void updateMdClient(MdClientUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdClientExists(updateReqVO.getId());
        // 更新
        MdClientDO updateObj = MdClientConvert.INSTANCE.convert(updateReqVO);
        mdClientMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdClient(Long id) {
        // 校验存在
        validateMdClientExists(id);
        // 删除
        mdClientMapper.deleteById(id);
    }

    private void validateMdClientExists(Long id) {
        if (mdClientMapper.selectById(id) == null) {
            throw exception(MD_CLIENT_NOT_EXISTS);
        }
    }

    @Override
    public MdClientDO getMdClient(Long id) {
        return mdClientMapper.selectById(id);
    }

    @Override
    public List<MdClientDO> getMdClientList(Collection<Long> ids) {
        return mdClientMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdClientDO> getMdClientPage(MdClientPageReqVO pageReqVO) {
        return mdClientMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdClientDO> getMdClientList(MdClientExportReqVO exportReqVO) {
        return mdClientMapper.selectList(exportReqVO);
    }

}
