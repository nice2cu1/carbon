package com.neu.carbon.distribusion.service;

import java.util.List;
import com.neu.carbon.distribusion.domain.DistribusionTotalEmission;

/**
 * 配额管理Service接口
 *
 * @author neuedu
 * @date 2024-01-24
 */
public interface IDistribusionTotalEmissionService
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
     * 批量删除配额管理
     *
     * @param ids 需要删除的配额管理ID
     * @return 结果
     */
    public int deleteDistribusionTotalEmissionByIds(Long[] ids);

    /**
     * 删除配额管理信息
     *
     * @param id 配额管理ID
     * @return 结果
     */
    public int deleteDistribusionTotalEmissionById(Long id);

}
