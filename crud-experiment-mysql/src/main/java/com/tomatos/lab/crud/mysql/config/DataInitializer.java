package com.tomatos.lab.crud.mysql.config;

import com.tomatos.lab.crud.mysql.entity.UserNoIndex;
import com.tomatos.lab.crud.mysql.entity.UserWithIndex;
import com.tomatos.lab.crud.mysql.mapper.UserNoIndexMapper;
import com.tomatos.lab.crud.mysql.mapper.UserWithIndexMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserNoIndexMapper userNoIndexMapper;
    private final UserWithIndexMapper userWithIndexMapper;

    private static final long TARGET_COUNT = 2_000_000L;
    private static final int BATCH_SIZE = 3_000;
    private static final int USER_ID_POOL_SIZE = 1_000;

    private long globalCounter = 0;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data initialization...");
        long startTime = System.currentTimeMillis();

        initializeTable("t_user_no_index", () -> userNoIndexMapper.selectCount(null), this::insertUserNoIndex);
        initializeTable("t_user_with_index", () -> userWithIndexMapper.selectCount(null), this::insertUserWithIndex);

        long endTime = System.currentTimeMillis();
        log.info("Data initialization completed in {} ms", endTime - startTime);
    }

    private void initializeTable(String tableName, Supplier<Long> countSupplier, Runnable insertAction) {
        long currentCount = countSupplier.get();
        log.info("Table {} current count: {}", tableName, currentCount);

        if (currentCount >= TARGET_COUNT) {
            log.info("Table {} already has {} records, skipping initialization", tableName, currentCount);
            return;
        }

        long needToInsert = TARGET_COUNT - currentCount;
        log.info("Table {} needs to insert {} records", tableName, needToInsert);

        long insertedCount = 0;
        long batchCount = (needToInsert + BATCH_SIZE - 1) / BATCH_SIZE;

        for (long i = 0; i < batchCount; i++) {
            insertAction.run();
            insertedCount += BATCH_SIZE;
            if ((i + 1) % 10 == 0) {
                log.info("Table {} inserted {} / {} records", tableName, Math.min(insertedCount, needToInsert), needToInsert);
            }
        }

        log.info("Table {} initialization completed, total inserted: {}", tableName, needToInsert);
    }

    private void insertUserNoIndex() {
        List<UserNoIndex> batch = generateUserNoIndexBatch();
        userNoIndexMapper.insertBatch(batch);
    }

    private void insertUserWithIndex() {
        List<UserWithIndex> batch = generateUserWithIndexBatch();
        userWithIndexMapper.insertBatch(batch);
    }

    private List<UserNoIndex> generateUserNoIndexBatch() {
        List<UserNoIndex> batch = new ArrayList<>(BATCH_SIZE);
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < BATCH_SIZE; i++) {
            UserNoIndex user = new UserNoIndex();
            user.setUserId((int) (globalCounter % USER_ID_POOL_SIZE));
            user.setUsername("user_" + System.nanoTime());
            user.setEmail("user_" + System.nanoTime() + "@example.com");
            user.setPhone("1" + String.format("%010d", random.nextInt(1_000_000_000)));
            user.setAddress("Address " + random.nextInt(10000));
            user.setStatus(random.nextInt(2));
            user.setCreateTime(now);
            user.setUpdateTime(now);
            batch.add(user);
            globalCounter++;
        }

        return batch;
    }

    private List<UserWithIndex> generateUserWithIndexBatch() {
        List<UserWithIndex> batch = new ArrayList<>(BATCH_SIZE);
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < BATCH_SIZE; i++) {
            UserWithIndex user = new UserWithIndex();
            user.setUserId((int) (globalCounter % USER_ID_POOL_SIZE));
            user.setUsername("user_" + System.nanoTime());
            user.setEmail("user_" + System.nanoTime() + "@example.com");
            user.setPhone("1" + String.format("%010d", random.nextInt(1_000_000_000)));
            user.setAddress("Address " + random.nextInt(10000));
            user.setStatus(random.nextInt(2));
            user.setCreateTime(now);
            user.setUpdateTime(now);
            batch.add(user);
            globalCounter++;
        }

        return batch;
    }
}
