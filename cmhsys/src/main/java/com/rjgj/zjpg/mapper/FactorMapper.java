package com.rjgj.zjpg.mapper;
import com.rjgj.zjpg.entity.CostStandard;
import com.rjgj.zjpg.entity.Factor;
import org.apache.ibatis.annotations.*;


import java.util.List;
@Mapper
public interface FactorMapper {
//    @Select({"select * from factor"})
//    List<Factor> selectFactor();

    //得到下拉框中的因子元素List
    @Select("select * from factor where factor_type = #{factor_type} AND stdId = #{stdId}")
    public List<Factor> getFactorsByTypeAndStdId(@Param("factor_type") String factor_type, @Param("stdId") int stdId);

    @Insert("insert into factor (factor_type, stdId, factor_name, factor_value) "+
                    "values (#{factor_type}, #{stdId}, #{factor_name}, #{factor_value})")
    public boolean insertFactor(Factor factor);

    @Delete("delete from factor where factor_type = #{factor_type} AND stdId = #{stdId} AND factor_name = #{factor_name}")
    public boolean deleteFactor(Factor factor);

    @Insert({
            "<script>",
            "INSERT INTO factor (stdId, factor_type, factor_name, factor_value) VALUES",
            "<foreach collection='list' item='factor' separator=','>",
            "(#{factor.stdId}, #{factor.factor_type}, #{factor.factor_name}, #{factor.factor_value})",
            "</foreach>",
            "</script>"
    })
    int batchInsertFactors(@Param("list") List<Factor> factorList);

}
