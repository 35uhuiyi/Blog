package com.ny.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yang
 * @version 1.0
 * @date 2021/4/14 17:47
 */
public class ThreadUtils {
    private static final ExecutorService es = Executors.newFixedThreadPool(10);
    public static void execute(Runnable runnable){
        es.submit(runnable);
    }

}
