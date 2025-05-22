package com.dofast.module.mes.service.oracle;


import com.dofast.module.mes.dal.dataobject.oracle.oracle.HrDTO;

import java.util.List;

public interface HrOracleService {

    List<HrDTO> selectHrList();

}
