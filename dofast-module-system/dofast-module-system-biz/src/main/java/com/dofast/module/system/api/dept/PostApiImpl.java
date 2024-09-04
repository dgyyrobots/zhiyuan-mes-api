package com.dofast.module.system.api.dept;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.system.api.dept.dto.DeptPostDTO;
import com.dofast.module.system.dal.dataobject.dept.PostDO;
import com.dofast.module.system.dal.mysql.dept.PostMapper;
import com.dofast.module.system.service.dept.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 岗位 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class PostApiImpl implements PostApi {

    @Resource
    private PostService postService;

    @Resource
    private PostMapper postMapper;

    @Override
    public void validPostList(Collection<Long> ids) {
        postService.validatePostList(ids);
    }

    @Override
    public DeptPostDTO selectById(Long id) {
        PostDO postDO = postMapper.selectById(id);
        DeptPostDTO deptPostDTO = BeanUtil.toBean(postDO, DeptPostDTO.class);
        return deptPostDTO;
    }

}
