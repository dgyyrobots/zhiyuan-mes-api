package com.dofast.module.product.service.comment;





import com.dofast.framework.common.exception.util.ServiceExceptionUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.member.api.user.MemberUserApi;
import com.dofast.module.member.api.user.dto.MemberUserRespDTO;
import com.dofast.module.product.api.comment.dto.ProductCommentCreateReqDTO;
import com.dofast.module.product.controller.admin.comment.vo.ProductCommentCreateReqVO;
import com.dofast.module.product.controller.admin.comment.vo.ProductCommentPageReqVO;
import com.dofast.module.product.controller.admin.comment.vo.ProductCommentReplyReqVO;
import com.dofast.module.product.controller.admin.comment.vo.ProductCommentUpdateVisibleReqVO;
import com.dofast.module.product.controller.app.comment.vo.AppCommentPageReqVO;
import com.dofast.module.product.controller.app.comment.vo.AppCommentStatisticsRespVO;
import com.dofast.module.product.controller.app.comment.vo.AppProductCommentRespVO;
import com.dofast.module.product.convert.comment.ProductCommentConvert;
import com.dofast.module.product.dal.dataobject.comment.ProductCommentDO;
import com.dofast.module.product.dal.dataobject.sku.ProductSkuDO;
import com.dofast.module.product.dal.dataobject.spu.ProductSpuDO;
import com.dofast.module.product.dal.mysql.comment.ProductCommentMapper;




import com.dofast.module.product.enums.ErrorCodeConstants;
import com.dofast.module.product.service.sku.ProductSkuService;
import com.dofast.module.product.service.spu.ProductSpuService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;

import static com.dofast.module.product.enums.ErrorCodeConstants.*;




/**
 * 商品评论 Service 实现类
 *
 * @author wangzhs
 */
@Service
@Validated
public class ProductCommentServiceImpl implements ProductCommentService {

    @Resource
    private ProductCommentMapper productCommentMapper;

    @Resource
    private ProductSpuService productSpuService;

    @Resource
    @Lazy
    private ProductSkuService productSkuService;

    @Resource
    private MemberUserApi memberUserApi;

    @Override
    public void createComment(ProductCommentCreateReqVO createReqVO) {
        // 校验 SKU
        ProductSkuDO skuDO = validateSku(createReqVO.getSkuId());
        // 校验 SPU
        ProductSpuDO spuDO = validateSpu(skuDO.getSpuId());

        // 创建评论
        ProductCommentDO comment = ProductCommentConvert.INSTANCE.convert(createReqVO, spuDO, skuDO);
        productCommentMapper.insert(comment);
    }

    @Override
    public Long createComment(ProductCommentCreateReqDTO createReqDTO) {
        // 校验 SKU
        ProductSkuDO skuDO = validateSku(createReqDTO.getSkuId());
        // 校验 SPU
        ProductSpuDO spuDO = validateSpu(skuDO.getSpuId());
        // 校验评论
        validateCommentExists(createReqDTO.getUserId(), createReqDTO.getOrderId());
        // 获取用户详细信息
        MemberUserRespDTO user = memberUserApi.getUser(createReqDTO.getUserId());

        // 创建评论
        ProductCommentDO comment = ProductCommentConvert.INSTANCE.convert(createReqDTO, spuDO, skuDO, user);
        productCommentMapper.insert(comment);
        return comment.getId();
    }

    /**
     * 判断当前订单的当前商品用户是否评价过
     *
     * @param userId      用户编号
     * @param orderItemId 订单项编号
     */
    private void validateCommentExists(Long userId, Long orderItemId) {
        ProductCommentDO exist = productCommentMapper.selectByUserIdAndOrderItemId(userId, orderItemId);
        if (exist != null) {
            throw exception(COMMENT_ORDER_EXISTS);

        }
    }

    private ProductSkuDO validateSku(Long skuId) {
        ProductSkuDO sku = productSkuService.getSku(skuId);
        if (sku == null) {
            throw exception(SKU_NOT_EXISTS);
        }
        return sku;
    }

    private ProductSpuDO validateSpu(Long spuId) {
        ProductSpuDO spu = productSpuService.getSpu(spuId);
        if (null == spu) {
            throw exception(SPU_NOT_EXISTS);
        }
        return spu;
    }

    @Override
    public void updateCommentVisible(ProductCommentUpdateVisibleReqVO updateReqVO) {
        // 校验评论是否存在
        validateCommentExists(updateReqVO.getId());

        // 更新可见状态
        productCommentMapper.updateById(new ProductCommentDO().setId(updateReqVO.getId())
                .setVisible(true));
    }

    @Override
    public void replyComment(ProductCommentReplyReqVO replyVO, Long userId) {
        // 校验评论是否存在
        validateCommentExists(replyVO.getId());
        // 回复评论
        productCommentMapper.updateById(new ProductCommentDO().setId(replyVO.getId())
                .setReplyTime(LocalDateTime.now()).setReplyUserId(userId)
                .setReplyStatus(Boolean.TRUE).setReplyContent(replyVO.getReplyContent()));
    }

    private ProductCommentDO validateCommentExists(Long id) {
        ProductCommentDO productComment = productCommentMapper.selectById(id);
        if (productComment == null) {
            throw exception(COMMENT_NOT_EXISTS);
        }
        return productComment;
    }

    @Override
    public AppCommentStatisticsRespVO getCommentStatistics(Long spuId, Boolean visible) {
        return ProductCommentConvert.INSTANCE.convert(
                // 查询商品 id = spuId 的所有好评数量
                productCommentMapper.selectCountBySpuId(spuId, visible, AppCommentPageReqVO.GOOD_COMMENT),
                // 查询商品 id = spuId 的所有中评数量
                productCommentMapper.selectCountBySpuId(spuId, visible, AppCommentPageReqVO.MEDIOCRE_COMMENT),
                // 查询商品 id = spuId 的所有差评数量
                productCommentMapper.selectCountBySpuId(spuId, visible, AppCommentPageReqVO.NEGATIVE_COMMENT)
        );
    }

    @Override
    public List<AppProductCommentRespVO> getCommentList(Long spuId, Integer count) {
        return ProductCommentConvert.INSTANCE.convertList02(productCommentMapper.selectCommentList(spuId, count).getList());
    }

    @Override
    public PageResult<ProductCommentDO> getCommentPage(AppCommentPageReqVO pageVO, Boolean visible) {
        return productCommentMapper.selectPage(pageVO, visible);
    }

    @Override
    public PageResult<ProductCommentDO> getCommentPage(ProductCommentPageReqVO pageReqVO) {
        return productCommentMapper.selectPage(pageReqVO);
    }

}
