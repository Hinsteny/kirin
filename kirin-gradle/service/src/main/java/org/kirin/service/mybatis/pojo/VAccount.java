package org.kirin.service.mybatis.pojo;

import java.util.Date;

public class VAccount {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column v_account.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column v_account.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column v_account.account_no
     *
     * @mbg.generated
     */
    private String accountNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column v_account.amount
     *
     * @mbg.generated
     */
    private Long amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column v_account.created_at
     *
     * @mbg.generated
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column v_account.modified_at
     *
     * @mbg.generated
     */
    private Date modifiedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_account.id
     *
     * @return the value of v_account.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_account.id
     *
     * @param id the value for v_account.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_account.user_id
     *
     * @return the value of v_account.user_id
     *
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_account.user_id
     *
     * @param userId the value for v_account.user_id
     *
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_account.account_no
     *
     * @return the value of v_account.account_no
     *
     * @mbg.generated
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_account.account_no
     *
     * @param accountNo the value for v_account.account_no
     *
     * @mbg.generated
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_account.amount
     *
     * @return the value of v_account.amount
     *
     * @mbg.generated
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_account.amount
     *
     * @param amount the value for v_account.amount
     *
     * @mbg.generated
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_account.created_at
     *
     * @return the value of v_account.created_at
     *
     * @mbg.generated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_account.created_at
     *
     * @param createdAt the value for v_account.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column v_account.modified_at
     *
     * @return the value of v_account.modified_at
     *
     * @mbg.generated
     */
    public Date getModifiedAt() {
        return modifiedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column v_account.modified_at
     *
     * @param modifiedAt the value for v_account.modified_at
     *
     * @mbg.generated
     */
    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}