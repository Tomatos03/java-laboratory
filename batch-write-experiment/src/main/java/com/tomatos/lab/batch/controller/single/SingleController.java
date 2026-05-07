package com.tomatos.lab.batch.controller.single;

import com.tomatos.lab.batch.service.single.SingleWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/single")
@RequiredArgsConstructor
public class SingleController {

    private final SingleWriteService singleWriteService;

    @PostMapping("/write")
    public SingleWriteService.SingleWriteResult write(
            @RequestParam(defaultValue = "100000") int count) {

        long startTime = System.currentTimeMillis();
        SingleWriteService.SingleWriteResult result = singleWriteService.writeSingle(count);
        long totalTime = System.currentTimeMillis() - startTime;

        System.out.println("[单次写入] 总数=" + count +
                         ", 耗时=" + totalTime + "ms, 吞吐量=" +
                         String.format("%.2f", result.throughputPerSec) + " records/sec");

        return result;
    }
}
