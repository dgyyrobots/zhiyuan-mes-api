package com.dofast.module.system.api.dept;

import com.dofast.module.system.api.dept.dto.DeptRespDTO;
import com.dofast.module.system.convert.dept.DeptConvert;
import com.dofast.module.system.dal.dataobject.dept.DeptDO;
import com.dofast.module.system.service.dept.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 部门 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class DeptApiImpl implements DeptApi {

    @Resource
    private DeptService deptService;

    @Override
    public DeptRespDTO getDept(Long id) {
        DeptDO dept = deptService.getDept(id);
        return DeptConvert.INSTANCE.convert03(dept);
    }

    @Override
    public List<DeptRespDTO> getDeptList(Collection<Long> ids) {
        List<DeptDO> depts = deptService.getDeptList(ids);
        return DeptConvert.INSTANCE.convertList03(depts);
    }

    @Override
    public void validateDeptList(Collection<Long> ids) {
        deptService.validateDeptList(ids);
    }

}
