package org.kirin.service.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Hinsteny
 * @version AppTasks: AppTasks 2019-06-05 18:10 All rights reserved.$
 */
@Component("appTasks")
public class AppTasks {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 固定间隔任务
     */
    @Scheduled(fixedDelay = 1000*10)
    public void fixDelayTask() {
        logger.info("Execute fixDelayTask");
    }

    /**
     * 固定频率任务
     */
    @Scheduled(fixedRate = 1000*10)
    public void fixedRateTask() {
        logger.info("Execute fixedRateTask");
    }

    /**
     * 定时周期任务
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void cronTask() {
        logger.info("Execute cronTask");
    }

}
