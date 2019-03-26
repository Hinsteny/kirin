package org.kirin.user;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.kirin.ApplicationTest;
import org.kirin.infrastructure.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Hinsteny
 * @version $ID: UserTest 2019-03-26 09:43 All rights reserved.$
 */
public class UserTest extends ApplicationTest {

    @Autowired
    private UserController userController;

    @Test
    public void searchUserCase() {
        BaseResponse search = userController.search("Hin");
        System.out.println(JSON.toJSONString(search));
    }

}
