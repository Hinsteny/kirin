package org.kirin.user.response;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Hinsteny
 * @version $ID: UserInfoResp 2019-03-20 17:14 All rights reserved.$
 */
@Getter
@Setter
public class UserInfoResp {

    private Long id;

    private String username;

    private String sex;

    private String email;

    private Date createdAt;

    private Date modifiedAt;

}
