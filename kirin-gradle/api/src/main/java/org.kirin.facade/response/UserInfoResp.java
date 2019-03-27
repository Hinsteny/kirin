package org.kirin.facade.response;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Hinsteny
 * @version $ID: CreateUserReq 2019-03-20 16:49 All rights reserved.$
 */
@Getter
@Setter
public class UserInfoResp implements Serializable {

    private String username;

    private String password;

    private Boolean sex;

    private String email;

}
