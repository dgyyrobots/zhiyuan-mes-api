package com.dofast.module.member.service.signin;





import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.pojo.PageParam;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.object.ObjectUtils;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.member.api.user.MemberUserApi;
import com.dofast.module.member.api.user.dto.MemberUserRespDTO;
import com.dofast.module.member.controller.admin.signin.vo.record.MemberSignInRecordPageReqVO;
import com.dofast.module.member.controller.app.signin.vo.AppMemberSignInSummaryRespVO;
import com.dofast.module.member.dal.dataobject.signin.MemberSignInConfigDO;
import com.dofast.module.member.dal.dataobject.signin.MemberSignInRecordDO;
import com.dofast.module.member.dal.mysql.signin.MemberSignInConfigMapper;
import com.dofast.module.member.dal.mysql.signin.MemberSignInRecordMapper;
import com.dofast.module.member.enums.ErrorCodeConstants;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.dofast.framework.common.enums.CommonStatusEnum;
import com.dofast.framework.common.pojo.PageParam;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.date.DateUtils;
import com.dofast.framework.common.util.object.ObjectUtils;
import com.dofast.module.member.api.user.MemberUserApi;
import com.dofast.module.member.api.user.dto.MemberUserRespDTO;
import com.dofast.module.member.controller.admin.signin.vo.record.MemberSignInRecordPageReqVO;
import com.dofast.module.member.controller.app.signin.vo.record.AppMemberSignInRecordSummaryRespVO;
import com.dofast.module.member.convert.signin.MemberSignInRecordConvert;
import com.dofast.module.member.dal.dataobject.signin.MemberSignInConfigDO;
import com.dofast.module.member.dal.dataobject.signin.MemberSignInRecordDO;
import com.dofast.module.member.dal.mysql.signin.MemberSignInRecordMapper;



import com.dofast.module.member.enums.MemberExperienceBizTypeEnum;
import com.dofast.module.member.enums.point.MemberPointBizTypeEnum;
import com.dofast.module.member.service.level.MemberLevelService;
import com.dofast.module.member.service.point.MemberPointRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.util.CollectionUtils;


import org.springframework.util.CollectionUtils;

import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;


import java.time.temporal.ChronoUnit;
import java.util.Comparator;


import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.Set;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertSet;

import static com.dofast.module.member.enums.ErrorCodeConstants.SIGN_IN_RECORD_TODAY_EXISTS;




