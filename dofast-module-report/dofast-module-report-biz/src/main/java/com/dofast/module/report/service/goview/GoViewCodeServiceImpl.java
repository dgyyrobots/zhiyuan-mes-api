package com.dofast.module.report.service.goview;

import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeCreateReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeExportReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodePageReqVO;
import com.dofast.module.report.controller.admin.goview.vo.code.GoViewCodeUpdateReqVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import com.dofast.module.report.dal.dataobject.goview.GoViewCodeDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.report.convert.goviewcode.GoViewCodeConvert;
import com.dofast.module.report.dal.mysql.goview.GoViewCodeMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.report.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * GoView登录 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class GoViewCodeServiceImpl implements GoViewCodeService {

    @Resource
    private GoViewCodeMapper goViewCodeMapper;

    @Override
    public Long createGoViewCode(GoViewCodeCreateReqVO createReqVO) {
        // 插入
        GoViewCodeDO goViewCode = GoViewCodeConvert.INSTANCE.convert(createReqVO);
        goViewCodeMapper.insert(goViewCode);
        // 返回
        return goViewCode.getId();
    }

    @Override
    public void updateGoViewCode(GoViewCodeUpdateReqVO updateReqVO) {
        // 校验存在
        validateGoViewCodeExists(updateReqVO.getId());
        // 更新
        GoViewCodeDO updateObj = GoViewCodeConvert.INSTANCE.convert(updateReqVO);
        goViewCodeMapper.updateById(updateObj);
    }

    @Override
    public void deleteGoViewCode(Long id) {
        // 校验存在
        validateGoViewCodeExists(id);
        // 删除
        goViewCodeMapper.deleteById(id);
    }

    private void validateGoViewCodeExists(Long id) {
        if (goViewCodeMapper.selectById(id) == null) {
            throw exception(GO_VIEW_CODE_NOT_EXISTS);
        }
    }

    @Override
    public GoViewCodeDO getGoViewCode(Long id) {
        return goViewCodeMapper.selectById(id);
    }

    @Override
    public  GoViewCodeDO getGoViewCode(String code){
        return goViewCodeMapper.selectOne(GoViewCodeDO::getCode,code);
    }

    @Override
    public List<GoViewCodeDO> getGoViewCodeList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return goViewCodeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<GoViewCodeDO> getGoViewCodePage(GoViewCodePageReqVO pageReqVO) {
        return goViewCodeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GoViewCodeDO> getGoViewCodeList(GoViewCodeExportReqVO exportReqVO) {
        return goViewCodeMapper.selectList(exportReqVO);
    }

}
