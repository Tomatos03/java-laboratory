# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Java Laboratory - 一个用于 Java 技术实验的多模块项目。

**核心原则：一个具体的实验，对应项目的一个子模块。** 每个子模块是独立的 Spring Boot 应用，可单独编译、运行和测试。

## Build & Run Commands

```bash
# 编译整个项目
mvn clean compile

# 编译指定模块
mvn clean compile -pl cache-experiment-h2

# 运行指定模块
mvn spring-boot:run -pl cache-experiment-h2

# 打包
mvn clean package -DskipTests

# 运行测试
mvn test
mvn test -pl cache-experiment-h2
```

## Tech Stack

- **Java**: 17
- **Build**: Maven 多模块
- **Web**: Spring Boot 3.2.5
- **ORM**: MyBatis-Plus 3.5.6
- **Database**: H2 (内存数据库，用于实验)
- **Cache**: Redis

## Architecture

```
java-laboratory/
├── pom.xml                      # 父 POM（聚合模块，管理依赖版本）
└── cache-experiment-h2/         # 子模块：H2 直查 vs Redis 缓存实验
```

### Module Design Pattern

每个子模块是一个独立的 Spring Boot 应用，内部通过不同路径区分实现方式：

- `/api/direct/**` - 直接查询数据库的实现
- `/api/cache/**` - 先查 Redis 缓存的实现

这种设计便于在同一进程中对比两种实现的性能差异。

## Conventions

- 子模块命名：`{实验类型}-{参照组}`，如 `cache-experiment-h2`、`cache-experiment-mysql`
- 包名：`com.tomatos.lab.{模块名}`
- 实体类使用 Lombok `@Data` 注解
- Mapper 继承 `BaseMapper<T>`，复杂查询使用注解 SQL
- 性能统计：在 Controller 层记录耗时并输出日志

## Docker 依赖管理

**每个子模块的外部依赖（如 Redis、MySQL）通过 `docker-compose.yml` 声明，使用 Docker 进行测试。**

```bash
# 启动依赖服务
docker compose up -d

# 停止依赖服务
docker compose down
```

模块目录结构示例：
```
cache-experiment-h2/
├── docs/
│   └── docker/
│       └── docker-compose.yml  # 声明 Redis 等依赖
├── pom.xml
└── src/
```
