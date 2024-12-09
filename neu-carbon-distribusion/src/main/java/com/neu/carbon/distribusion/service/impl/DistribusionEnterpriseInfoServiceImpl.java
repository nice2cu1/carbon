package com.neu.carbon.distribusion.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.distribusion.mapper.DistribusionEnterpriseInfoMapper;
import com.neu.carbon.distribusion.domain.DistribusionEnterpriseInfo;
import com.neu.carbon.distribusion.service.IDistribusionEnterpriseInfoService;

/**
 * 企业信息Service业务层处理
 * 
 * @author neuedu
 * @date 2024-01-25
 */
@Service
public class DistribusionEnterpriseInfoServiceImpl implements IDistribusionEnterpriseInfoService 
{
    @Autowired
    private DistribusionEnterpriseInfoMapper distribusionEnterpriseInfoMapper;

    /**
     * 查询企业信息
     * 
     * @param id 企业信息ID
     * @return 企业信息
     */
    @Override
    public DistribusionEnterpriseInfo selectDistribusionEnterpriseInfoById(Long id)
    {
        return distribusionEnterpriseInfoMapper.selectDistribusionEnterpriseInfoById(id);
    }

    /**
     * 查询企业信息列表
     * 
     * @param distribusionEnterpriseInfo 企业信息
     * @return 企业信息
     */
    @Override
    public List<DistribusionEnterpriseInfo> selectDistribusionEnterpriseInfoList(DistribusionEnterpriseInfo distribusionEnterpriseInfo)
    {
        return distribusionEnterpriseInfoMapper.selectDistribusionEnterpriseInfoList(distribusionEnterpriseInfo);
    }

    /**
     * 新增企业信息
     * 
     * @param distribusionEnterpriseInfo 企业信息
     * @return 结果
     */
    @Override
    public int insertDistribusionEnterpriseInfo(DistribusionEnterpriseInfo distribusionEnterpriseInfo)
    {
        return distribusionEnterpriseInfoMapper.insertDistribusionEnterpriseInfo(distribusionEnterpriseInfo);
    }

    /**
     * 修改企业信息
     * 
     * @param distribusionEnterpriseInfo 企业信息
     * @return 结果
     */
    @Override
    public int updateDistribusionEnterpriseInfo(DistribusionEnterpriseInfo distribusionEnterpriseInfo)
    {
        return distribusionEnterpriseInfoMapper.updateDistribusionEnterpriseInfo(distribusionEnterpriseInfo);
    }

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的企业信息ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionEnterpriseInfoByIds(Long[] ids)
    {
        return distribusionEnterpriseInfoMapper.deleteDistribusionEnterpriseInfoByIds(ids);
    }

    /**
     * 删除企业信息信息
     * 
     * @param id 企业信息ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionEnterpriseInfoById(Long id)
    {
        return distribusionEnterpriseInfoMapper.deleteDistribusionEnterpriseInfoById(id);
    }
}
