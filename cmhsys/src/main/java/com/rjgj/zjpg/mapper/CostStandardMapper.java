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
            "insert into cost_std (stdName, pdr, createTime, intro, type, enable) " +
                    "values (#{stdName}, #{pdr}, CURRENT_TIMESTAMP, #{intro}, #{type}, #{enable})"
                    )
    @Options(useGeneratedKeys = true, keyProperty = "stdId", keyColumn = "stdId")
    public boolean insertCostStandard(CostStandard costStandard);

    @Update(
            "UPDATE cost_std SET " +
                    "enable = #{enable} " +
                    "WHERE stdId = #{stdId}"
    )
    public boolean updateCostStandard(CostStandard costStandard);

    @Delete("delete from cost_std where stdId = #{stdId}")
    public boolean deleteCostStandard(@Param("stdId") int stdId);

}
