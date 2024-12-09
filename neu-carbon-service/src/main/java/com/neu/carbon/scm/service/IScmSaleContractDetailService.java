package com.neu.carbon.scm.service;

import java.util.List;
import com.neu.carbon.scm.domain.ScmSaleContractDetail;

/**
 * 合同明细Service接口
 * 
 * @author neuedu
 * @date 2022-07-04
 */
public interface IScmSaleContractDetailService 
{
    /**
     * 查询合同明细
     * 
     * @param id 合同明细ID
     * @return 合同明细
     */
    public ScmSaleContractDetail selectScmSaleContractDetailById(Long id);

    /**
     * 查询合同明细列表
     * 
     * @param scmSaleContractDetail 合同明细
     * @return 合同明细集合
     */
    public List<ScmSaleContractDetail> selectScmSaleContractDetailList(ScmSaleContractDetail scmSaleContractDetail);

    /**
     * 新增合同明细
     * 
     * @param scmSaleContractDetail 合同明细
     * @return 结果
     */
    public int insertScmSaleContractDetail(ScmSaleContractDetail scmSaleContractDetail);

    /**
     * 修改合同明细
     * 
     * @param scmSaleContractDetail 合同明细
     * @return 结果
     */
    public int updateScmSaleContractDetail(ScmSaleContractDetail scmSaleContractDetail);

    /**
     * 批量删除合同明细
     * 
     * @param ids 需要删除的合同明细ID
     * @return 结果
     */
    public int deleteScmSaleContractDetailByIds(Long[] ids);

    /**
     * 删除合同明细信息
     * 
     * @param id 合同明细ID
     * @return 结果
     */
    public int deleteScmSaleContractDetailById(Long id);
}