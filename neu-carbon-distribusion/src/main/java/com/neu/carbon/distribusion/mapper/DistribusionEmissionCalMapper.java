package com.neu.carbon.distribusion.mapper;

import java.util.List;
import com.neu.carbon.distribusion.domain.DistribusionEmissionCal;
import com.neu.carbon.distribusion.domain.DistribusionTotalEmission;

/**
 * 额度Mapper接口
 *
 * @author neuedu
 * @date 2024-01-25
 */
public interface DistribusionEmissionCalMapper
{
    /**
     * 查询额度
     *
     * @param id 额度ID
     * @return 额度
     */
    public DistribusionEmissionCal selectDistribusionEmissionCalById(Long id);

    /**
     * 查询额度列表
     *
     * @param distribusionEmissionCal 额度
     * @return 额度集合
     */
    public List<DistribusionEmissionCal> selectDistribusionEmissionCalList(DistribusionEmissionCal distribusionEmissionCal);
    public List<DistribusionEmissionCal> selectDistribusionEmissionCalAppList(DistribusionEmissionCal distribusionEmissionCal);

    /**
     * 新增额度
     *
     * @param distribusionEmissionCal 额度
     * @return 结果
     */
    public int insertDistribusionEmissionCal(DistribusionEmissionCal distribusionEmissionCal);

    /**
     * 修改额度
     *
     * @param distribusionEmissionCal 额度
     * @return 结果
     */
    public int updateDistribusionEmissionCal(DistribusionEmissionCal distribusionEmissionCal);

    /**
     * 删除额度
     *
     * @param id 额度ID
     * @return 结果
     */
    public int deleteDistribusionEmissionCalById(Long id);

    /**
     * 批量删除额度
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDistribusionEmissionCalByIds(Long[] ids);

    DistribusionEmissionCal getInfoByEnterpriceId(Long id);
}
