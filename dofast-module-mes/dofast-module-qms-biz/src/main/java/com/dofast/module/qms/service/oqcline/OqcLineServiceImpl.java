package com.dofast.module.qms.service.oqcline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.qms.controller.admin.oqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.oqcline.OqcLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.qms.convert.oqcline.OqcLineConvert;
import com.dofast.module.qms.dal.mysql.oqcline.OqcLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.qms.enums.ErrorCodeConstants.*;

/**
 * 出货检验单行 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OqcLineServiceImpl implements OqcLineService {

    @Resource
    private OqcLineMapper oqcLineMapper;

    @Override
    public int updateCrMajMinQuantity(Long qcId, Long lineId) {
        OqcLineDO line = new OqcLineDO();
        line.setId(lineId);
        line.setOqcId(qcId);
        return oqcLineMapper.updateCrMajMinQuantity(line);
    }

    @Override
    public int deleteByOqcId(Long oqcId) {
        return oqcLineMapper.deleteByOqcId(oqcId);
    }

    @Override
    public Long createOqcLine(OqcLineCreateReqVO createReqVO) {
        // 插入
        OqcLineDO oqcLine = OqcLineConvert.INSTANCE.convert(createReqVO);
        oqcLineMapper.insert(oqcLine);
        // 返回
        return oqcLine.getId();
    }

    @Override
    public void updateOqcLine(OqcLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateOqcLineExists(updateReqVO.getId());
        // 更新
        OqcLineDO updateObj = OqcLineConvert.INSTANCE.convert(updateReqVO);
        oqcLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteOqcLine(Long id) {
        // 校验存在
        validateOqcLineExists(id);
        // 删除
        oqcLineMapper.deleteById(id);
    }

    private void validateOqcLineExists(Long id) {
        if (oqcLineMapper.selectById(id) == null) {
            throw exception(OQC_LINE_NOT_EXISTS);
        }
    }

    @Override
    public OqcLineDO getOqcLine(Long id) {
        return oqcLineMapper.selectById(id);
    }

    @Override
    public List<OqcLineDO> getOqcLineList(Collection<Long> ids) {
        return oqcLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OqcLineDO> getOqcLinePage(OqcLinePageReqVO pageReqVO) {
        return oqcLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OqcLineDO> getOqcLineList(OqcLineExportReqVO exportReqVO) {
        return oqcLineMapper.selectList(exportReqVO);
    }

}
