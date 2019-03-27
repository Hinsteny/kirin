package org.kirin.facade.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Hinsteny
 * @version $ID: CreateUserReq 2019-03-20 16:49 All rights reserved.$
 */
@Getter
@Setter
public class UserInfoReq {

    private String name;

    private String password;

    private Boolean sex;

    private String email;

}
