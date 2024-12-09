package com.neu.carbon.distribusion.mapper;

import java.util.List;
import com.neu.carbon.distribusion.domain.DistribusionTotalEmission;

/**
 * 配额管理Mapper接口
 *
 * @author neuedu
 * @date 2024-01-24
 */
public interface DistribusionTotalEmissionMapper
{
    /**
     * 查询配额管理
     *
     * @param id 配额管理ID
     * @return 配额管理
     */
    public DistribusionTotalEmission selectDistribusionTotalEmissionById(Long id);

    /**
     * 查询配额管理列表
     *
     * @param distribusionTotalEmission 配额管理
     * @return 配额管理集合
     */
    public List<DistribusionTotalEmission> selectDistribusionTotalEmissionList(DistribusionTotalEmission distribusionTotalEmission);

    /**
     * 新增配额管理
     *
     * @param distribusionTotalEmission 配额管理
     * @return 结果
     */
    public int insertDistribusionTotalEmission(DistribusionTotalEmission distribusionTotalEmission);

    /**
     * 修改配额管理
     *
     * @param distribusionTotalEmission 配额管理
     * @return 结果
     */
    public int updateDistribusionTotalEmission(DistribusionTotalEmission distribusionTotalEmission);

    /**
     * 删除配额管理
     *
     * @param id 配额管理ID
     * @return 结果
     */
    public int deleteDistribusionTotalEmissionById(Long id);

    /**
     * 批量删除配额管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDistribusionTotalEmissionByIds(Long[] ids);

}
