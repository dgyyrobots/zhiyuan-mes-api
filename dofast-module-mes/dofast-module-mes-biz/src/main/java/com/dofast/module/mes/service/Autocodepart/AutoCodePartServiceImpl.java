package com.dofast.module.mes.service.Autocodepart;

import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import com.dofast.module.mes.controller.admin.Autocoderule.vo.AutoCodeRuleListVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.Autocodepart.vo.*;
import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.Autocodepart.AutoCodePartConvert;
import com.dofast.module.mes.dal.mysql.Autocodepart.AutoCodePartMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 编码生成规则组成 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AutoCodePartServiceImpl implements AutoCodePartService {

    @Resource
    private AutoCodePartMapper autoCodePartMapper;

    @Override
    public String checkPartUnique(AutoCodePartBaseVO sysAutoCodePart) {
        AutoCodePartDO part = autoCodePartMapper.checkPartUnique(sysAutoCodePart);
        Long partId = StrUtils.isNull(sysAutoCodePart.getId())?-1L:sysAutoCodePart.getId();
        if(StrUtils.isNotNull(part) && partId.longValue() != part.getId().longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public List<AutoCodePartDO> getAutoCodePartList(AutoCodePartListVO listVO) {
        return autoCodePartMapper.selectList(listVO);
    }

    @Override
    public Long createAutoCodePart(AutoCodePartCreateReqVO createReqVO) {
        // 插入
        AutoCodePartDO autoCodePart = AutoCodePartConvert.INSTANCE.convert(createReqVO);
        autoCodePartMapper.insert(autoCodePart);
        // 返回
        return autoCodePart.getId();
    }

    @Override
    public void updateAutoCodePart(AutoCodePartUpdateReqVO updateReqVO) {
        // 校验存在
        validateAutoCodePartExists(updateReqVO.getId());
        // 更新
        AutoCodePartDO updateObj = AutoCodePartConvert.INSTANCE.convert(updateReqVO);
        autoCodePartMapper.updateById(updateObj);
    }

    @Override
    public void deleteAutoCodePart(Long id) {
        // 校验存在
        validateAutoCodePartExists(id);
        // 删除
        autoCodePartMapper.deleteById(id);
    }

    private void validateAutoCodePartExists(Long id) {
        if (autoCodePartMapper.selectById(id) == null) {
            throw exception(AUTO_CODE_PART_NOT_EXISTS);
        }
    }

    @Override
    public AutoCodePartDO getAutoCodePart(Long id) {
        return autoCodePartMapper.selectById(id);
    }

    @Override
    public List<AutoCodePartDO> getAutoCodePartList(Collection<Long> ids) {
        return autoCodePartMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AutoCodePartDO> getAutoCodePartPage(AutoCodePartPageReqVO pageReqVO) {
        return autoCodePartMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AutoCodePartDO> getAutoCodePartList(AutoCodePartExportReqVO exportReqVO) {
        return autoCodePartMapper.selectList(exportReqVO);
    }

}
