package com.tomatos.lab.batch.service.single;

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
public class SingleWriteService {

    private final ProductRepository productRepository;

    @Transactional
    public SingleWriteResult writeSingle(int totalCount) {
        long startTime = System.nanoTime();

        List<Product> products = new ArrayList<>(totalCount);

        for (int i = 0; i < totalCount; i++) {
            Product product = new Product();
            product.setName("Product-" + (i + 1));
            product.setPrice(BigDecimal.valueOf(Math.random() * 1000));
            product.setQuantity((int) (Math.random() * 100) + 1);
            product.setCreatedAt(LocalDateTime.now());

            products.add(product);
        }

        productRepository.saveAll(products);

        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;

        return new SingleWriteResult(
            totalCount,
            durationMs,
            (double) totalCount / (durationMs / 1000.0)
        );
    }

    public static class SingleWriteResult {
        public int recordsInserted;
        public long durationMs;
        public double throughputPerSec;

        public SingleWriteResult(int recordsInserted, long durationMs, double throughputPerSec) {
            this.recordsInserted = recordsInserted;
            this.durationMs = durationMs;
            this.throughputPerSec = throughputPerSec;
        }
    }
}

