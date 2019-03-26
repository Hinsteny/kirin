package org.kirin.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.kirin.mybatis.pojo.VAccount;
import org.kirin.mybatis.pojo.VAccountExample;

public interface VAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    long countByExample(VAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    int deleteByExample(VAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    int insert(VAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    int insertSelective(VAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    List<VAccount> selectByExample(VAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    VAccount selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") VAccount record, @Param("example") VAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") VAccount record, @Param("example") VAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(VAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_account
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(VAccount record);
}