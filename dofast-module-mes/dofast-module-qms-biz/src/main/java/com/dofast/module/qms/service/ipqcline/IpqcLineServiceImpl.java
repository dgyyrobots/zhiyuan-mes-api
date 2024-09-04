package com.dofast.module.qms.service.ipqcline;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.qms.controller.admin.ipqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.ipqcline.IpqcLineDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.qms.convert.ipqcline.IpqcLineConvert;
import com.dofast.module.qms.dal.mysql.ipqcline.IpqcLineMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.qms.enums.ErrorCodeConstants.*;

/**
 * 过程检验单行 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class IpqcLineServiceImpl implements IpqcLineService {

    @Resource
    private IpqcLineMapper ipqcLineMapper;

    @Override
    public int updateCrMajMinQuantity(Long qcId, Long lineId) {
        IpqcLineDO line = new IpqcLineDO();
        line.setIpqcId(qcId);
        line.setId(lineId);
        return ipqcLineMapper.updateCrMajMinQuantity(line);
    }

    @Override
    public int deleteByIpqcId(Long ipqcId) {
        return ipqcLineMapper.deleteByIpqcId(ipqcId);
    }

    @Override
    public Long createIpqcLine(IpqcLineCreateReqVO createReqVO) {
        // 插入
        IpqcLineDO ipqcLine = IpqcLineConvert.INSTANCE.convert(createReqVO);
        ipqcLineMapper.insert(ipqcLine);
        // 返回
        return ipqcLine.getId();
    }

    @Override
    public void updateIpqcLine(IpqcLineUpdateReqVO updateReqVO) {
        // 校验存在
        validateIpqcLineExists(updateReqVO.getId());
        // 更新
        IpqcLineDO updateObj = IpqcLineConvert.INSTANCE.convert(updateReqVO);
        ipqcLineMapper.updateById(updateObj);
    }

    @Override
    public void deleteIpqcLine(Long id) {
        // 校验存在
        validateIpqcLineExists(id);
        // 删除
        ipqcLineMapper.deleteById(id);
    }

    private void validateIpqcLineExists(Long id) {
        if (ipqcLineMapper.selectById(id) == null) {
            throw exception(IPQC_LINE_NOT_EXISTS);
        }
    }

    @Override
    public IpqcLineDO getIpqcLine(Long id) {
        return ipqcLineMapper.selectById(id);
    }

    @Override
    public List<IpqcLineDO> getIpqcLineList(Collection<Long> ids) {
        return ipqcLineMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<IpqcLineDO> getIpqcLinePage(IpqcLinePageReqVO pageReqVO) {
        return ipqcLineMapper.selectPage(pageReqVO);
    }

    @Override
    public List<IpqcLineDO> getIpqcLineList(IpqcLineExportReqVO exportReqVO) {
        return ipqcLineMapper.selectList(exportReqVO);
    }

}
