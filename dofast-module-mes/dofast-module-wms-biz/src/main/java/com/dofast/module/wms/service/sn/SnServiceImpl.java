package com.dofast.module.wms.service.sn;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.sn.vo.*;
import com.dofast.module.wms.dal.dataobject.sn.SnDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.sn.SnConvert;
import com.dofast.module.wms.dal.mysql.sn.SnMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * SN码 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class SnServiceImpl implements SnService {

    @Resource
    private SnMapper snMapper;

    @Override
    public Long createSn(SnCreateReqVO createReqVO) {
        // 插入
        SnDO sn = SnConvert.INSTANCE.convert(createReqVO);
        snMapper.insert(sn);
        // 返回
        return sn.getId();
    }

    @Override
    public void updateSn(SnUpdateReqVO updateReqVO) {
        // 校验存在
        validateSnExists(updateReqVO.getId());
        // 更新
        SnDO updateObj = SnConvert.INSTANCE.convert(updateReqVO);
        snMapper.updateById(updateObj);
    }

    @Override
    public void deleteSn(Long id) {
        // 校验存在
        validateSnExists(id);
        // 删除
        snMapper.deleteById(id);
    }

    private void validateSnExists(Long id) {
        if (snMapper.selectById(id) == null) {
            throw exception(SN_NOT_EXISTS);
        }
    }

    @Override
    public SnDO getSn(Long id) {
        return snMapper.selectById(id);
    }

    @Override
    public List<SnDO> getSnList(Collection<Long> ids) {
        return snMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SnDO> getSnPage(SnPageReqVO pageReqVO) {
        return snMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<SnDO> getSnPageSn(SnPageReqVO pageReqVO) {
        return snMapper.selectPageSn(pageReqVO);
    }

    @Override
    public List<SnDO> getSnList(SnExportReqVO exportReqVO) {
        return snMapper.selectList(exportReqVO);
    }

}
