package com.dofast.module.mes.job;

import com.dofast.framework.common.exception.ServiceException;
import com.dofast.framework.common.util.object.ObjectUtils;
import com.dofast.framework.quartz.core.handler.JobHandler;
import com.dofast.framework.security.core.LoginUser;
import com.dofast.framework.tenant.core.context.TenantContextHolder;
import com.dofast.module.mes.dal.dataobject.oracle.oracle.HrDTO;
import com.dofast.module.mes.dal.mysql.hr.HrMysqlMapper;
import com.dofast.module.mes.service.oracle.HrOracleService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Component("HrJob")
public class HrJob implements JobHandler {

    @Resource
    private HrOracleService hrOracleService;

    @Resource
    private HrMysqlMapper hrMysqlMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public String execute(String param) throws Exception {
        //从 ERP 库，获取人员同步信息
        List<HrDTO> hrList = hrOracleService.selectHrList();

        //将 ERP 库中的 人员信息 同步 到 MES中
        for (int i = 0; i < hrList.size(); i++) {
            HrDTO dto = hrList.get(i);
            String tenantId = null;
            if (dto.getJudianQx().length() == 4) {
                //通过据点获取 租户号
                tenantId = hrMysqlMapper.getTenantId(dto.getJudian());
            } else {
                //集团的租户
                tenantId = "1";
            }
            HrDTO hrDTO = hrList.get(i);
            hrDTO.setTenant_id(tenantId + "");
            hrDTO.setAvatar("http://172.18.12.250:9000/ammes/userAvatar_userBlob58546_02365778b93347449525036575fda91e.");
            hrDTO.setPassword(passwordEncoder.encode("AA123456"));
            //判断该账号是否已经存在
            int num = hrMysqlMapper.findIfUserName(hrDTO.getUserName());
            if (num == 0) {
                hrDTO.setDeptNo(StringUtils.isNotEmpty(hrDTO.getDeptNo()) && NumberUtils.isCreatable(hrDTO.getDeptNo()) ? hrDTO.getDeptNo() : "1");
                hrMysqlMapper.insertToSystemUsers(hrDTO);
                //通过租户ID,获取系统ID
                List<String> sysIds = hrMysqlMapper.findToDj002(tenantId);
                if (CollectionUtils.isNotEmpty(sysIds)) {
                    for (int i1 = 0; i1 < sysIds.size(); i1++) {
                        String sysId = sysIds.get(i1);
                        hrDTO.setSysId(sysId);
                        hrMysqlMapper.insertToDj001(hrDTO);
                    }
                } else {
                    log.info("\n" + hrDTO.getUserName() + "该用户没有所属公司");
                    throw new ServiceException(500, "该用户没有所属公司");
                }
            } else {
                log.info("\n" + hrDTO.getUserName() + "账号已存在");
            }

        }
        return "HR Job Success";
    }
}
