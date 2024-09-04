package com.dofast.module.tm.api.ToolTypeApi;

import cn.hutool.core.bean.BeanUtil;
import com.dofast.module.tm.api.TmToolTypeApi.TmToolTypeApi;
import com.dofast.module.tm.api.TmToolTypeApi.dto.TmToolTypeDTO;
import com.dofast.module.tm.dal.dataobject.tooltype.ToolTypeDO;
import com.dofast.module.tm.dal.mysql.tooltype.ToolTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmToolTypeApiImpl implements TmToolTypeApi {
    @Autowired
    ToolTypeMapper toolTypeMapper;

    @Override
    public TmToolTypeDTO selectById(Long id) {
        ToolTypeDO toolTypeDO = toolTypeMapper.selectById(id);
        TmToolTypeDTO tmToolTypeDTO = BeanUtil.toBean(toolTypeDO, TmToolTypeDTO.class);
        return tmToolTypeDTO;
    }
}