/**
 * 签到记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MemberSignInRecordServiceImpl implements MemberSignInRecordService {

    @Resource
    private MemberSignInRecordMapper signInRecordMapper;
    @Resource
    private MemberSignInConfigService signInConfigService;
    @Resource
    private MemberPointRecordService pointRecordService;
    @Resource
    private MemberLevelService memberLevelService;

    @Resource
    private MemberUserApi memberUserApi;

    @Override
    public AppMemberSignInRecordSummaryRespVO getSignInRecordSummary(Long userId) {
        // 1. 初始化默认返回信息
        AppMemberSignInRecordSummaryRespVO summary = new AppMemberSignInRecordSummaryRespVO();
        summary.setTotalDay(0);
        summary.setContinuousDay(0);
        summary.setTodaySignIn(false);

        // 2. 获取用户签到的记录数
        Long signCount = signInRecordMapper.selectCountByUserId(userId);
        if (ObjUtil.equal(signCount, 0L)) {
            return summary;
        }
        summary.setTotalDay(signCount.intValue()); // 设置总签到天数

        // 3. 校验当天是否有签到
        MemberSignInRecordDO lastRecord = signInRecordMapper.selectLastRecordByUserId(userId);
        if (lastRecord == null) {
            return summary;
        }
        summary.setTodaySignIn(DateUtils.isToday(lastRecord.getCreateTime()));

        // 4. 校验今天是否签到，没有签到则直接返回
        if (!summary.getTodaySignIn()) {
            return summary;
        }
        // 4.1. 判断连续签到天数
        // TODO @puhui999：连续签到，可以基于 lastRecord 的 day 和当前时间判断呀？按 day 统计连续签到天数可能不准确
        //      1. day 只是记录第几天签到的有可能不连续，比如第一次签到是周一，第二次签到是周三这样 lastRecord 的 day 为 2 但是并不是连续的两天
        //      2. day 超出签到规则的最大天数会重置到从第一天开始签到（我理解为开始下一轮，类似一周签到七天七天结束下周又从周一开始签到）
        // 1. 回复：周三签到，day 要归 1 呀。连续签到哈；
        List<MemberSignInRecordDO> signInRecords = signInRecordMapper.selectListByUserId(userId);
        signInRecords.sort(Comparator.comparing(MemberSignInRecordDO::getCreateTime).reversed()); // 根据签到时间倒序
        summary.setContinuousDay(calculateConsecutiveDays(signInRecords));
        return summary;
    }

    /**
     * 计算连续签到天数
     *
     * @param signInRecords 签到记录列表
     * @return int 连续签到天数
     */
    public int calculateConsecutiveDays(List<MemberSignInRecordDO> signInRecords) {
        int consecutiveDays = 1;  // 初始连续天数为1
        LocalDate previousDate = null;

        for (MemberSignInRecordDO record : signInRecords) {
            LocalDate currentDate = record.getCreateTime().toLocalDate();

            if (previousDate != null) {
                // 检查相邻两个日期是否连续
                if (currentDate.minusDays(1).isEqual(previousDate)) {
                    consecutiveDays++;
                } else {
                    // 如果日期不连续，停止遍历
                    break;
                }
            }

            previousDate = currentDate;
        }

        return consecutiveDays;
    }

    @Override
    public PageResult<MemberSignInRecordDO> getSignInRecordPage(MemberSignInRecordPageReqVO pageReqVO) {
        // 根据用户昵称查询出用户ids
        Set<Long> userIds = null;
        if (StringUtils.isNotBlank(pageReqVO.getNickname())) {
            List<MemberUserRespDTO> users = memberUserApi.getUserListByNickname(pageReqVO.getNickname());
            // 如果查询用户结果为空直接返回无需继续查询
            if (CollUtil.isEmpty(users)) {
                return PageResult.empty();
            }
            userIds = convertSet(users, MemberUserRespDTO::getId);
        }
        // 分页查询
        return signInRecordMapper.selectPage(pageReqVO, userIds);
    }

    @Override
    public PageResult<MemberSignInRecordDO> getSignRecordPage(Long userId, PageParam pageParam) {
        return signInRecordMapper.selectPage(userId, pageParam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MemberSignInRecordDO createSignRecord(Long userId) {
        // 1. 获取当前用户最近的签到
        MemberSignInRecordDO lastRecord = signInRecordMapper.selectLastRecordByUserId(userId);
        // 1.1. 判断是否重复签到
        validateSigned(lastRecord);

        // 2.1. 获取所有的签到规则
        List<MemberSignInConfigDO> signInConfigs = signInConfigService.getSignInConfigList(CommonStatusEnum.ENABLE.getStatus());
        // 2.2. 组合数据
        MemberSignInRecordDO record = MemberSignInRecordConvert.INSTANCE.convert(userId, lastRecord, signInConfigs);

        // 3. 插入签到记录
        signInRecordMapper.insert(record);

        // 4. 增加积分
        if (!ObjectUtils.equalsAny(record.getPoint(), null, 0)) {
            pointRecordService.createPointRecord(userId, record.getPoint(), MemberPointBizTypeEnum.SIGN, String.valueOf(record.getId()));
        }
        // 5. 增加经验
        if (!ObjectUtils.equalsAny(record.getExperience(), null, 0)) {
            memberLevelService.addExperience(userId, record.getExperience(), MemberExperienceBizTypeEnum.SIGN_IN, String.valueOf(record.getId()));
        }
        return record;
    }

    private void validateSigned(MemberSignInRecordDO signInRecordDO) {
        if (signInRecordDO == null) {
            return;
        }
        if (DateUtils.isToday(signInRecordDO.getCreateTime())) {
            throw exception(SIGN_IN_RECORD_TODAY_EXISTS);
        }
    }

}
