package com.neu.carbon.distribusion.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.distribusion.mapper.DistribusionProductMapper;
import com.neu.carbon.distribusion.domain.DistribusionProduct;
import com.neu.carbon.distribusion.service.IDistribusionProductService;

/**
 * productService业务层处理
 * 
 * @author neuedu
 * @date 2024-01-23
 */
@Service
public class DistribusionProductServiceImpl implements IDistribusionProductService 
{
    @Autowired
    private DistribusionProductMapper distribusionProductMapper;

    /**
     * 查询product
     * 
     * @param id productID
     * @return product
     */
    @Override
    public DistribusionProduct selectDistribusionProductById(Long id)
    {
        return distribusionProductMapper.selectDistribusionProductById(id);
    }

    /**
     * 查询product列表
     * 
     * @param distribusionProduct product
     * @return product
     */
    @Override
    public List<DistribusionProduct> selectDistribusionProductList(DistribusionProduct distribusionProduct)
    {
        return distribusionProductMapper.selectDistribusionProductList(distribusionProduct);
    }

    /**
     * 新增product
     * 
     * @param distribusionProduct product
     * @return 结果
     */
    @Override
    public int insertDistribusionProduct(DistribusionProduct distribusionProduct)
    {
        return distribusionProductMapper.insertDistribusionProduct(distribusionProduct);
    }

    /**
     * 修改product
     * 
     * @param distribusionProduct product
     * @return 结果
     */
    @Override
    public int updateDistribusionProduct(DistribusionProduct distribusionProduct)
    {
        return distribusionProductMapper.updateDistribusionProduct(distribusionProduct);
    }

    /**
     * 批量删除product
     * 
     * @param ids 需要删除的productID
     * @return 结果
     */
    @Override
    public int deleteDistribusionProductByIds(Long[] ids)
    {
        return distribusionProductMapper.deleteDistribusionProductByIds(ids);
    }

    /**
     * 删除product信息
     * 
     * @param id productID
     * @return 结果
     */
    @Override
    public int deleteDistribusionProductById(Long id)
    {
        return distribusionProductMapper.deleteDistribusionProductById(id);
    }
}
