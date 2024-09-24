package com.krr006.records;

import com.krr006.records.exception.RecordNotFoundException;
import com.krr006.records.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

@SpringBootTest
public class RecordLoadTest {

    @Autowired
    private RecordService recordService;

    @Test
    public void testSelect1MillionRecordsWith100Connections() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<Callable<Long>> tasks = new ArrayList<>();

        IntStream.range(0, 1_000_000).forEach(i -> tasks.add(() -> {
            long startTime = System.nanoTime();
            try {
                recordService.getRecordById((long) (Math.random() * 100_000) + 1);
            } catch (RecordNotFoundException e) {
            }
            long endTime = System.nanoTime();
            return (endTime - startTime);
        }));

        long startTime = System.currentTimeMillis();
        List<Future<Long>> results = executorService.invokeAll(tasks);
        executorService.shutdown();

        List<Long> times = new ArrayList<>();
        for (Future<Long> result : results) {
            times.add(result.get());
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + " ms");

        times.sort(Long::compare);
        long medianTime = times.get(times.size() / 2);
        long p95Time = times.get((int) (times.size() * 0.95));
        long p99Time = times.get((int) (times.size() * 0.99));

        System.out.println("Median time: " + medianTime + " ns");
        System.out.println("95th percentile time: " + p95Time + " ns");
        System.out.println("99th percentile time: " + p99Time + " ns");
    }
}