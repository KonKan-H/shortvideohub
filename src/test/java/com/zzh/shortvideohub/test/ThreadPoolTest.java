package com.zzh.shortvideohub.test;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Date: 2020/11/23 13:58
 * @Author: zzh
 */
@SpringBootTest
public class ThreadPoolTest {

    @Test
    public void cpuNum() {
        System.out.println("cpu核心数:"+Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void fixedThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " run");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    @Test
    public void singleThreadPool() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " run");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    @Test
    public void cachedThreadPool() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " run");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    public void scheduledThreadPool() {
        ExecutorService threadPool = Executors.newScheduledThreadPool(4);
        try{
            for(int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " run");
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdownNow();
        }
    }
}
