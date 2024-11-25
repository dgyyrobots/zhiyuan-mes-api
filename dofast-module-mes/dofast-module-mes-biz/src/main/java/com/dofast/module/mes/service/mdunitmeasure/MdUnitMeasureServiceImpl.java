package com.dofast.module.mes.service.mdunitmeasure;

import com.dofast.module.mes.controller.admin.mditem.vo.MdItemBaseVO;
import com.dofast.module.mes.dal.dataobject.mditem.MdItemDO;
import com.dofast.module.mes.dal.dataobject.mdunitconverse.MdUnitConverseDO;
import com.dofast.module.mes.dal.mysql.mdunitconverse.MdUnitConverseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;

import java.sql.*;
import java.util.*;

import com.dofast.module.mes.controller.admin.mdunitmeasure.vo.*;
import com.dofast.module.mes.dal.dataobject.mdunitmeasure.MdUnitMeasureDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.mes.convert.mdunitmeasure.MdUnitMeasureConvert;
import com.dofast.module.mes.dal.mysql.mdunitmeasure.MdUnitMeasureMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.mes.enums.ErrorCodeConstants.*;

/**
 * 单位 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MdUnitMeasureServiceImpl implements MdUnitMeasureService {

    @Resource
    private MdUnitMeasureMapper mdUnitMeasureMapper;

    @Resource
    private MdUnitConverseMapper mdUnitConverseMapper;

    @Override
    public Long createMdUnitMeasure(MdUnitMeasureCreateReqVO createReqVO) {
        // 插入
        MdUnitMeasureDO mdUnitMeasure = MdUnitMeasureConvert.INSTANCE.convert(createReqVO);
        mdUnitMeasureMapper.insert(mdUnitMeasure);
        // 返回
        return mdUnitMeasure.getId();
    }

    @Override
    public void updateMdUnitMeasure(MdUnitMeasureUpdateReqVO updateReqVO) {
        // 校验存在
        validateMdUnitMeasureExists(updateReqVO.getId());
        // 更新
        MdUnitMeasureDO updateObj = MdUnitMeasureConvert.INSTANCE.convert(updateReqVO);
        mdUnitMeasureMapper.updateById(updateObj);
    }

    @Override
    public void deleteMdUnitMeasure(Long id) {
        // 校验存在
        validateMdUnitMeasureExists(id);
        // 删除
        mdUnitMeasureMapper.deleteById(id);
    }

    private void validateMdUnitMeasureExists(Long id) {
        if (mdUnitMeasureMapper.selectById(id) == null) {
            throw exception(MD_UNIT_MEASURE_NOT_EXISTS);
        }
    }

    @Override
    public MdUnitMeasureDO getMdUnitMeasure(Long id) {
        return mdUnitMeasureMapper.selectById(id);
    }

    @Override
    public List<MdUnitMeasureDO> getMdUnitMeasureList(Collection<Long> ids) {
        return mdUnitMeasureMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MdUnitMeasureDO> getMdUnitMeasurePage(MdUnitMeasurePageReqVO pageReqVO) {
        return mdUnitMeasureMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MdUnitMeasureDO> getMdUnitMeasureList(MdUnitMeasureExportReqVO exportReqVO) {
        return mdUnitMeasureMapper.selectList(exportReqVO);
    }

    @Override
    public List<MdUnitMeasureDO> getMdUnitMeasureList(MdUnitMeasureListVO exportReqVO) {
        return mdUnitMeasureMapper.selectList(exportReqVO);
    }

    @Override
    public String initUnit() {
        // 初始化单位
        //TODO 从ERP获取物料信息
        String oracleUrl = "jdbc:oracle:thin:@//192.168.127.7:1521/TOPPRD";
        String oracleUser = "amuser";
        String oraclePassword = "am123456";
        try (Connection oracleConn = DriverManager.getConnection(oracleUrl, oracleUser, oraclePassword)) {
            String oracleQuery = "select distinct a.ooca001 as measureCode , a.ooca002 , a.ooca003 ,  b.oocal003 as measureName from DSDATA.OOCA_T a\n" +
                    "                    left join DSDATA.OOCAL_T b on  a.ooca001 = b.oocal001 and a.OOCAENT = b.OOCALENT\n" +
                    "                    where a.OOCAENT  = 100 and oocal002 = 'zh_CN'";

            String unitConverse = "select distinct oocc001 as measureCode, oocc002 as originCount , oocc003 as converCode , oocc004 as converCount , ooccstus as deleted from DSDATA.oocc_t\n" +
                    "where ooccent = 100";
            try {
                PreparedStatement oracleStmt = oracleConn.prepareStatement(oracleQuery);
                ResultSet rs = oracleStmt.executeQuery();

                PreparedStatement unitStmt = oracleConn.prepareStatement(unitConverse);
                ResultSet unitRs = unitStmt.executeQuery();

                // 单位信息
                List<MdUnitMeasureDO> unitToInsert = new ArrayList<>();
                List<MdUnitMeasureDO> unitToUp = new ArrayList<>();

                // 单位换算
                List<MdUnitConverseDO> converseToInsert = new ArrayList<>();
                List<MdUnitConverseDO> converseToUp = new ArrayList<>();
                while (rs.next()) {
                    String unitCode = rs.getString("measureCode");
                    // 校验当前单位信息是否存在
                    MdUnitMeasureDO unitInfo = mdUnitMeasureMapper.selectOne(MdUnitMeasureDO::getMeasureCode, unitCode);
                    if (unitInfo != null) {
                        // 已存在, 修改当前单位信息
                        unitInfo.setMeasureCode(rs.getString("measureCode")); // 单位编码
                        unitInfo.setMeasureName(rs.getString("measureName")); // 单位名称
                        unitInfo.setEnableFlag("Y"); // 启用标识
                        unitInfo.setDeleted(false); // 默认使用
                        unitToUp.add(unitInfo);
                    } else {
                        MdUnitMeasureDO unit = new MdUnitMeasureDO();
                        unit.setMeasureCode(rs.getString("measureCode")); // 单位编码
                        unit.setMeasureName(rs.getString("measureName")); // 单位名称
                        unit.setEnableFlag("Y"); // 启用标识
                        unit.setDeleted(false); // 默认使用
                        unitToInsert.add(unit);
                    }
                }
                if (!unitToInsert.isEmpty() && unitToInsert.size() != 0) {
                    mdUnitMeasureMapper.insertBatch(unitToInsert);
                }
                if (!unitToUp.isEmpty() && unitToUp.size() != 0) {
                    mdUnitMeasureMapper.updateBatch(unitToUp);
                }
                System.out.println("单位基础数据迁移成功!");
                // 开始获取单位换算信息
                while (unitRs.next()) {
                    String measureCode = unitRs.getString("measureCode");
                    String converCode = unitRs.getString("converCode");
                    // 校验当前原编码与转换单位是否存在
                    MdUnitConverseDO conver = new MdUnitConverseDO();
                    conver.setMeasureCode(measureCode);
                    conver.setConverseCode(converCode);
                    MdUnitConverseDO converInfo = mdUnitConverseMapper.selectOne(MdUnitConverseDO::getMeasureCode, measureCode, MdUnitConverseDO::getConverseCode, converCode);
                    if (converInfo != null) {
                        // 已存在, 修改当前单位信息
                        converInfo.setOriginCount(unitRs.getBigDecimal("originCount")); // 原单位数量
                        converInfo.setConverseCount(unitRs.getBigDecimal("converCount")); // 转换单位数量
                        converseToUp.add(converInfo);
                    } else {
                        MdUnitConverseDO addInfo = new MdUnitConverseDO();
                        addInfo.setMeasureCode(measureCode);
                        addInfo.setConverseCode(converCode);
                        addInfo.setOriginCount(unitRs.getBigDecimal("originCount")); // 原单位数量
                        addInfo.setConverseCount(unitRs.getBigDecimal("converCount")); // 转换单位数量
                        addInfo.setDeleted(unitRs.getString("deleted") == "Y" ? false : true); // 是否禁用
                        converseToInsert.add(addInfo);
                    }
                }
                if (!converseToInsert.isEmpty() || converseToInsert.size() != 0) {
                    mdUnitConverseMapper.insertBatch(converseToInsert);
                }
                if(!converseToUp.isEmpty()  || converseToUp.size()!=0){
                    mdUnitConverseMapper.updateBatch(converseToUp);
                }
                System.out.println("单位换算数据迁移成功!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("异常!");
            }
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("异常!");
        }
        return "success";
    }

}
