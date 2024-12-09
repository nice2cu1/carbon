package com.neu.carbon.distribusion.mapper;

import java.util.List;
import com.neu.carbon.distribusion.domain.DistribusionCredit;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * 额度Mapper接口
 * 
 * @author neuedu
 * @date 2024-01-25
 */
public interface DistribusionCreditMapper 
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
     * 删除额度
     * 
     * @param id 额度ID
     * @return 结果
     */
    public int deleteDistribusionCreditById(Long id);

    /**
     * 批量删除额度
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDistribusionCreditByIds(Long[] ids);
    /**
     * 通过企业ID查询额度信息
     * @param id 企业id
     * @return 结果
     * */
    DistribusionCredit getInfoByEnterpriseId(@Param("id") Long id);
}
