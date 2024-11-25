package com.dofast.module.mes.service.mdunitconverse;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.mdunitconverse.vo.*;
import com.dofast.module.mes.dal.dataobject.mdunitconverse.MdUnitConverseDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mdunitconverse.MdUnitConverseConvert;
import com.dofast.module.mes.dal.mysql.mdunitconverse.MdUnitConverseMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 单位换算 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class MdUnitConverseServiceImpl implements MdUnitConverseService {

    @Resource
    private MdUnitConverseMapper mdUnitConverseMapper;

    @Override
    public Long createMdUnitConverse(MdUnitConverseCreateReqVO createReqVO) {
        // 插入
        MdUnitConverseDO mdUnitConverse = MdUnitConverseConvert.INSTANCE.convert(createReqVO);
        mdUnitConverseMapper.insert(mdUnitConverse);
        // 返回
        return mdUnitConverse.getId();
    }

    @Override
    public void updateMdUnitConverse(MdUnitConverseUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdUnitConverseExists(updateReqVO.getId());
        // 更新
        MdUnitConverseDO updateObj = MdUnitConverseConvert.INSTANCE.convert(updateReqVO);
        mdUnitConverseMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdUnitConverse(Long id) {
        // 校验存在
        validateMdUnitConverseExists(id);
        // 删除
        mdUnitConverseMapper.deleteById(id);
    }

    private void validateMdUnitConverseExists(Long id) {
        if (mdUnitConverseMapper.selectById(id) == null) {
            throw exception(MD_UNIT_CONVERSE_NOT_EXISTS);
        }
    }

    @Override
    public MdUnitConverseDO getMdUnitConverse(Long id) {
        return mdUnitConverseMapper.selectById(id);
    }

    @Override
    public List<MdUnitConverseDO> getMdUnitConverseList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return mdUnitConverseMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdUnitConverseDO> getMdUnitConversePage(MdUnitConversePageReqVO pageReqVO) {
        return mdUnitConverseMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdUnitConverseDO> getMdUnitConverseList(MdUnitConverseExportReqVO exportReqVO) {
        return mdUnitConverseMapper.selectList(exportReqVO);
    }

}
