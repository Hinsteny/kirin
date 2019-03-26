package org.kirin;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Hinsteny
 * @version $ID: ApplicationTest 2019-03-26 09:36 All rights reserved.$
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class ApplicationTest {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

}
