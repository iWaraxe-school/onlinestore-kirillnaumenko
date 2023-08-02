package com.coherentsolutions;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPoolManager {
    private static final int availableThreadCount = Configuration.getInstance().threadCount;
    public static final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(availableThreadCount);

    public static void ShutdownScheduledThreadPool(){
        System.out.println("Shutting down thread pool...");
        scheduledThreadPool.shutdown();
    }
}
