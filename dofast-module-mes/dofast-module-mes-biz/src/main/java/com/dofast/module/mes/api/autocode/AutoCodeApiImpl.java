package com.dofast.module.mes.api.autocode;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.module.mes.controller.admin.Autocodepart.vo.AutoCodePartListVO;
import com.dofast.module.mes.controller.admin.Autocoderule.vo.AutoCodeRuleListVO;
import com.dofast.module.mes.controller.admin.autocoderesult.vo.AutoCodeResultCreateReqVO;
import com.dofast.module.mes.controller.admin.autocoderesult.vo.AutoCodeResultListVO;
import com.dofast.module.mes.controller.admin.autocoderesult.vo.AutoCodeResultUpdateReqVO;
import com.dofast.module.mes.convert.autocoderesult.AutoCodeResultConvert;
import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;
import com.dofast.module.mes.dal.dataobject.Autocoderule.AutoCodeRuleDO;
import com.dofast.module.mes.dal.dataobject.autocoderesult.AutoCodeResultDO;
import com.dofast.module.mes.enums.PartTypeEnum;
import com.dofast.module.mes.handler.PartTypeHandler;
import com.dofast.module.mes.service.Autocodepart.AutoCodePartService;
import com.dofast.module.mes.service.Autocoderule.AutoCodeRuleService;
import com.dofast.module.mes.service.autocoderesult.AutoCodeResultService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoCodeApiImpl implements AutoCodeApi{
    public static ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();
    @Resource
    AutoCodeRuleService autoCodeRuleService;

    @Resource
    private AutoCodePartService autoCodePartService;

    @Autowired
    private PartTypeHandler partTypeHandler;

    @Resource
    AutoCodeResultService autoCodeResultService;

    private String lastSerialNo;

    @Override
    public String genSerialCode(String ruleCode, String inputCharacter) {
        AutoCodeRuleListVO listVO = new AutoCodeRuleListVO();
        listVO.setRuleCode(ruleCode);
        AutoCodeRuleDO rule = autoCodeRuleService.getOne(ruleCode);


        AutoCodePartListVO partListVO = new AutoCodePartListVO();
        partListVO.setRuleId(rule.getId());
        List<AutoCodePartDO> parts = autoCodePartService.getAutoCodePartList(partListVO);

        List<AutoCodePartDO> collect = parts.stream().filter(part-> PartTypeEnum.PART_TYPE_SERIALNO.getCode().equals(part.getPartType())).collect(Collectors.toList());

        Assert.isTrue(collect.size()<2,"编码规则[{}]流水号方式的组成只能存在一个",ruleCode);

        StringBuilder buff = new StringBuilder();

        parts.forEach(codePart ->{
            codePart.setInputCharacter(inputCharacter);
            //根据当前组成部分，获取当前组成部分的结果
            String partStr = partTypeHandler.choiceExecute(codePart);

            //如果是流水号部分，则进行记录
            if(StringUtils.equals(codePart.getPartType(),PartTypeEnum.PART_TYPE_SERIALNO.getCode())){
                lastSerialNo = partStr;
            }
            //将获取到的部分组装进整体编码中
            buff.append(partStr);
        });
        Assert.notBlank(buff.toString(),"规则：[{}]生成的编码为空！",ruleCode);

        String autoCode = paddingStr(rule,buff);

        //将生成结果保存到数据库
        saveAutoCodeResult(rule,autoCode,inputCharacter);
        return autoCode;
    }

    /**
     * 根据编码规则的配置进行补齐操作
     * @param rule
     * @param sb
     * @return
     */
    private String paddingStr(AutoCodeRuleDO rule,StringBuilder sb){
        String isPadding = rule.getIsPadded();
        if("Y".equals(isPadding)){
            int maxLength = rule.getMaxLength();
            String paddingChar = rule.getPaddedChar();
            StringBuilder resultStr = new StringBuilder();
            long length = maxLength - sb.length();
            Assert.isTrue(maxLength>sb.length(),"生成的编码[{}]已经超出规则中配置的最大长度：[{}]",sb.toString(),maxLength);

            if("L".equals(rule.getPaddedMethod())){
                //左补齐
                //使用指定字符补齐左侧后,再将生成的编码添加到右侧
                for(;length>0;length --){
                    resultStr.append(paddingChar);
                }
                resultStr.append(sb);
            }else{
                //右补齐
                //将生成的编码添加到左侧后,再使用指定字符补齐右侧
                resultStr.append(sb);
                for(;length>0;length --){
                    resultStr.append(paddingChar);
                }
            }
            return resultStr.toString();
        }
        return sb.toString(); //如果不需要补齐，则直接返回
    }

    private void saveAutoCodeResult(AutoCodeRuleDO rule,String autoCode,String inputChar){
        Boolean flag = threadLocal.get(); //针对当前线程的判断 flag = true则数据库中没有当前规则的生成记录
        if(flag !=null && flag){
            AutoCodeResultCreateReqVO rs = new AutoCodeResultCreateReqVO();
            rs.setRuleId(rule.getId());
            rs.setGenDate(DateUtil.format(LocalDateTime.now(),"yyyyMMddHHmmss"));
            rs.setLastResult(autoCode);
            rs.setGenIndex(1);
            rs.setLastSerialNo(Integer.parseInt(lastSerialNo));
            rs.setLastInputChar(inputChar);
            autoCodeResultService.createAutoCodeResult(rs);
        }else{
            //直接更新对应的记录（我们默认非流水号模式下一个RULE_CODE只有一种方式）
            AutoCodeResultListVO bo = new AutoCodeResultListVO();
            bo.setRuleId(rule.getId());
            List<AutoCodeResultDO> results = autoCodeResultService.getAutoCodeResultList(bo);
            Assert.notEmpty(results,"未查询到规则{[]}对应的结果记录",rule.getRuleCode());
            AutoCodeResultDO rs = results.get(0);
            rs.setLastResult(autoCode);
            rs.setGenDate(DateUtil.format(LocalDateTime.now(),"yyyyMMddHHmmss"));
            rs.setGenIndex(rs.getGenIndex()+1);
            rs.setLastSerialNo(Integer.parseInt(lastSerialNo));
            rs.setLastInputChar(inputChar);

            AutoCodeResultUpdateReqVO updateReqVO = new AutoCodeResultUpdateReqVO();
            updateReqVO.setId(rs.getId());
            updateReqVO.setGenDate(rs.getGenDate());
            updateReqVO.setGenIndex(rs.getGenIndex());
            updateReqVO.setLastInputChar(rs.getLastInputChar());
            updateReqVO.setLastSerialNo(rs.getLastSerialNo());
            updateReqVO.setLastResult(autoCode);
            updateReqVO.setRuleId(rs.getRuleId());
            updateReqVO.setAttr1(rs.getAttr1());
            updateReqVO.setAttr2(rs.getAttr2());
            updateReqVO.setAttr4(rs.getAttr4());
            updateReqVO.setRemark(rs.getRemark());

            autoCodeResultService.updateAutoCodeResult(updateReqVO);
        }

    }
}
