package com.dofast.module.system.service.dj002;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dofast.module.system.oneLogin.mapper.OneLoginMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;

import java.sql.Wrapper;
import java.util.*;

import com.dofast.module.system.controller.admin.dj002.vo.*;
import com.dofast.module.system.dal.dataobject.dj002.Dj002DO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.system.convert.dj002.Dj002Convert;
import com.dofast.module.system.dal.mysql.dj002.Dj002Mapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 系统地址信息 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class Dj002ServiceImpl implements Dj002Service {

    @Resource
    private Dj002Mapper dj002Mapper;

    @Resource
    private OneLoginMapper oneLoginMapper;

    @Override
    public Integer createDj002(Dj002CreateReqVO createReqVO) {
        // 插入
        Dj002DO dj002 = Dj002Convert.INSTANCE.convert(createReqVO);
        dj002Mapper.insert(dj002);
        // 返回
        return dj002.getId();
    }

    @Override
    public void updateDj002(Dj002UpdateReqVO updateReqVO) {
        // 校验存在
        validateDj002Exists(updateReqVO.getId());
        // 更新
        Dj002DO updateObj = Dj002Convert.INSTANCE.convert(updateReqVO);
        oneLoginMapper.updateDj002Net(updateObj);
    }

    @Override
    public void deleteDj002(Integer id) {
        // 校验存在
        validateDj002Exists(id);
        // 删除
        dj002Mapper.deleteById(id);
    }

    private void validateDj002Exists(Integer id) {
        if (dj002Mapper.selectById(id) == null) {
            throw exception(500);
        }
    }

    @Override
    public Dj002DO getDj002(Integer id) {
        return dj002Mapper.selectById(id);
    }

    @Override
    public List<Dj002DO> getDj002List(Collection<Integer> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return dj002Mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<Dj002DO> getDj002Page(Dj002PageReqVO pageReqVO) {
        return dj002Mapper.selectPage(pageReqVO);
    }

    @Override
    public List<Dj002DO> getDj002List(Dj002ExportReqVO exportReqVO) {
        return dj002Mapper.selectList(exportReqVO);
    }

}
