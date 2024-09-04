package com.dofast.module.mes.controller.pad.mdworkstation;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.dofast.framework.common.pad.controller.PadBaseController;
import com.dofast.framework.common.pad.util.PadStringUtils;
import com.dofast.framework.common.pojo.AjaxResult;
import com.dofast.framework.common.pojo.UserConstants;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.framework.web.core.util.WebFrameworkUtils;
import com.dofast.module.mes.controller.admin.mdworkstation.vo.MdWorkstationExportReqVO;
import com.dofast.module.mes.controller.admin.userworkstation.vo.UserWorkstationCreateReqVO;
import com.dofast.module.mes.controller.admin.userworkstation.vo.UserWorkstationExportReqVO;
import com.dofast.module.mes.dal.dataobject.mdworkstation.MdWorkstationDO;
import com.dofast.module.mes.dal.dataobject.mdworkstationworker.MdWorkstationWorkerDO;
import com.dofast.module.mes.dal.dataobject.userworkstation.UserWorkstationDO;
import com.dofast.module.mes.service.mdworkstation.MdWorkstationService;
import com.dofast.module.mes.service.mdworkstationworker.MdWorkstationWorkerService;
import com.dofast.module.mes.service.userworkstation.UserWorkstationService;
import com.dofast.module.pro.api.WorkrecordApi.WorkrecordApi;
import com.dofast.module.pro.api.WorkrecordApi.dto.WorkrecordDTO;
import com.dofast.module.system.api.user.AdminUserApi;
import com.dofast.module.system.api.user.dto.AdminUserRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/mobile/md/workstation")
@Validated
@Tag(name = "PAD管理后台 - 工作站")
public class PadMdWorkstationController extends PadBaseController {
    @Autowired
    private AdminUserApi adminUserApi;

    @Autowired
    private MdWorkstationService mdWorkstationService;

    @Autowired
    WorkrecordApi workrecordApi;

    @Autowired
    private UserWorkstationService userWorkstationService;
    @Autowired
    private MdWorkstationWorkerService workstationWorkerService;

    @Operation(summary = "工作站查询接口")
    @GetMapping("/getWorkstationList")
    public AjaxResult getWorkstationList(MdWorkstationDO mdWorkstation){
        MdWorkstationExportReqVO mdWorkstationExportReqVO = BeanUtil.toBean(mdWorkstation, MdWorkstationExportReqVO.class);
        AdminUserRespDTO adminUserRespDTO = adminUserApi.getUser(WebFrameworkUtils.getLoginUserId());
        if (CollectionUtil.isEmpty(adminUserRespDTO.getPostIds())) {
            return AjaxResult.success(new ArrayList<>());
        }
        List<MdWorkstationWorkerDO>  workstationWorkers = workstationWorkerService.getMdWorkstationWorkerListByPostId(adminUserRespDTO.getPostIds());
        if (CollectionUtil.isEmpty(workstationWorkers)) {
            return AjaxResult.success(new ArrayList<>());
        }
        Set<Long> workstationIds= CollectionUtils.convertSet(workstationWorkers, MdWorkstationWorkerDO::getWorkstationId);
        mdWorkstationExportReqVO.setIds(new ArrayList<>(workstationIds));
        List<MdWorkstationDO> list = mdWorkstationService.getMdWorkstationList(mdWorkstationExportReqVO);
        return AjaxResult.success(list);
    }

    @Operation(summary = "获取当前用户绑定的工作站")
    @GetMapping("/getMyWorkstation")
    public AjaxResult getBindWorkstation(HttpServletRequest request){
        Long loginUserId = WebFrameworkUtils.getLoginUserId(request);
        UserWorkstationExportReqVO param = new UserWorkstationExportReqVO();
        param.setUserId(loginUserId);
        List<UserWorkstationDO> uw = userWorkstationService.getUserWorkstationList(param);
        if(!CollectionUtil.isEmpty(uw)){
            return AjaxResult.success(uw.get(0));
        }
        return AjaxResult.success();
    }

    @Operation(summary = "上工/下工记录")
    @PutMapping()
    @Transactional
    public AjaxResult bindWorkstation(@RequestBody WorkrecordDTO workrecord){
        MdWorkstationExportReqVO param = new MdWorkstationExportReqVO();
        param.setWorkstationCode(workrecord.getWorkstationCode());
        List<MdWorkstationDO> workstations = mdWorkstationService.getMdWorkstationList(param);
        MdWorkstationDO workstation = null;
        if(!CollectionUtil.isEmpty(workstations)){
            workstation = workstations.get(0);
        }

        if(!PadStringUtils.isNotNull(workstation)){
            return AjaxResult.error("未能找到对应的工作站");
        }

        AdminUserRespDTO user = adminUserApi.getUser(workrecord.getUserId());
        workrecord.setUserName(user.getUsername());
        workrecord.setWorkstationId(workstation.getId());
        workrecord.setWorkstationName(workstation.getWorkstationName());
        workrecordApi.insertWorkrecord(workrecord);

        UserWorkstationDO uw = new UserWorkstationDO();
        uw.setUserId(workrecord.getUserId());
        uw.setUserName(workrecord.getUserName());
        uw.setNickName(workrecord.getNickName());
        uw.setWorkstationId(workstation.getId());
        uw.setWorkstationCode(workstation.getWorkstationCode());
        uw.setWorkstationName(workstation.getWorkstationName());

        if(UserConstants.YES.equals(workrecord.getOperationFlag())){
            //如果是绑定
            userWorkstationService.deleteUserWorkstation(workrecord.getUserName());

            UserWorkstationCreateReqVO userWorkstationCreateReqVO = BeanUtil.toBean(uw, UserWorkstationCreateReqVO.class);
            userWorkstationService.createUserWorkstation(userWorkstationCreateReqVO);
        }else{
            userWorkstationService.deleteUserWorkstation(workrecord.getUserName());
        }

        return AjaxResult.success();
    }

}
