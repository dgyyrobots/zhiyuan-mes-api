package com.dofast.module.qms.service.iqcline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.qms.controller.admin.iqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.iqcline.IqcLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.qms.convert.iqcline.IqcLineConvert;
import com.dofast.module.qms.dal.mysql.iqcline.IqcLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.qms.enums.ErrorCodeConstants.*;

/**
 * 来料检验单行 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class IqcLineServiceImpl implements IqcLineService {

    @Resource
    private IqcLineMapper iqcLineMapper;

    @Override
    public int deleteByIqcId(Long iqcId) {
        return iqcLineMapper.deleteByIqcId(iqcId);
    }

    @Override
    public int updateCrMajMinQuantity(Long iqcId, Long lineId) {
        IqcLineDO qcIqcLine = new IqcLineDO();
        qcIqcLine.setIqcId(iqcId);
        qcIqcLine.setId(lineId);
        return iqcLineMapper.updateCrMajMinQuantity(qcIqcLine);
    }

    @Override
    public Long createIqcLine(IqcLineCreateReqVO createReqVO) {
        // 插入
        IqcLineDO iqcLine = IqcLineConvert.INSTANCE.convert(createReqVO);
        iqcLineMapper.insert(iqcLine);
        // 返回
        return iqcLine.getId();
    }

    @Override
    public void updateIqcLine(IqcLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateIqcLineExists(updateReqVO.getId());
        // 更新
        IqcLineDO updateObj = IqcLineConvert.INSTANCE.convert(updateReqVO);
        iqcLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteIqcLine(Long id) {
        // 校验存在
        validateIqcLineExists(id);
        // 删除
        iqcLineMapper.deleteById(id);
    }

    private void validateIqcLineExists(Long id) {
        if (iqcLineMapper.selectById(id) == null) {
            throw exception(IQC_LINE_NOT_EXISTS);
        }
    }

    @Override
    public IqcLineDO getIqcLine(Long id) {
        return iqcLineMapper.selectById(id);
    }

    @Override
    public List<IqcLineDO> getIqcLineList(Collection<Long> ids) {
        return iqcLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<IqcLineDO> getIqcLinePage(IqcLinePageReqVO pageReqVO) {
        return iqcLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<IqcLineDO> getIqcLineList(IqcLineExportReqVO exportReqVO) {
        return iqcLineMapper.selectList(exportReqVO);
    }

}
