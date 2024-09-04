package com.dofast.module.wms.service.rtvendorline;

import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.wms.controller.admin.rtvendorline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtvendorline.RtVendorLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.wms.convert.rtvendorline.RtVendorLineConvert;
import com.dofast.module.wms.dal.mysql.rtvendorline.RtVendorLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.wms.enums.ErrorCodeConstants.*;

/**
 * 供应商退货行 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RtVendorLineServiceImpl implements RtVendorLineService {

    @Resource
    private RtVendorLineMapper rtVendorLineMapper;

    @Override
    public int deleteByRtId(Long rtId) {
        return rtVendorLineMapper.delete(new LambdaQueryWrapperX<RtVendorLineDO>().eq(RtVendorLineDO::getRtId,rtId));
    }

    @Override
    public Long createRtVendorLine(RtVendorLineCreateReqVO createReqVO) {
        // 插入
        RtVendorLineDO rtVendorLine = RtVendorLineConvert.INSTANCE.convert(createReqVO);
        rtVendorLineMapper.insert(rtVendorLine);
        // 返回
        return rtVendorLine.getId();
    }

    @Override
    public void updateRtVendorLine(RtVendorLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateRtVendorLineExists(updateReqVO.getId());
        // 更新
        RtVendorLineDO updateObj = RtVendorLineConvert.INSTANCE.convert(updateReqVO);
        rtVendorLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteRtVendorLine(Long id) {
        // 校验存在
        validateRtVendorLineExists(id);
        // 删除
        rtVendorLineMapper.deleteById(id);
    }

    private void validateRtVendorLineExists(Long id) {
        if (rtVendorLineMapper.selectById(id) == null) {
            throw exception(RT_VENDOR_LINE_NOT_EXISTS);
        }
    }

    @Override
    public RtVendorLineDO getRtVendorLine(Long id) {
        return rtVendorLineMapper.selectById(id);
    }

    @Override
    public List<RtVendorLineDO> getRtVendorLineList(Collection<Long> ids) {
        return rtVendorLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RtVendorLineDO> getRtVendorLinePage(RtVendorLinePageReqVO pageReqVO) {
        return rtVendorLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RtVendorLineDO> getRtVendorLineList(RtVendorLineExportReqVO exportReqVO) {
        return rtVendorLineMapper.selectList(exportReqVO);
    }

}
