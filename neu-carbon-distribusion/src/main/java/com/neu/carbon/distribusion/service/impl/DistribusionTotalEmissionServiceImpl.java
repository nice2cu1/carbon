package com.neu.carbon.distribusion.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.distribusion.mapper.DistribusionTotalEmissionMapper;
import com.neu.carbon.distribusion.domain.DistribusionTotalEmission;
import com.neu.carbon.distribusion.service.IDistribusionTotalEmissionService;

/**
 * 配额管理Service业务层处理
 *
 * @author neuedu
 * @date 2024-01-24
 */
@Service
public class DistribusionTotalEmissionServiceImpl implements IDistribusionTotalEmissionService
{
    @Autowired
    private DistribusionTotalEmissionMapper distribusionTotalEmissionMapper;

    /**
     * 查询配额管理
     *
     * @param id 配额管理ID
     * @return 配额管理
     */
    @Override
    public DistribusionTotalEmission selectDistribusionTotalEmissionById(Long id)
    {
        return distribusionTotalEmissionMapper.selectDistribusionTotalEmissionById(id);
    }

    /**
     * 查询配额管理列表
     *
     * @param distribusionTotalEmission 配额管理
     * @return 配额管理
     */
    @Override
    public List<DistribusionTotalEmission> selectDistribusionTotalEmissionList(DistribusionTotalEmission distribusionTotalEmission)
    {
        return distribusionTotalEmissionMapper.selectDistribusionTotalEmissionList(distribusionTotalEmission);
    }

    /**
     * 新增配额管理
     *
     * @param distribusionTotalEmission 配额管理
     * @return 结果
     */
    @Override
    public int insertDistribusionTotalEmission(DistribusionTotalEmission distribusionTotalEmission)
    {
        return distribusionTotalEmissionMapper.insertDistribusionTotalEmission(distribusionTotalEmission);
    }

    /**
     * 修改配额管理
     *
     * @param distribusionTotalEmission 配额管理
     * @return 结果
     */
    @Override
    public int updateDistribusionTotalEmission(DistribusionTotalEmission distribusionTotalEmission)
    {
        return distribusionTotalEmissionMapper.updateDistribusionTotalEmission(distribusionTotalEmission);
    }

    /**
     * 批量删除配额管理
     *
     * @param ids 需要删除的配额管理ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionTotalEmissionByIds(Long[] ids)
    {
        return distribusionTotalEmissionMapper.deleteDistribusionTotalEmissionByIds(ids);
    }

    /**
     * 删除配额管理信息
     *
     * @param id 配额管理ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionTotalEmissionById(Long id)
    {
        return distribusionTotalEmissionMapper.deleteDistribusionTotalEmissionById(id);
    }


}
