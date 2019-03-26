package org.kirin.user.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @author Hinsteny
 * @version $ID: CreateUserReq 2019-03-20 16:49 All rights reserved.$
 */
@Getter
@Setter
public class CreateUserReq {

    @NotBlank(message = "用户名不应为空")
    @Length(min = 1, max = 30, message = "用户名长度应为1~30")
    private String name;

    @NotBlank(message = "用户密码不应为空")
    @Length(min = 6, max = 30, message = "用户密码长度应为6~30")
    private String password;

    @NotNull(message = "性别不能为空")
    private Boolean sex;

    @NotBlank(message = "邮箱不应为空")
    @Length(min = 5, message = "邮箱长度至少为5")
    private String email;

}
