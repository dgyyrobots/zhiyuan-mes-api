package com.dofast.module.member.service.point;

import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.module.member.controller.admin.point.vo.config.MemberPointConfigSaveReqVO;
import com.dofast.module.member.convert.point.MemberPointConfigConvert;
import com.dofast.module.member.dal.dataobject.point.MemberPointConfigDO;
import com.dofast.module.member.dal.mysql.point.MemberPointConfigMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员积分配置 Service 实现类
 *
 * @author QingX
 */
@Service
@Validated
public class MemberPointConfigServiceImpl implements MemberPointConfigService {

    @Resource
    private MemberPointConfigMapper memberPointConfigMapper;

    @Override
    public void savePointConfig(MemberPointConfigSaveReqVO saveReqVO) {
        // 存在，则进行更新
        MemberPointConfigDO dbConfig = getPointConfig();
        if (dbConfig != null) {
            memberPointConfigMapper.updateById(MemberPointConfigConvert.INSTANCE.convert(saveReqVO).setId(dbConfig.getId()));
            return;
        }
        // 不存在，则进行插入
        memberPointConfigMapper.insert(MemberPointConfigConvert.INSTANCE.convert(saveReqVO));
    }

    @Override
    public MemberPointConfigDO getPointConfig() {
        List<MemberPointConfigDO> list = memberPointConfigMapper.selectList();
        return CollectionUtils.getFirst(list);
    }

}
