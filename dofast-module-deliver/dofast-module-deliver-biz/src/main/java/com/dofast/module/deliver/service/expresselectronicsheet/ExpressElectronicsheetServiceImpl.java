package com.dofast.module.deliver.service.expresselectronicsheet;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo.*;
import com.dofast.module.deliver.dal.dataobject.expresselectronicsheet.ExpressElectronicsheetDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.deliver.convert.expresselectronicsheet.ExpressElectronicsheetConvert;
import com.dofast.module.deliver.dal.mysql.expresselectronicsheet.ExpressElectronicsheetMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.deliver.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 电子面单 Service 实现类
 *
 * @author a1
 */
@Service
@Validated
public class ExpressElectronicsheetServiceImpl implements ExpressElectronicsheetService {

    @Resource
    private ExpressElectronicsheetMapper expressElectronicsheetMapper;

    @Override
    public Long createExpressElectronicsheet(ExpressElectronicsheetCreateReqVO createReqVO) {
        // 插入
        ExpressElectronicsheetDO expressElectronicsheet = ExpressElectronicsheetConvert.INSTANCE.convert(createReqVO);
        expressElectronicsheetMapper.insert(expressElectronicsheet);
        // 返回
        return expressElectronicsheet.getId();
    }

    @Override
    public void updateExpressElectronicsheet(ExpressElectronicsheetUpdateReqVO updateReqVO) {
        // 校验存在
        validateExpressElectronicsheetExists(updateReqVO.getId());
        // 更新
        ExpressElectronicsheetDO updateObj = ExpressElectronicsheetConvert.INSTANCE.convert(updateReqVO);
        expressElectronicsheetMapper.updateById(updateObj);
    }

    @Override
    public void deleteExpressElectronicsheet(Long id) {
        // 校验存在
        validateExpressElectronicsheetExists(id);
        // 删除
        expressElectronicsheetMapper.deleteById(id);
    }

    private void validateExpressElectronicsheetExists(Long id) {
        if (expressElectronicsheetMapper.selectById(id) == null) {
            throw exception(EXPRESS_ELECTRONICSHEET_NOT_EXISTS);
        }
    }

    @Override
    public ExpressElectronicsheetDO getExpressElectronicsheet(Long id) {
        return expressElectronicsheetMapper.selectById(id);
    }

    @Override
    public List<ExpressElectronicsheetDO> getExpressElectronicsheetList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return expressElectronicsheetMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ExpressElectronicsheetDO> getExpressElectronicsheetPage(ExpressElectronicsheetPageReqVO pageReqVO) {
        return expressElectronicsheetMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ExpressElectronicsheetDO> getExpressElectronicsheetList(ExpressElectronicsheetExportReqVO exportReqVO) {
        return expressElectronicsheetMapper.selectList(exportReqVO);
    }

}
