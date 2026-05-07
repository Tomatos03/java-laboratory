package com.tomatos.lab.batch.service.batch;

import com.tomatos.lab.batch.entity.Product;
import com.tomatos.lab.batch.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchWriteService {

    private final ProductRepository productRepository;

    @Transactional
    public BatchWriteResult writeBatch(int batchSize, int totalCount) {
        long startTime = System.nanoTime();
        int insertedCount = 0;

        List<Product> batch = new ArrayList<>(batchSize);

        for (int i = 0; i < totalCount; i++) {
            Product product = new Product();
            product.setName("Product-" + (i + 1));
            product.setPrice(BigDecimal.valueOf(Math.random() * 1000));
            product.setQuantity((int) (Math.random() * 100) + 1);
            product.setCreatedAt(LocalDateTime.now());

            batch.add(product);

            if (batch.size() >= batchSize) {
                productRepository.saveAll(batch);
                insertedCount += batch.size();
                batch.clear();
            }
        }

        if (!batch.isEmpty()) {
            productRepository.saveAll(batch);
            insertedCount += batch.size();
        }

        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;

        return new BatchWriteResult(
            insertedCount,
            durationMs,
            batchSize,
            (double) insertedCount / (durationMs / 1000.0)
        );
    }

    public static class BatchWriteResult {
        public int recordsInserted;
        public long durationMs;
        public int batchSize;
        public double throughputPerSec;

        public BatchWriteResult(int recordsInserted, long durationMs, int batchSize, double throughputPerSec) {
            this.recordsInserted = recordsInserted;
            this.durationMs = durationMs;
            this.batchSize = batchSize;
            this.throughputPerSec = throughputPerSec;
        }
    }
}

