package org.kirin.message.body;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Hinsteny
 * @version $ID: UserCreateEventMessage 2019-04-02 10:37 All rights reserved.$
 */
@Setter
@Getter
public class UserCreateEventMessage {

    private LocalDateTime createTime;

    private String userName;

}
