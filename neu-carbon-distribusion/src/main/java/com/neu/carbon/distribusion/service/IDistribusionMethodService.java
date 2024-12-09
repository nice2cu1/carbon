package com.neu.carbon.distribusion.service;

import java.util.List;
import com.neu.carbon.distribusion.domain.DistribusionMethod;

/**
 * methodService接口
 * 
 * @author neuedu
 * @date 2024-01-23
 */
public interface IDistribusionMethodService 
{
    /**
     * 查询method
     * 
     * @param id methodID
     * @return method
     */
    public DistribusionMethod selectDistribusionMethodById(Long id);

    public DistribusionMethod selectDistribusionMethodByName(String name);



    /**
     * 查询method列表
     * 
     * @param distribusionMethod method
     * @return method集合
     */
    public List<DistribusionMethod> selectDistribusionMethodList(DistribusionMethod distribusionMethod);

    /**
     * 新增method
     * 
     * @param distribusionMethod method
     * @return 结果
     */
    public int insertDistribusionMethod(DistribusionMethod distribusionMethod);

    /**
     * 修改method
     * 
     * @param distribusionMethod method
     * @return 结果
     */
    public int updateDistribusionMethod(DistribusionMethod distribusionMethod);

    /**
     * 批量删除method
     * 
     * @param ids 需要删除的methodID
     * @return 结果
     */
    public int deleteDistribusionMethodByIds(Long[] ids);

    /**
     * 删除method信息
     * 
     * @param id methodID
     * @return 结果
     */
    public int deleteDistribusionMethodById(Long id);
}
