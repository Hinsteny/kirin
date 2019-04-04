package org.kirin.facade.request;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Hinsteny
 * @version $ID: CreateUserReq 2019-03-20 16:49 All rights reserved.$
 */
@Getter
@Setter
public class UserInfoReq implements Serializable {

    private String name;

    private String password;

    private Boolean sex;

    private String email;

}
