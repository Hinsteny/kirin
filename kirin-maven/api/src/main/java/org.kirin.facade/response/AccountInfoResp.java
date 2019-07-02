package org.kirin.facade.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Hinsteny
 * @version $ID: AccountInfoResp 2019-05-14 16:49 All rights reserved.$
 */
public class AccountInfoResp implements Serializable {

    private String userId;

    private String accountNo;

    private Boolean accountType;

    private BigDecimal amount;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Boolean getAccountType() {
        return accountType;
    }

    public void setAccountType(Boolean accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
