package com.dofast.module.trade.service.bpm;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.bpm.api.task.BpmProcessInstanceApi;
import com.dofast.module.bpm.api.task.dto.BpmProcessInstanceCreateReqDTO;
import com.dofast.module.trade.controller.admin.bpm.mixin.vo.MixinOrderBpmCreateReqVO;
import com.dofast.module.trade.controller.admin.bpm.mixin.vo.MixinOrderBpmPageReqVO;
import com.dofast.module.trade.convert.mixin.MixinOrderConvert;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;
import com.dofast.module.trade.dal.mysql.mixin.MixinOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.pad.util.PadSecurityUtils.getUserId;
import static com.dofast.module.trade.enums.ErrorCodeConstants.MIXIN_ORDER_IS_NOT_EXITS;

@Service
public class BpmMixinOrderServiceImpl implements BpmMixinOrderService{

    /**
     * 销售订单对应的流程定义 KEY
     */
    public static final String MIXINORDER_KEY = "mixinorder";

    @Resource
    private MixinOrderMapper mixinOrderMapper;

    @Resource
    private BpmProcessInstanceApi processInstanceApi;
    @Override
    public Long createMixinOrder(Long userId, MixinOrderBpmCreateReqVO createReqVO) {
        // 插入 售后信息
        /*long day = LocalDateTimeUtil.between(createReqVO.getStartTime(), createReqVO.getEndTime()).toDays();
        BpmOALeaveDO leave = BpmOALeaveConvert.INSTANCE.convert(createReqVO).setUserId(userId).setDay(day)
                .setResult(BpmProcessInstanceResultEnum.PROCESS.getResult());
        leaveMapper.insert(leave);*/
        MixinOrderDO convert = MixinOrderConvert.INSTANCE.convert(createReqVO);
        convert.setCreator(String.valueOf(getUserId()));
        mixinOrderMapper.insert(convert);

        // 发起 BPM 流程
        Map<String, Object> processInstanceVariables = new HashMap<>();
        processInstanceVariables.put("after-order",createReqVO);
        String processInstanceId = processInstanceApi.createProcessInstance(userId,
                new BpmProcessInstanceCreateReqDTO().setProcessDefinitionKey(MIXINORDER_KEY)
                        .setVariables(processInstanceVariables).setBusinessKey(String.valueOf(convert.getId())));

        // 将工作流的编号，更新到 OA 请假单中
//        orderAfterMapper.updateById(new BpmOALeaveDO().setId(leave.getId()).setProcessInstanceId(processInstanceId));
        mixinOrderMapper.updateById(MixinOrderConvert.INSTANCE.convert(createReqVO).setProcessInstanceId(processInstanceId));
        return convert.getId();
    }

    @Override
    public void updateMixinOrderResult(Long id, Integer result) {
        validateLeaveExists(id);
        String status="";
        if (result==0){
            status="CREATED";
        }else if (result==1){
            status="PASSED";
        }
        mixinOrderMapper.updateById(new MixinOrderDO().setId(id).setStatus(status));
    }

    private void validateLeaveExists(Long id) {
        if (mixinOrderMapper.selectById(id) == null) {
            throw exception(MIXIN_ORDER_IS_NOT_EXITS);
        }
    }

    @Override
    public MixinOrderDO getMixinOrder(Long id) {
        return mixinOrderMapper.selectById(id);
    }

    @Override
    public PageResult<MixinOrderDO> getMixinOrderPage(Long userId, MixinOrderBpmPageReqVO pageReqVO) {
        return mixinOrderMapper.selectPage(String.valueOf(userId),pageReqVO);
    }
}
