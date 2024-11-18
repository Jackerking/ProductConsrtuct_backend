package com.rjgj.zjpg.mapper;

import com.rjgj.zjpg.entity.CostStandard;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CostStandardMapper {
    @Select({"select * from cost_std"})
    List<CostStandard> selectCostStandard();
    @Select(
            "select * from cost_std where stdId = #{stdId}"
    )
    public CostStandard getCostStandardById(int stdId);

    @Insert(
            "insert into cost_std (stdName, pdr, createTime, intro, type, enable) "+
                    "values (#{stdName}, #{pdr}, CURRENT_TIMESTAMP, #{intro}, #{type}, #{enable})"
    )
    public boolean insertCostStandard(CostStandard costStandard);

//    @Update(
//            "update cost_std set " +
//                    "stdName = #{stdName}, " +
//                    "PDR = #{PDR}, " +
//                    "intro = #{intro}, " +
//                    "createTime = CURRENT_TIMESTAMP, " +
//                    "type = #{type}, " +
//                    "enable = #{enable} " +
//                    "where stdId = #{stdId}"
//    )
//    public boolean updateCostStandard(CostStandard costStandard);

    @Update("update cost_std set enable = #{enable} where stdId = #{stdId}")
    public boolean updateCostStandard(CostStandard costStandard);

    @Delete("delete from cost_std where stdId = #{stdId}")
    public boolean deleteCostStandard(@Param("stdId") int stdId);

}
