package com.neu.carbon.distribusion.service;

import java.util.List;
import com.neu.carbon.distribusion.domain.DistribusionProduct;

/**
 * productService接口
 * 
 * @author neuedu
 * @date 2024-01-23
 */
public interface IDistribusionProductService 
{
    /**
     * 查询product
     * 
     * @param id productID
     * @return product
     */
    public DistribusionProduct selectDistribusionProductById(Long id);

    /**
     * 查询product列表
     * 
     * @param distribusionProduct product
     * @return product集合
     */
    public List<DistribusionProduct> selectDistribusionProductList(DistribusionProduct distribusionProduct);

    /**
     * 新增product
     * 
     * @param distribusionProduct product
     * @return 结果
     */
    public int insertDistribusionProduct(DistribusionProduct distribusionProduct);

    /**
     * 修改product
     * 
     * @param distribusionProduct product
     * @return 结果
     */
    public int updateDistribusionProduct(DistribusionProduct distribusionProduct);

    /**
     * 批量删除product
     * 
     * @param ids 需要删除的productID
     * @return 结果
     */
    public int deleteDistribusionProductByIds(Long[] ids);

    /**
     * 删除product信息
     * 
     * @param id productID
     * @return 结果
     */
    public int deleteDistribusionProductById(Long id);
}
