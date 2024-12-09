package com.neu.carbon.mes.mapper;

import java.util.List;
import com.neu.carbon.mes.domain.MesProductPlanDetail;

/**
 * 生产计划明细Mapper接口
 * 
 * @author neuedu
 * @date 2022-07-12
 */
public interface MesProductPlanDetailMapper 
{
    /**
     * 查询生产计划明细
     * 
     * @param id 生产计划明细ID
     * @return 生产计划明细
     */
    public MesProductPlanDetail selectMesProductPlanDetailById(Long id);

    /**
     * 查询生产计划明细列表
     * 
     * @param mesProductPlanDetail 生产计划明细
     * @return 生产计划明细集合
     */
    public List<MesProductPlanDetail> selectMesProductPlanDetailList(MesProductPlanDetail mesProductPlanDetail);

    /**
     * 新增生产计划明细
     * 
     * @param mesProductPlanDetail 生产计划明细
     * @return 结果
     */
    public int insertMesProductPlanDetail(MesProductPlanDetail mesProductPlanDetail);

    /**
     * 修改生产计划明细
     * 
     * @param mesProductPlanDetail 生产计划明细
     * @return 结果
     */
    public int updateMesProductPlanDetail(MesProductPlanDetail mesProductPlanDetail);

    /**
     * 删除生产计划明细
     * 
     * @param id 生产计划明细ID
     * @return 结果
     */
    public int deleteMesProductPlanDetailById(Long id);

    /**
     * 批量删除生产计划明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMesProductPlanDetailByIds(Long[] ids);
}