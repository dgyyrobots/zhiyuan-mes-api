package com.dofast.module.deliver.convert.expresselectronicsheet;

import java.util.*;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo.*;
import com.dofast.module.deliver.dal.dataobject.expresselectronicsheet.ExpressElectronicsheetDO;

/**
 * 电子面单 Convert
 *
 * @author a1
 */
@Mapper
public interface ExpressElectronicsheetConvert {

    ExpressElectronicsheetConvert INSTANCE = Mappers.getMapper(ExpressElectronicsheetConvert.class);

    default ExpressElectronicsheetDO convert(ExpressElectronicsheetCreateReqVO bean){
        if ( bean == null ) {
            return null;
        }

        ExpressElectronicsheetDO.ExpressElectronicsheetDOBuilder expressElectronicsheetDO = ExpressElectronicsheetDO.builder();

        expressElectronicsheetDO.companyName( bean.getCompanyName() );
        expressElectronicsheetDO.type( bean.getType() );
        expressElectronicsheetDO.info( bean.getInfo() );
        expressElectronicsheetDO.kdnCode( bean.getKdnCode() );
        expressElectronicsheetDO.template(JSON.toJSONString(bean.getTemplate()));
        expressElectronicsheetDO.config( JSON.toJSONString(bean.getConfig()) );

        return expressElectronicsheetDO.build();
    }

    default ExpressElectronicsheetDO convert(ExpressElectronicsheetUpdateReqVO bean){
        if ( bean == null ) {
            return null;
        }

        ExpressElectronicsheetDO.ExpressElectronicsheetDOBuilder expressElectronicsheetDO = ExpressElectronicsheetDO.builder();

        expressElectronicsheetDO.id( bean.getId() );
        expressElectronicsheetDO.companyName( bean.getCompanyName() );
        expressElectronicsheetDO.type( bean.getType() );
        expressElectronicsheetDO.info( bean.getInfo() );
        expressElectronicsheetDO.kdnCode( bean.getKdnCode() );
        expressElectronicsheetDO.template( JSON.toJSONString(bean.getTemplate()));
        expressElectronicsheetDO.config( JSON.toJSONString(bean.getConfig() ));

        return expressElectronicsheetDO.build();
    }

    default ExpressElectronicsheetRespVO convert(ExpressElectronicsheetDO bean){
        if ( bean == null ) {
            return null;
        }

        ExpressElectronicsheetRespVO expressElectronicsheetRespVO = new ExpressElectronicsheetRespVO();

        expressElectronicsheetRespVO.setCompanyName( bean.getCompanyName() );
        expressElectronicsheetRespVO.setType( bean.getType() );
        expressElectronicsheetRespVO.setInfo( bean.getInfo() );
        expressElectronicsheetRespVO.setKdnCode( bean.getKdnCode() );
        expressElectronicsheetRespVO.setTemplate(  JSON.parseObject(bean.getTemplate(),Template.class));
        expressElectronicsheetRespVO.setConfig( JSON.parseObject(bean.getConfig(),Config.class) );
        expressElectronicsheetRespVO.setId( bean.getId() );
        expressElectronicsheetRespVO.setCreateTime( bean.getCreateTime() );

        return expressElectronicsheetRespVO;
    }

    List<ExpressElectronicsheetRespVO> convertList(List<ExpressElectronicsheetDO> list);

    PageResult<ExpressElectronicsheetRespVO> convertPage(PageResult<ExpressElectronicsheetDO> page);

    List<ExpressElectronicsheetExcelVO> convertList02(List<ExpressElectronicsheetDO> list);

}
