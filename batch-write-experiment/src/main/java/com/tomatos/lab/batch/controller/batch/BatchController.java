package com.tomatos.lab.batch.controller.batch;

import com.tomatos.lab.batch.service.batch.BatchWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
public class BatchController {

    private final BatchWriteService batchWriteService;

    @PostMapping("/write")
    public BatchWriteService.BatchWriteResult write(
            @RequestParam(defaultValue = "1000") int size,
            @RequestParam(defaultValue = "100000") int count) {

        long startTime = System.currentTimeMillis();
        BatchWriteService.BatchWriteResult result = batchWriteService.writeBatch(size, count);
        long totalTime = System.currentTimeMillis() - startTime;

        System.out.println("[批量写入] 批大小=" + size + ", 总数=" + count +
                         ", 耗时=" + totalTime + "ms, 吞吐量=" +
                         String.format("%.2f", result.throughputPerSec) + " records/sec");

        return result;
    }
}
