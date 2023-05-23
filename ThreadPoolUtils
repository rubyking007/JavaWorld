import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static final int KEEP_ALIVE_TIME = 60;

    private static ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolUtils() {
    }

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        if (threadPoolExecutor == null) {
            synchronized (ThreadPoolUtils.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(
                            CORE_POOL_SIZE,
                            MAXIMUM_POOL_SIZE,
                            KEEP_ALIVE_TIME,
                            TimeUnit.SECONDS,
                            new CustomThreadFactory(),
                            new ThreadPoolExecutor.AbortPolicy()
                    );
                }
            }
        }
        return threadPoolExecutor;
    }

    public static ScheduledExecutorService getScheduledThreadPoolExecutor() {
        return Executors.newScheduledThreadPool(CORE_POOL_SIZE);
    }

    private static class CustomThreadFactory implements ThreadFactory {
        private static final String THREAD_NAME_PREFIX = "CustomThreadPool-";
        private static int threadCount = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(THREAD_NAME_PREFIX + threadCount++);
            return thread;
        }
    }
}
