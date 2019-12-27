package net.notfab.hubbasics.spigot.managers;

import net.notfab.hubbasics.spigot.entities.Manager;
import net.notfab.hubbasics.spigot.entities.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class TaskManager extends Manager {

    private ThreadPoolExecutor cachedExecutor;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public TaskManager() {
        ThreadFactoryBuilder threadBuilder = new ThreadFactoryBuilder();
        threadBuilder.setNamePrefix("HubBasics");
        //
        this.cachedExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.cachedExecutor.setThreadFactory(threadBuilder.build());
        //
        scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
        scheduledThreadPoolExecutor.setMaximumPoolSize(Integer.MAX_VALUE);
        scheduledThreadPoolExecutor.setThreadFactory(threadBuilder.build());
    }

    @Override
    public void onDisable() {
        this.scheduledThreadPoolExecutor.shutdownNow();
        this.cachedExecutor.shutdownNow();
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long initDelay, long rate, TimeUnit unit) {
        return scheduledThreadPoolExecutor.scheduleWithFixedDelay(runnable, initDelay, rate, unit);
    }

    public ScheduledFuture<?> runTaskLater(Runnable runnable, long delay, TimeUnit unit) {
        return scheduledThreadPoolExecutor.schedule(runnable, delay, unit);
    }

    public Future<?> runAsync(Runnable runnable) {
        return this.cachedExecutor.submit(runnable);
    }

}