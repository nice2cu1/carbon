package com.neu.carbon.distribusion.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.distribusion.mapper.DistribusionCreditMapper;
import com.neu.carbon.distribusion.domain.DistribusionCredit;
import com.neu.carbon.distribusion.service.IDistribusionCreditService;

/**
 * 额度Service业务层处理
 * 
 * @author neuedu
 * @date 2024-01-25
 */
@Service
public class DistribusionCreditServiceImpl implements IDistribusionCreditService 
{
    @Autowired
    private DistribusionCreditMapper distribusionCreditMapper;

    /**
     * 查询额度
     * 
     * @param id 额度ID
     * @return 额度
     */
    @Override
    public DistribusionCredit selectDistribusionCreditById(Long id)
    {
        return distribusionCreditMapper.selectDistribusionCreditById(id);
    }

    /**
     * 查询额度列表
     * 
     * @param distribusionCredit 额度
     * @return 额度
     */
    @Override
    public List<DistribusionCredit> selectDistribusionCreditList(DistribusionCredit distribusionCredit)
    {
        return distribusionCreditMapper.selectDistribusionCreditList(distribusionCredit);
    }

    /**
     * 新增额度
     * 
     * @param distribusionCredit 额度
     * @return 结果
     */
    @Override
    public int insertDistribusionCredit(DistribusionCredit distribusionCredit)
    {
        return distribusionCreditMapper.insertDistribusionCredit(distribusionCredit);
    }

    /**
     * 修改额度
     * 
     * @param distribusionCredit 额度
     * @return 结果
     */
    @Override
    public int updateDistribusionCredit(DistribusionCredit distribusionCredit)
    {
        return distribusionCreditMapper.updateDistribusionCredit(distribusionCredit);
    }

    /**
     * 批量删除额度
     * 
     * @param ids 需要删除的额度ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionCreditByIds(Long[] ids)
    {
        return distribusionCreditMapper.deleteDistribusionCreditByIds(ids);
    }

    /**
     * 删除额度信息
     * 
     * @param id 额度ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionCreditById(Long id)
    {
        return distribusionCreditMapper.deleteDistribusionCreditById(id);
    }

    @Override
    public DistribusionCredit getInfoByEnterpriseId(Long id) {
        return distribusionCreditMapper.getInfoByEnterpriseId(id);
    }
}
