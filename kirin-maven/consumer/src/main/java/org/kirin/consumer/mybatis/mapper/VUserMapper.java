package org.kirin.consumer.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.kirin.consumer.mybatis.pojo.VUser;
import org.kirin.consumer.mybatis.pojo.VUserExample;

public interface VUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    long countByExample(VUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    int deleteByExample(VUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    int insert(VUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    int insertSelective(VUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    List<VUser> selectByExample(VUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    VUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") VUser record, @Param("example") VUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") VUser record, @Param("example") VUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(VUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(VUser record);
}