package com.dofast.module.trade.convert.brokerage;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.dict.core.util.DictFrameworkUtils;
import com.dofast.module.member.api.user.dto.MemberUserRespDTO;
import com.dofast.module.trade.controller.admin.brokerage.vo.withdraw.BrokerageWithdrawPageReqVO;
import com.dofast.module.trade.controller.admin.brokerage.vo.withdraw.BrokerageWithdrawRespVO;
import com.dofast.module.trade.controller.app.brokerage.vo.withdraw.AppBrokerageWithdrawCreateReqVO;
import com.dofast.module.trade.controller.app.brokerage.vo.withdraw.AppBrokerageWithdrawPageReqVO;
import com.dofast.module.trade.controller.app.brokerage.vo.withdraw.AppBrokerageWithdrawRespVO;
import com.dofast.module.trade.dal.dataobject.brokerage.BrokerageWithdrawDO;
import com.dofast.module.trade.enums.DictTypeConstants;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 佣金提现 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface BrokerageWithdrawConvert {

    BrokerageWithdrawConvert INSTANCE = Mappers.getMapper(BrokerageWithdrawConvert.class);

    BrokerageWithdrawDO convert(AppBrokerageWithdrawCreateReqVO createReqVO, Long userId, Integer feePrice);

    BrokerageWithdrawRespVO convert(BrokerageWithdrawDO bean);

    List<BrokerageWithdrawRespVO> convertList(List<BrokerageWithdrawDO> list);

    PageResult<BrokerageWithdrawRespVO> convertPage(PageResult<BrokerageWithdrawDO> page);

    default PageResult<BrokerageWithdrawRespVO> convertPage(PageResult<BrokerageWithdrawDO> pageResult, Map<Long, MemberUserRespDTO> userMap) {
        PageResult<BrokerageWithdrawRespVO> result = convertPage(pageResult);
        for (BrokerageWithdrawRespVO vo : result.getList()) {
            vo.setUserNickname(Optional.ofNullable(userMap.get(vo.getUserId())).map(MemberUserRespDTO::getNickname).orElse(null));
        }
        return result;
    }

    PageResult<AppBrokerageWithdrawRespVO> convertPage02(PageResult<BrokerageWithdrawDO> pageResult);

    default PageResult<AppBrokerageWithdrawRespVO> convertPage03(PageResult<BrokerageWithdrawDO> pageResult) {
        PageResult<AppBrokerageWithdrawRespVO> result = convertPage02(pageResult);
        for (AppBrokerageWithdrawRespVO vo : result.getList()) {

            vo.setStatusName(DictFrameworkUtils.getDictDataLabel(DictTypeConstants.BROKERAGE_WITHDRAW_STATUS, vo.getStatus()));


            vo.setStatusName(DictFrameworkUtils.getDictDataLabel(DictTypeConstants.BROKERAGE_WITHDRAW_STATUS, String.valueOf(vo.getStatus())));

        }
        return result;
    }

    BrokerageWithdrawPageReqVO convert(AppBrokerageWithdrawPageReqVO pageReqVO, Long userId);
}
