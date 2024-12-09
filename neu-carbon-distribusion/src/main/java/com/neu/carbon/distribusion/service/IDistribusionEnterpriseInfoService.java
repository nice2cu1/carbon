package com.neu.carbon.distribusion.service;

import java.util.List;
import com.neu.carbon.distribusion.domain.DistribusionEnterpriseInfo;

/**
 * 企业信息Service接口
 * 
 * @author neuedu
 * @date 2024-01-25
 */
public interface IDistribusionEnterpriseInfoService 
{
    /**
     * 查询企业信息
     * 
     * @param id 企业信息ID
     * @return 企业信息
     */
    public DistribusionEnterpriseInfo selectDistribusionEnterpriseInfoById(Long id);

    /**
     * 查询企业信息列表
     * 
     * @param distribusionEnterpriseInfo 企业信息
     * @return 企业信息集合
     */
    public List<DistribusionEnterpriseInfo> selectDistribusionEnterpriseInfoList(DistribusionEnterpriseInfo distribusionEnterpriseInfo);

    /**
     * 新增企业信息
     * 
     * @param distribusionEnterpriseInfo 企业信息
     * @return 结果
     */
    public int insertDistribusionEnterpriseInfo(DistribusionEnterpriseInfo distribusionEnterpriseInfo);

    /**
     * 修改企业信息
     * 
     * @param distribusionEnterpriseInfo 企业信息
     * @return 结果
     */
    public int updateDistribusionEnterpriseInfo(DistribusionEnterpriseInfo distribusionEnterpriseInfo);

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的企业信息ID
     * @return 结果
     */
    public int deleteDistribusionEnterpriseInfoByIds(Long[] ids);

    /**
     * 删除企业信息信息
     * 
     * @param id 企业信息ID
     * @return 结果
     */
    public int deleteDistribusionEnterpriseInfoById(Long id);
}
