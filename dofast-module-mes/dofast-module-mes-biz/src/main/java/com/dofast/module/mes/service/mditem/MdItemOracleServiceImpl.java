package com.dofast.module.mes.service.mditem;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.module.mes.dal.oracle.mditem.MdItemOracleMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
@DS("oracle")
public class MdItemOracleServiceImpl implements MdItemOracleService {

    @Resource
    public MdItemOracleMapper mdItemOracleMapper;

    @Override
    public List<Map<String, Object>> initMdItemInfo() {
        List<Map<String, Object>> items = new ArrayList<>(); // 所有ERP中的物料信息
        // 开始获取数据
        items = mdItemOracleMapper.initItemInfo();
        return items;
    }


}
