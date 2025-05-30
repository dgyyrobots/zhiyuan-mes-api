package com.dofast.module.member.controller.app.signin;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageParam;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.security.core.annotations.PreAuthenticated;
import com.dofast.module.member.controller.app.signin.vo.record.AppMemberSignInRecordRespVO;
import com.dofast.module.member.controller.app.signin.vo.record.AppMemberSignInRecordSummaryRespVO;
import com.dofast.module.member.convert.signin.MemberSignInRecordConvert;
import com.dofast.module.member.dal.dataobject.signin.MemberSignInRecordDO;
import com.dofast.module.member.service.signin.MemberSignInRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 签到记录")
@RestController
@RequestMapping("/member/sign-in/record")
@Validated
public class AppMemberSignInRecordController {

    @Resource
    private MemberSignInRecordService signInRecordService;



    // TODO 芋艿：临时 mock => UserSignController.getUserInfo


    // TODO 芋艿：临时 mock => UserSignController.getUserInfo

    @GetMapping("/get-summary")
    @Operation(summary = "获得个人签到统计")
    @PreAuthenticated
    public CommonResult<AppMemberSignInRecordSummaryRespVO> getSignInRecordSummary() {




        AppMemberSignInRecordSummaryRespVO respVO = new AppMemberSignInRecordSummaryRespVO();
        if (false) {
            respVO.setTotalDay(100);
            respVO.setContinuousDay(5);
            respVO.setTodaySignIn(true);
        } else {
            respVO.setTotalDay(100);
            respVO.setContinuousDay(10);
            respVO.setTodaySignIn(false);
        }

        return success(signInRecordService.getSignInRecordSummary(getLoginUserId()));


    }

    @PostMapping("/create")
    @Operation(summary = "签到")
    @PreAuthenticated
    public CommonResult<AppMemberSignInRecordRespVO> createSignInRecord() {
        MemberSignInRecordDO recordDO = signInRecordService.createSignRecord(getLoginUserId());
        return success(MemberSignInRecordConvert.INSTANCE.coverRecordToAppRecordVo(recordDO));
    }

    @GetMapping("/page")
    @Operation(summary = "获得签到记录分页")
    @PreAuthenticated
    public CommonResult<PageResult<AppMemberSignInRecordRespVO>> getSignRecordPage(PageParam pageParam) {
        PageResult<MemberSignInRecordDO> pageResult = signInRecordService.getSignRecordPage(getLoginUserId(), pageParam);
        return success(MemberSignInRecordConvert.INSTANCE.convertPage02(pageResult));
    }

}
