package com.dofast.module.promotion.convert.coupon;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.promotion.api.coupon.dto.CouponRespDTO;
import com.dofast.module.promotion.controller.admin.coupon.vo.coupon.CouponPageItemRespVO;
import com.dofast.module.promotion.controller.admin.coupon.vo.coupon.CouponPageReqVO;
import com.dofast.module.promotion.controller.app.coupon.vo.coupon.AppCouponMatchRespVO;
import com.dofast.module.promotion.controller.app.coupon.vo.coupon.AppCouponPageReqVO;
import com.dofast.module.promotion.controller.app.coupon.vo.coupon.AppCouponRespVO;
import com.dofast.module.promotion.dal.dataobject.coupon.CouponDO;
import com.dofast.module.promotion.dal.dataobject.coupon.CouponTemplateDO;
import com.dofast.module.promotion.enums.coupon.CouponStatusEnum;
import com.dofast.module.promotion.enums.coupon.CouponTemplateValidityTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 优惠劵 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface CouponConvert {

    CouponConvert INSTANCE = Mappers.getMapper(CouponConvert.class);

    PageResult<CouponPageItemRespVO> convertPage(PageResult<CouponDO> page);

    CouponRespDTO convert(CouponDO bean);

    default CouponDO convert(CouponTemplateDO template, Long userId) {
        CouponDO couponDO = new CouponDO()
                .setTemplateId(template.getId())
                .setName(template.getName())
                .setTakeType(template.getTakeType())
                .setUsePrice(template.getUsePrice())
                .setProductScope(template.getProductScope())
                .setProductScopeValues(template.getProductScopeValues())
                .setDiscountType(template.getDiscountType())
                .setDiscountPercent(template.getDiscountPercent())
                .setDiscountPrice(template.getDiscountPrice())
                .setDiscountLimitPrice(template.getDiscountLimitPrice())
                .setStatus(CouponStatusEnum.UNUSED.getStatus())
                .setUserId(userId);
        if (CouponTemplateValidityTypeEnum.DATE.getType().equals(template.getValidityType())) {
            couponDO.setValidStartTime(template.getValidStartTime());
            couponDO.setValidEndTime(template.getValidEndTime());
        } else if (CouponTemplateValidityTypeEnum.TERM.getType().equals(template.getValidityType())) {
            couponDO.setValidStartTime(LocalDateTime.now().plusDays(template.getFixedStartTerm()));
            couponDO.setValidEndTime(LocalDateTime.now().plusDays(template.getFixedEndTerm()));
        }
        return couponDO;
    }

    CouponPageReqVO convert(AppCouponPageReqVO pageReqVO, Collection<Long> userIds);

    PageResult<AppCouponRespVO> convertAppPage(PageResult<CouponDO> pageResult);

    List<AppCouponMatchRespVO> convertList(List<CouponDO> list);

}
