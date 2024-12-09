package com.neu.carbon.distribusion.service;

import java.util.List;
import com.neu.carbon.distribusion.domain.DistribusionCredit;

/**
 * 额度Service接口
 * 
 * @author neuedu
 * @date 2024-01-25
 */
public interface IDistribusionCreditService 
{
    /**
     * 查询额度
     * 
     * @param id 额度ID
     * @return 额度
     */
    public DistribusionCredit selectDistribusionCreditById(Long id);

    /**
     * 查询额度列表
     * 
     * @param distribusionCredit 额度
     * @return 额度集合
     */
    public List<DistribusionCredit> selectDistribusionCreditList(DistribusionCredit distribusionCredit);

    /**
     * 新增额度
     * 
     * @param distribusionCredit 额度
     * @return 结果
     */
    public int insertDistribusionCredit(DistribusionCredit distribusionCredit);

    /**
     * 修改额度
     * 
     * @param distribusionCredit 额度
     * @return 结果
     */
    public int updateDistribusionCredit(DistribusionCredit distribusionCredit);

    /**
     * 批量删除额度
     * 
     * @param ids 需要删除的额度ID
     * @return 结果
     */
    public int deleteDistribusionCreditByIds(Long[] ids);

    /**
     * 删除额度信息
     * 
     * @param id 额度ID
     * @return 结果
     */
    public int deleteDistribusionCreditById(Long id);

    /**
     * 通过企业ID查询额度信息
     * @param id 企业id
     * @return 结果
     * */
    DistribusionCredit getInfoByEnterpriseId(Long id);
}
