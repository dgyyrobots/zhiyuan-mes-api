package com.dofast.module.promotion.service.diy;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.promotion.controller.admin.diy.vo.template.DiyTemplateCreateReqVO;
import com.dofast.module.promotion.controller.admin.diy.vo.template.DiyTemplatePageReqVO;
import com.dofast.module.promotion.controller.admin.diy.vo.template.DiyTemplatePropertyUpdateRequestVO;
import com.dofast.module.promotion.controller.admin.diy.vo.template.DiyTemplateUpdateReqVO;
import com.dofast.module.promotion.convert.diy.DiyPageConvert;
import com.dofast.module.promotion.convert.diy.DiyTemplateConvert;
import com.dofast.module.promotion.dal.dataobject.diy.DiyTemplatesDO;
import com.dofast.module.promotion.dal.mysql.diy.DiyTemplateMappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.promotion.enums.ErrorCodeConstants.*;

/**
 * 装修模板 Service 实现类
 *
 * @author owen
 */
@Service("DiyTemplateServiceImplA")
@Validated
public class DiyTemplateServiceImpl implements DiyTemplateService {

    @Resource
    private DiyTemplateMappers diyTemplatesMapper;
    @Resource
    private DiyPageService diyPageService;

    @Override
    public Long createDiyTemplate(DiyTemplateCreateReqVO createReqVO) {
        // 校验名称唯一
        validateNameUnique(null, createReqVO.getName());
        // 插入
        DiyTemplatesDO diyTemplate = DiyTemplateConvert.INSTANCE.convert(createReqVO);
        diyTemplate.setProperty("{}");
        diyTemplatesMapper.insert(diyTemplate);
        // 创建默认页面
        createDefaultPage(diyTemplate);
        // 返回
        return diyTemplate.getId();
    }

    /**
     * 创建模板下面的默认页面
     * 默认创建两个页面：首页、我的
     *
     * @param diyTemplate 模板对象
     */
    private void createDefaultPage(DiyTemplatesDO diyTemplate) {
        String remark = String.format("模板【%s】自动创建", diyTemplate.getName());
        diyPageService.createDiyPage(DiyPageConvert.INSTANCE.convertCreateVo(diyTemplate.getId(), "首页", remark));
        diyPageService.createDiyPage(DiyPageConvert.INSTANCE.convertCreateVo(diyTemplate.getId(), "我的", remark));
    }

    @Override
    public void updateDiyTemplate(DiyTemplateUpdateReqVO updateReqVO) {
        // 校验存在
        validateDiyTemplateExists(updateReqVO.getId());
        // 校验名称唯一
        validateNameUnique(updateReqVO.getId(), updateReqVO.getName());
        // 更新
        DiyTemplatesDO updateObj = DiyTemplateConvert.INSTANCE.convert(updateReqVO);
        diyTemplatesMapper.updateById(updateObj);
    }

    void validateNameUnique(Long id, String name) {
        if (StrUtil.isBlank(name)) {
            return;
        }
        DiyTemplatesDO template = diyTemplatesMapper.selectByName(name);
        if (template == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的模板
        if (id == null) {
            throw exception(DIY_TEMPLATE_NAME_USED, name);
        }
        if (!template.getId().equals(id)) {
            throw exception(DIY_TEMPLATE_NAME_USED, name);
        }
    }

    @Override
    public void deleteDiyTemplate(Long id) {
        // 校验存在
        DiyTemplatesDO diyTemplateDO = validateDiyTemplateExists(id);
        // 校验使用中
        if (BooleanUtil.isTrue(diyTemplateDO.getUsed())) {
            throw exception(DIY_TEMPLATE_USED_CANNOT_DELETE);
        }
        // 删除
        diyTemplatesMapper.deleteById(id);
    }

    private DiyTemplatesDO validateDiyTemplateExists(Long id) {
        DiyTemplatesDO diyTemplateDO = diyTemplatesMapper.selectById(id);
        if (diyTemplateDO == null) {
            throw exception(DIY_TEMPLATE_NOT_EXISTS);
        }
        return diyTemplateDO;
    }

    @Override
    public DiyTemplatesDO getDiyTemplate(Long id) {
        return diyTemplatesMapper.selectById(id);
    }

    @Override
    public PageResult<DiyTemplatesDO> getDiyTemplatePage(DiyTemplatePageReqVO pageReqVO) {
        return diyTemplatesMapper.selectPage(pageReqVO);
    }

    @Override
    public void useDiyTemplate(Long id) {
        // 校验存在
        validateDiyTemplateExists(id);
        // 已使用的更新为未使用
        DiyTemplatesDO used = diyTemplatesMapper.selectByUsed(true);
        if (used != null) {
            // 如果 id 相同，说明未发生变化
            if (used.getId().equals(id)) {
                return;
            }
            this.updateUsed(used.getId(), false, null);
        }
        // 更新为已使用
        this.updateUsed(id, true, LocalDateTime.now());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDiyTemplateProperty(DiyTemplatePropertyUpdateRequestVO updateReqVO) {
        // 校验存在
        validateDiyTemplateExists(updateReqVO.getId());
        // 更新模板属性
        DiyTemplatesDO updateObj = DiyTemplateConvert.INSTANCE.convert(updateReqVO);
        diyTemplatesMapper.updateById(updateObj);
    }

    /**
     * 更新模板是否使用
     *
     * @param id       模板编号
     * @param used     是否使用
     * @param usedTime 使用时间
     */
    private void updateUsed(Long id, Boolean used, LocalDateTime usedTime) {
        DiyTemplatesDO updateObj = new DiyTemplatesDO().setId(id)
                .setUsed(used).setUsedTime(usedTime);
        diyTemplatesMapper.updateById(updateObj);
    }

}
