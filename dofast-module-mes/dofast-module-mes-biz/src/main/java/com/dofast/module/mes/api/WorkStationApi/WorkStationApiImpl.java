package com.dofast.module.mes.api.WorkStationApi;

import com.dofast.module.mes.api.WorkStationAPi.WorkStationApi;
import com.dofast.module.mes.api.WorkStationAPi.dto.WorkStationDTO;
import com.dofast.module.mes.convert.mdworkstation.MdWorkstationConvert;
import com.dofast.module.mes.dal.dataobject.mdworkstation.MdWorkstationDO;
import com.dofast.module.mes.dal.mysql.mdworkstation.MdWorkstationMapper;
import com.dofast.module.mes.service.mdworkstation.MdWorkstationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WorkStationApiImpl implements WorkStationApi {
    @Resource
    MdWorkstationService mdWorkstationService;

    @Resource
    MdWorkstationMapper mdWorkstationMapper;


    @Override
    public WorkStationDTO getWorkstation(Long id) {
        MdWorkstationDO mdWorkstationDO = mdWorkstationService.getMdWorkstation(id);
        return MdWorkstationConvert.INSTANCE.convert01(mdWorkstationDO);
    }

    @Override
    public WorkStationDTO getWorkstation(String workStationCode) {
        MdWorkstationDO mdWorkstationDO = mdWorkstationMapper.selectOne(MdWorkstationDO::getWorkstationCode, workStationCode);
        return MdWorkstationConvert.INSTANCE.convert01(mdWorkstationDO);
    }


}
