package io.github.aluria.engine.scheduler;

import io.github.aluria.engine.scheduler.executor.BukkitExecutor;
import io.github.aluria.engine.scheduler.executor.impl.AsynchronousBukkitExecutor;
import io.github.aluria.engine.scheduler.executor.impl.PrimaryThreadBukkitExecutor;

import java.util.concurrent.Executor;

public class Scheduler {

  private static final BukkitExecutor PRIMARY_THREAD_EXECUTOR = new PrimaryThreadBukkitExecutor();
  private static final BukkitExecutor ASYNCHRONOUS_EXECUTOR = new AsynchronousBukkitExecutor();

  public static BukkitExecutor async() {
    return ASYNCHRONOUS_EXECUTOR;
  }

  public static BukkitExecutor sync() {
    return PRIMARY_THREAD_EXECUTOR;
  }
}
