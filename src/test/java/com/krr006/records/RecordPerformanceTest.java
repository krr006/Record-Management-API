package com.krr006.records;

import com.krr006.records.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.stream.IntStream;
import com.krr006.records.entity.Record;

@SpringBootTest
public class RecordPerformanceTest {

    @Autowired
    private RecordService recordService;

    @Test
    public void testCreate100kRecords() {
        long startTime = System.currentTimeMillis();

        IntStream.range(0, 100_000).forEach(i -> {
            Record record = new Record();
            record.setData("Record data " + i);
            recordService.createRecord(record);
        });

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to create 100k records: " + (endTime - startTime) + " ms");
    }
}