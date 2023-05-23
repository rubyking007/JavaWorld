import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThreadPoolUtilsTest {
    private static ThreadPoolExecutor threadPool;

    @BeforeAll
    public static void setUp() {
        threadPool = ThreadPoolUtils.getThreadPoolExecutor();
    }

    @AfterAll
    public static void tearDown() {
        threadPool.shutdown();
    }

    @Test
    public void testThreadPoolCreation() {
        assertNotNull(threadPool);
        assertEquals(ThreadPoolUtils.CORE_POOL_SIZE, threadPool.getCorePoolSize());
        assertEquals(ThreadPoolUtils.MAXIMUM_POOL_SIZE, threadPool.getMaximumPoolSize());
    }

    @Test
    public void testTaskExecution() throws ExecutionException, InterruptedException {
        Future<String> future = threadPool.submit(() -> "Hello, World!");

        assertNotNull(future);
        assertTrue(future.isDone());
        assertEquals("Hello, World!", future.get());
    }
}
