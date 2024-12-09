package com.neu.carbon.distribusion.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.distribusion.mapper.DistribusionMethodMapper;
import com.neu.carbon.distribusion.domain.DistribusionMethod;
import com.neu.carbon.distribusion.service.IDistribusionMethodService;

/**
 * methodService业务层处理
 * 
 * @author neuedu
 * @date 2024-01-23
 */
@Service
public class DistribusionMethodServiceImpl implements IDistribusionMethodService 
{
    @Autowired
    private DistribusionMethodMapper distribusionMethodMapper;

    /**
     * 查询method
     * 
     * @param id methodID
     * @return method
     */
    @Override
    public DistribusionMethod selectDistribusionMethodById(Long id)
    {
        return distribusionMethodMapper.selectDistribusionMethodById(id);
    }

    @Override
    public DistribusionMethod selectDistribusionMethodByName(String name)
    {
        return distribusionMethodMapper.selectDistribusionMethodByName(name);
    }



    /**
     * 查询method列表
     * 
     * @param distribusionMethod method
     * @return method
     */
    @Override
    public List<DistribusionMethod> selectDistribusionMethodList(DistribusionMethod distribusionMethod)
    {
        return distribusionMethodMapper.selectDistribusionMethodList(distribusionMethod);
    }

    /**
     * 新增method
     * 
     * @param distribusionMethod method
     * @return 结果
     */
    @Override
    public int insertDistribusionMethod(DistribusionMethod distribusionMethod)
    {
        return distribusionMethodMapper.insertDistribusionMethod(distribusionMethod);
    }

    /**
     * 修改method
     * 
     * @param distribusionMethod method
     * @return 结果
     */
    @Override
    public int updateDistribusionMethod(DistribusionMethod distribusionMethod)
    {
        return distribusionMethodMapper.updateDistribusionMethod(distribusionMethod);
    }

    /**
     * 批量删除method
     * 
     * @param ids 需要删除的methodID
     * @return 结果
     */
    @Override
    public int deleteDistribusionMethodByIds(Long[] ids)
    {
        return distribusionMethodMapper.deleteDistribusionMethodByIds(ids);
    }

    /**
     * 删除method信息
     * 
     * @param id methodID
     * @return 结果
     */
    @Override
    public int deleteDistribusionMethodById(Long id)
    {
        return distribusionMethodMapper.deleteDistribusionMethodById(id);
    }
}
