package org.kirin.service.account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.kirin.facade.response.AccountInfoResp;
import org.kirin.service.account.request.CreateAccountReq;
import org.kirin.service.mybatis.mapper.VAccountMapper;
import org.kirin.service.mybatis.pojo.VAccount;
import org.kirin.service.mybatis.pojo.VAccountExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hinsteny
 * @version AccountService: AccountService 2019-05-14 10:11 All rights reserved.$
 */
@Service(value = "accountService")
public class AccountService {

    @Autowired
    private VAccountMapper accountMapper;

    public Long createAccount(CreateAccountReq accountInfoReq) {
        VAccount account = new VAccount();
        account.setUserId(accountInfoReq.getUserId());
        account.setAccountNo(accountInfoReq.getAccountNo());
        account.setAccountType(accountInfoReq.getAccountType());
        account.setAmount(account.getAmount());
        accountMapper.insertSelective(account);
        return account.getId();
    }

    public List<AccountInfoResp> searchAccoountsByUserId(String userId) {
        VAccountExample example = new VAccountExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<VAccount> accounts = accountMapper.selectByExample(example);
        return CollectionUtils.isEmpty(accounts) ? new ArrayList<>() : (accounts.stream().map(vAccount -> {
            AccountInfoResp target = new AccountInfoResp();
            BeanUtils.copyProperties(vAccount, target);
            return target;
        }).collect(Collectors.toList()));
    }

}
