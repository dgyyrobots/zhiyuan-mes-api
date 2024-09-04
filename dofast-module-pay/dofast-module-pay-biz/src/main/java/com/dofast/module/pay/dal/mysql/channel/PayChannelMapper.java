package com.dofast.module.pay.dal.mysql.channel;





import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.query.QueryWrapperX;
import com.dofast.module.pay.controller.admin.merchant.vo.channel.PayChannelExportReqVO;
import com.dofast.module.pay.controller.admin.merchant.vo.channel.PayChannelPageReqVO;
import com.dofast.module.pay.dal.dataobject.channel.PayChannelDO;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Mapper
public interface PayChannelMapper extends BaseMapperX<PayChannelDO> {

    default PayChannelDO selectByAppIdAndCode(Long appId, String code) {
        return selectOne(PayChannelDO::getAppId, appId, PayChannelDO::getCode, code);
    }

    default List<PayChannelDO> selectListByAppIds(Collection<Long> appIds){
        return selectList(PayChannelDO::getAppId, appIds);
    }

    default List<PayChannelDO> selectListByAppId(Long appId, Integer status) {
        return selectList(new LambdaQueryWrapperX<PayChannelDO>()
                .eq(PayChannelDO::getAppId, appId)
                .eq(PayChannelDO::getStatus, status));
    }





    @Select("SELECT COUNT(*) FROM pay_channel WHERE update_time > #{maxUpdateTime}")
    Long selectCountByUpdateTimeGt(LocalDateTime maxUpdateTime);




    default PageResult<PayChannelDO> selectPage(PayChannelPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<PayChannelDO>()
                .eqIfPresent("code", reqVO.getCode())
                .eqIfPresent("status", reqVO.getStatus())
                .eqIfPresent("remark", reqVO.getRemark())
                .eqIfPresent("fee_rate", reqVO.getFeeRate())
                .eqIfPresent("merchant_id", reqVO.getMerchantId())
                .eqIfPresent("app_id", reqVO.getAppId())
                .betweenIfPresent("create_time", reqVO.getCreateTime())
                .orderByDesc("id"));
    }

    default List<PayChannelDO> selectList(PayChannelExportReqVO reqVO) {
        return selectList(new QueryWrapperX<PayChannelDO>()
                .eqIfPresent("code", reqVO.getCode())
                .eqIfPresent("status", reqVO.getStatus())
                .eqIfPresent("remark", reqVO.getRemark())
                .eqIfPresent("fee_rate", reqVO.getFeeRate())
                .eqIfPresent("merchant_id", reqVO.getMerchantId())
                .eqIfPresent("app_id", reqVO.getAppId())
                .betweenIfPresent("create_time", reqVO.getCreateTime())
                .orderByDesc("id"));
    }

    /**
     * 根据条件获取渠道数量
     *
     * @param merchantId 商户编号
     * @param appid      应用编号
     * @param code       渠道编码
     * @return 数量
     */
    default Integer selectCount(Long merchantId, Long appid, String code) {
        return this.selectCount(new QueryWrapper<PayChannelDO>().lambda()
                .eq(PayChannelDO::getMerchantId, merchantId)
                .eq(PayChannelDO::getAppId, appid)
                .eq(PayChannelDO::getCode, code)).intValue();
    }

    /**
     * 根据条件获取渠道
     *
     * @param merchantId 商户编号
     * @param appI      应用编号
     * @param code       渠道编码
     * @return 数量
     */
    default PayChannelDO selectOne(Long merchantId, Long appI, String code) {
        return this.selectOne((new QueryWrapper<PayChannelDO>().lambda()
                .eq(PayChannelDO::getMerchantId, merchantId)
                .eq(PayChannelDO::getAppId, appI)
                .eq(PayChannelDO::getCode, code)
        ));
    }

    // TODO @aquan：select 命名
    /**
     * 根据支付应用ID集合获得支付渠道列表
     *
     * @param appIds 应用编号集合
     * @return 支付渠道列表
     */
    default List<PayChannelDO> getChannelListByAppIds(Collection<Long> appIds){
        return this.selectList(new QueryWrapper<PayChannelDO>().lambda()
                .in(PayChannelDO::getAppId, appIds));
    }
}
