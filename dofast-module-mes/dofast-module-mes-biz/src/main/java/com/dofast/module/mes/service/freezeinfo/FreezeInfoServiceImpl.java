package com.dofast.module.mes.service.freezeinfo;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.freezeinfo.vo.*;
import com.dofast.module.mes.dal.dataobject.freezeinfo.FreezeInfoDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.freezeinfo.FreezeInfoConvert;
import com.dofast.module.mes.dal.mysql.freezeinfo.FreezeInfoMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 产品冻结信息 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class FreezeInfoServiceImpl implements FreezeInfoService {

    @Resource
    private FreezeInfoMapper freezeInfoMapper;

    @Override
    public Long createFreezeInfo(FreezeInfoCreateReqVO createReqVO) {
        // 插入
        FreezeInfoDO freezeInfo = FreezeInfoConvert.INSTANCE.convert(createReqVO);
        freezeInfoMapper.insert(freezeInfo);
        // 返回
        return freezeInfo.getId();
    }

    @Override
    public void updateFreezeInfo(FreezeInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateFreezeInfoExists(updateReqVO.getId());
        // 更新
        FreezeInfoDO updateObj = FreezeInfoConvert.INSTANCE.convert(updateReqVO);
        freezeInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteFreezeInfo(Long id) {
        // 校验存在
        validateFreezeInfoExists(id);
        // 删除
        freezeInfoMapper.deleteById(id);
    }

    private void validateFreezeInfoExists(Long id) {
        if (freezeInfoMapper.selectById(id) == null) {
            throw exception(FREEZE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public FreezeInfoDO getFreezeInfo(Long id) {
        return freezeInfoMapper.selectById(id);
    }

    @Override
    public List<FreezeInfoDO> getFreezeInfoList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return freezeInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<FreezeInfoDO> getFreezeInfoPage(FreezeInfoPageReqVO pageReqVO) {
        return freezeInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<FreezeInfoDO> getFreezeInfoList(FreezeInfoExportReqVO exportReqVO) {
        return freezeInfoMapper.selectList(exportReqVO);
    }

}
