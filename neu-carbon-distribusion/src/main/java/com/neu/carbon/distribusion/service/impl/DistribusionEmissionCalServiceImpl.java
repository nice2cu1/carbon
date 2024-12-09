package com.neu.carbon.distribusion.service.impl;

import java.util.List;

import com.neu.carbon.distribusion.domain.DistribusionTotalEmission;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.distribusion.mapper.DistribusionEmissionCalMapper;
import com.neu.carbon.distribusion.domain.DistribusionEmissionCal;
import com.neu.carbon.distribusion.service.IDistribusionEmissionCalService;

/**
 * 额度Service业务层处理
 *
 * @author neuedu
 * @date 2024-01-25
 */
@Service
public class DistribusionEmissionCalServiceImpl implements IDistribusionEmissionCalService
{
    @Autowired
    private DistribusionEmissionCalMapper distribusionEmissionCalMapper;

    /**
     * 查询额度
     *
     * @param id 额度ID
     * @return 额度
     */
    @Override
    public DistribusionEmissionCal selectDistribusionEmissionCalById(Long id)
    {
        return distribusionEmissionCalMapper.selectDistribusionEmissionCalById(id);
    }

    /**
     * 查询额度列表
     *
     * @param distribusionEmissionCal 额度
     * @return 额度
     */
    @Override
    public List<DistribusionEmissionCal> selectDistribusionEmissionCalList(DistribusionEmissionCal distribusionEmissionCal)
    {
        return distribusionEmissionCalMapper.selectDistribusionEmissionCalList(distribusionEmissionCal);
    }

    @Override
    public List<DistribusionEmissionCal> selectDistribusionEmissionCalAppList(DistribusionEmissionCal distribusionEmissionCal)
    {
        return distribusionEmissionCalMapper.selectDistribusionEmissionCalAppList(distribusionEmissionCal);
    }

    /**
     * 新增额度
     *
     * @param distribusionEmissionCal 额度
     * @return 结果
     */
    @Override
    public int insertDistribusionEmissionCal(DistribusionEmissionCal distribusionEmissionCal)
    {
        distribusionEmissionCal.setCreateTime(DateUtils.getNowDate());
        return distribusionEmissionCalMapper.insertDistribusionEmissionCal(distribusionEmissionCal);
    }

    /**
     * 修改额度
     *
     * @param distribusionEmissionCal 额度
     * @return 结果
     */
    @Override
    public int updateDistribusionEmissionCal(DistribusionEmissionCal distribusionEmissionCal)
    {
        distribusionEmissionCal.setUpdateTime(DateUtils.getNowDate());
        return distribusionEmissionCalMapper.updateDistribusionEmissionCal(distribusionEmissionCal);
    }

    /**
     * 批量删除额度
     *
     * @param ids 需要删除的额度ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionEmissionCalByIds(Long[] ids)
    {
        return distribusionEmissionCalMapper.deleteDistribusionEmissionCalByIds(ids);
    }

    /**
     * 删除额度信息
     *
     * @param id 额度ID
     * @return 结果
     */
    @Override
    public int deleteDistribusionEmissionCalById(Long id)
    {
        return distribusionEmissionCalMapper.deleteDistribusionEmissionCalById(id);
    }

    @Override
    public DistribusionEmissionCal getInfoByEnterpriceId(Long id) {
        return distribusionEmissionCalMapper.getInfoByEnterpriceId(id);
    }
}
