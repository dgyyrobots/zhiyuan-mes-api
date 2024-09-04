package com.dofast.module.member.dal.mysql.user;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;


import com.dofast.module.member.controller.admin.user.vo.MemberUserExportReqVO;


import com.dofast.module.member.controller.admin.user.vo.MemberUserExportReqVO;

import com.dofast.module.member.controller.admin.user.vo.MemberUserPageReqVO;
import com.dofast.module.member.dal.dataobject.user.MemberUserDO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员 User Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MemberUserMapper extends BaseMapperX<MemberUserDO> {

    default MemberUserDO selectByMobile(String mobile) {
        return selectOne(MemberUserDO::getMobile, mobile);
    }

    default List<MemberUserDO> selectListByNicknameLike(String nickname) {
        return selectList(new LambdaQueryWrapperX<MemberUserDO>()
                .likeIfPresent(MemberUserDO::getNickname, nickname));
    }

    default PageResult<MemberUserDO> selectPage(MemberUserPageReqVO reqVO) {
        // 处理 tagIds 过滤条件
        String tagIdSql = "";
        if (CollUtil.isNotEmpty(reqVO.getTagIds())) {
            tagIdSql = reqVO.getTagIds().stream()
                    .map(tagId -> "FIND_IN_SET(" + tagId + ", tag_ids)")
                    .collect(Collectors.joining(" OR "));
        }
        // 分页查询
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberUserDO>()
                .likeIfPresent(MemberUserDO::getMobile, reqVO.getMobile())
                .betweenIfPresent(MemberUserDO::getLoginDate, reqVO.getLoginDate())
                .eqIfPresent(MemberUserDO::getPosCode, reqVO.getPosCode())
                .eqIfPresent(MemberUserDO::getRefType, reqVO.getRefType())
                .eqIfPresent(MemberUserDO::getShareMember, reqVO.getShareMember())
                .eqIfPresent(MemberUserDO::getSourceMember, reqVO.getSourceMember())
                .eqIfPresent(MemberUserDO::getFenxiaoId, reqVO.getFenxiaoId())
                .eqIfPresent(MemberUserDO::getIsFenxiao, reqVO.getIsFenxiao())
                .eqIfPresent(MemberUserDO::getLevelId, reqVO.getMemberLevel())
                .likeIfPresent(MemberUserDO::getMemberLevelName, reqVO.getMemberLevelName())
                .eqIfPresent(MemberUserDO::getMemberLabel, reqVO.getMemberLabel())
                .likeIfPresent(MemberUserDO::getMemberLabelName, reqVO.getMemberLabelName())
                .eqIfPresent(MemberUserDO::getBirthday, reqVO.getBirthday())
                .eqIfPresent(MemberUserDO::getPoint, reqVO.getPoint())
                .eqIfPresent(MemberUserDO::getBalance, reqVO.getBalance())
                .eqIfPresent(MemberUserDO::getExperience, reqVO.getExperience())
                .eqIfPresent(MemberUserDO::getBalanceMoney, reqVO.getBalanceMoney())
                .eqIfPresent(MemberUserDO::getIsAuth, reqVO.getIsAuth())
                .eqIfPresent(MemberUserDO::getIsMember, reqVO.getIsMember())
                .likeIfPresent(MemberUserDO::getNickname, reqVO.getNickname())
                .betweenIfPresent(MemberUserDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MemberUserDO::getLevelId, reqVO.getLevelId())
                .eqIfPresent(MemberUserDO::getGroupId, reqVO.getGroupId())
                .and(reqVO.getStatus()!=null,v->{
                        v.eq(MemberUserDO::getStatus,reqVO.getStatus());
                })
                .apply(StrUtil.isNotEmpty(tagIdSql), tagIdSql)
                .orderByDesc(MemberUserDO::getId));
    }



    default List<MemberUserDO> selectList(MemberUserExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MemberUserDO>()
                .likeIfPresent(MemberUserDO::getNickname, reqVO.getNickname())
                .eqIfPresent(MemberUserDO::getAvatar, reqVO.getAvatar())
                .eqIfPresent(MemberUserDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MemberUserDO::getMobile, reqVO.getMobile())
                .eqIfPresent(MemberUserDO::getPassword, reqVO.getPassword())
                .eqIfPresent(MemberUserDO::getRegisterIp, reqVO.getRegisterIp())
                .eqIfPresent(MemberUserDO::getLoginIp, reqVO.getLoginIp())
                .betweenIfPresent(MemberUserDO::getLoginDate, reqVO.getLoginDate())
                .eqIfPresent(MemberUserDO::getPosCode, reqVO.getPosCode())
                .eqIfPresent(MemberUserDO::getRefType, reqVO.getRefType())
                .eqIfPresent(MemberUserDO::getShareMember, reqVO.getShareMember())
                .eqIfPresent(MemberUserDO::getSourceMember, reqVO.getSourceMember())
                .eqIfPresent(MemberUserDO::getFenxiaoId, reqVO.getFenxiaoId())
                .eqIfPresent(MemberUserDO::getIsFenxiao, reqVO.getIsFenxiao())
                .eqIfPresent(MemberUserDO::getLevelId, reqVO.getMemberLevel())
                .likeIfPresent(MemberUserDO::getMemberLevelName, reqVO.getMemberLevelName())
                .eqIfPresent(MemberUserDO::getMemberLabel, reqVO.getMemberLabel())
                .likeIfPresent(MemberUserDO::getMemberLabelName, reqVO.getMemberLabelName())
                .eqIfPresent(MemberUserDO::getBirthday, reqVO.getBirthday())
                .eqIfPresent(MemberUserDO::getPoint, reqVO.getPoint())
                .eqIfPresent(MemberUserDO::getBalance, reqVO.getBalance())
                .eqIfPresent(MemberUserDO::getExperience, reqVO.getExperience())
                .eqIfPresent(MemberUserDO::getBalanceMoney, reqVO.getBalanceMoney())
                .eqIfPresent(MemberUserDO::getIsAuth, reqVO.getIsAuth())
                .eqIfPresent(MemberUserDO::getIsMember, reqVO.getIsMember())
                .betweenIfPresent(MemberUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MemberUserDO::getId));

    }




    default Long selectCountByGroupId(Long groupId) {
        return selectCount(MemberUserDO::getGroupId, groupId);
    }

    default Long selectCountByLevelId(Long levelId) {
        return selectCount(MemberUserDO::getLevelId, levelId);
    }

    default Long selectCountByTagId(Long tagId) {
        return selectCount(new LambdaQueryWrapperX<MemberUserDO>()
                .apply("FIND_IN_SET({0}, tag_ids)", tagId));
    }

    /**
     * 更新用户积分（增加）
     *
     * @param id        用户编号
     * @param incrCount 增加积分（正数）
     */
    default void updatePointIncr(Long id, Integer incrCount) {
        Assert.isTrue(incrCount > 0);
        LambdaUpdateWrapper<MemberUserDO> lambdaUpdateWrapper = new LambdaUpdateWrapper<MemberUserDO>()
                .setSql(" point = point + " + incrCount)
                .eq(MemberUserDO::getId, id);
        update(null, lambdaUpdateWrapper);
    }

    /**
     * 更新用户积分（减少）
     *
     * @param id        用户编号
     * @param incrCount 增加积分（负数）
     * @return 更新行数
     */
    default int updatePointDecr(Long id, Integer incrCount) {
        Assert.isTrue(incrCount < 0);
        LambdaUpdateWrapper<MemberUserDO> lambdaUpdateWrapper = new LambdaUpdateWrapper<MemberUserDO>()
                .setSql(" point = point + " + incrCount) // 负数，所以使用 + 号
                .eq(MemberUserDO::getId, id);
        return update(null, lambdaUpdateWrapper);
    }




}
