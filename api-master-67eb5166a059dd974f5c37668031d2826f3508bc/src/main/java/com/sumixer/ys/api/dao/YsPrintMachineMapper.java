package com.sumixer.ys.api.dao;

import com.sumixer.ys.api.entity.YsPrintMachine;
import com.sumixer.ys.api.entity.YsPrintMachineExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface YsPrintMachineMapper {
    @SelectProvider(type=YsPrintMachineSqlProvider.class, method="countByExample")
    long countByExample(YsPrintMachineExample example);

    @DeleteProvider(type=YsPrintMachineSqlProvider.class, method="deleteByExample")
    int deleteByExample(YsPrintMachineExample example);

    @Delete({
        "delete from ys_print_machine",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into ys_print_machine (print_id, machine_code, ",
        "client_id, client_secret, ",
        "refresh_token, access_token, ",
        "expires_in, update_time, ",
        "`status`, store_id)",
        "values (#{printId,jdbcType=VARCHAR}, #{machineCode,jdbcType=VARCHAR}, ",
        "#{clientId,jdbcType=VARCHAR}, #{clientSecret,jdbcType=VARCHAR}, ",
        "#{refreshToken,jdbcType=VARCHAR}, #{accessToken,jdbcType=VARCHAR}, ",
        "#{expiresIn,jdbcType=INTEGER}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=INTEGER}, #{storeId,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(YsPrintMachine record);

    @InsertProvider(type=YsPrintMachineSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(YsPrintMachine record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ys_print_machine
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    YsPrintMachine selectOneByExample(YsPrintMachineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ys_print_machine
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    YsPrintMachine selectOneByExampleSelective(@Param("example") YsPrintMachineExample example, @Param("selective") YsPrintMachine.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ys_print_machine
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<YsPrintMachine> selectByExampleSelective(@Param("example") YsPrintMachineExample example, @Param("selective") YsPrintMachine.Column ... selective);

    @SelectProvider(type=YsPrintMachineSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="print_id", property="printId", jdbcType=JdbcType.VARCHAR),
        @Result(column="machine_code", property="machineCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="client_id", property="clientId", jdbcType=JdbcType.VARCHAR),
        @Result(column="client_secret", property="clientSecret", jdbcType=JdbcType.VARCHAR),
        @Result(column="refresh_token", property="refreshToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_token", property="accessToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="expires_in", property="expiresIn", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="store_id", property="storeId", jdbcType=JdbcType.VARCHAR)
    })
    List<YsPrintMachine> selectByExample(YsPrintMachineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ys_print_machine
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    YsPrintMachine selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") YsPrintMachine.Column ... selective);

    @Select({
        "select",
        "id, print_id, machine_code, client_id, client_secret, refresh_token, access_token, ",
        "expires_in, update_time, `status`, store_id",
        "from ys_print_machine",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="print_id", property="printId", jdbcType=JdbcType.VARCHAR),
        @Result(column="machine_code", property="machineCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="client_id", property="clientId", jdbcType=JdbcType.VARCHAR),
        @Result(column="client_secret", property="clientSecret", jdbcType=JdbcType.VARCHAR),
        @Result(column="refresh_token", property="refreshToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="access_token", property="accessToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="expires_in", property="expiresIn", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="store_id", property="storeId", jdbcType=JdbcType.VARCHAR)
    })
    YsPrintMachine selectByPrimaryKey(Integer id);

    @UpdateProvider(type=YsPrintMachineSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") YsPrintMachine record, @Param("example") YsPrintMachineExample example);

    @UpdateProvider(type=YsPrintMachineSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") YsPrintMachine record, @Param("example") YsPrintMachineExample example);

    @UpdateProvider(type=YsPrintMachineSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(YsPrintMachine record);

    @Update({
        "update ys_print_machine",
        "set print_id = #{printId,jdbcType=VARCHAR},",
          "machine_code = #{machineCode,jdbcType=VARCHAR},",
          "client_id = #{clientId,jdbcType=VARCHAR},",
          "client_secret = #{clientSecret,jdbcType=VARCHAR},",
          "refresh_token = #{refreshToken,jdbcType=VARCHAR},",
          "access_token = #{accessToken,jdbcType=VARCHAR},",
          "expires_in = #{expiresIn,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=INTEGER},",
          "store_id = #{storeId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(YsPrintMachine record);
}