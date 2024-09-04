package com.dofast.module.mes.service.mdworkstationworker;

import cn.hutool.core.collection.CollectionUtil;
import com.dofast.framework.common.util.string.StrUtils;
import com.dofast.module.mes.constant.Constant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.mes.controller.admin.mdworkstationworker.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationworker.MdWorkstationWorkerDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mdworkstationworker.MdWorkstationWorkerConvert;
import com.dofast.module.mes.dal.mysql.mdworkstationworker.MdWorkstationWorkerMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 人力资源 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdWorkstationWorkerServiceImpl implements MdWorkstationWorkerService {

    @Resource
    private MdWorkstationWorkerMapper mdWorkstationWorkerMapper;

    @Override
    public int deleteByWorkstationId(Long workstationId) {
        return mdWorkstationWorkerMapper.deleteByWorkstationId(workstationId);
    }

    @Override
    public String checkPostExist(MdWorkstationWorkerBaseVO mdWorkstationWorker) {
        MdWorkstationWorkerDO post = mdWorkstationWorkerMapper.checkPostExist(mdWorkstationWorker);
        Long recordId = mdWorkstationWorker.getId()==null?-1L:mdWorkstationWorker.getId();
        if(StrUtils.isNotNull(post) && post.getId().longValue() != recordId.longValue()){
            return Constant.NOT_UNIQUE;
        }
        return Constant.UNIQUE;
    }

    @Override
    public Long createMdWorkstationWorker(MdWorkstationWorkerCreateReqVO createReqVO) {
        // 插入
        MdWorkstationWorkerDO mdWorkstationWorker = MdWorkstationWorkerConvert.INSTANCE.convert(createReqVO);
        mdWorkstationWorkerMapper.insert(mdWorkstationWorker);
        // 返回
        return mdWorkstationWorker.getId();
    }

    @Override
    public void updateMdWorkstationWorker(MdWorkstationWorkerUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdWorkstationWorkerExists(updateReqVO.getId());
        // 更新
        MdWorkstationWorkerDO updateObj = MdWorkstationWorkerConvert.INSTANCE.convert(updateReqVO);
        mdWorkstationWorkerMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdWorkstationWorker(Long id) {
        // 校验存在
        validateMdWorkstationWorkerExists(id);
        // 删除
        mdWorkstationWorkerMapper.deleteById(id);
    }

    private void validateMdWorkstationWorkerExists(Long id) {
        if (mdWorkstationWorkerMapper.selectById(id) == null) {
            throw exception(MD_WORKSTATION_WORKER_NOT_EXISTS);
        }
    }

    @Override
    public MdWorkstationWorkerDO getMdWorkstationWorker(Long id) {
        return mdWorkstationWorkerMapper.selectById(id);
    }

    @Override
    public List<MdWorkstationWorkerDO> getMdWorkstationWorkerList(Collection<Long> ids) {
        return mdWorkstationWorkerMapper.selectBatchIds(ids);
    }

    @Override
    public List<MdWorkstationWorkerDO> getMdWorkstationWorkerListByPostId(Set<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return mdWorkstationWorkerMapper.selectList(ids);
    }

    @Override
    public PageResult<MdWorkstationWorkerDO> getMdWorkstationWorkerPage(MdWorkstationWorkerPageReqVO pageReqVO) {
        return mdWorkstationWorkerMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdWorkstationWorkerDO> getMdWorkstationWorkerList(MdWorkstationWorkerExportReqVO exportReqVO) {
        return mdWorkstationWorkerMapper.selectList(exportReqVO);
    }

}
