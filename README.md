# Java Laboratory

每个模块是一个独立的 Spring Boot 应用，用于验证和对比不同技术方案的性能表现。

## 项目结构

```
java-laboratory/
├── common-service/            # 公共服务模块（AOP、日志等工具）
├── cache-experiment-h2/       # 实验：H2 直查 vs Redis 缓存
├── cache-experiment-mysql/    # 实验：MySQL 直查 vs Redis 缓存
├── batch-write-experiment/    # 实验：批量写入 vs 逐条写入
└── crud-experiment-mysql/     # 实验：有索引 vs 无索引 CRUD
```

## 已完成实验

- **H2 直查 vs Redis 缓存性能对比** — [查看实验报告](./cache-experiment-h2/实验报告.md)

- **MySQL 直查 vs Redis 缓存性能对比** — [查看实验报告](./cache-experiment-mysql/实验报告.md)

- **批量写入 vs 逐条写入性能对比** — [查看实验报告](./batch-write-experiment/实验报告.md)

- **有索引 vs 无索引 CRUD 性能对比** — [查看实验报告](./crud-experiment-mysql/实验报告.md)
