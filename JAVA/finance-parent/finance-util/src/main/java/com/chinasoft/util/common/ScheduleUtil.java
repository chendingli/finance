package com.chinasoft.util.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 汪毅
 * @Date: 2018/6/29 15:20
 * @Description: 定时器
 */
public class ScheduleUtil {

    private static ScheduledExecutorService Manager = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 延时任务，指定延迟时间之后执行
     * @param command
     * @param delay
     * @param unit
     */
    public static void schedule(Runnable command, long delay, TimeUnit unit) {
        Manager.schedule(command, delay, unit);
    }

    /**
     * 循环任务，按照上一次任务的发起时间计算下一次任务的开始时间
     * @param command
     * @param initialDelay
     * @param period
     * @param unit
     */
    public static void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        Manager.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    /**
     * 循环任务，以上一次任务的结束时间计算下一次任务的开始时间
     * @param command
     * @param initialDelay
     * @param delay
     * @param unit
     */
    public static void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        Manager.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }

}
