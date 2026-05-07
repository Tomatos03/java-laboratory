# Architecture Overview

## Project Structure

Java Laboratory is a multi-module Maven project for Java technology experiments. Each experiment is a standalone Spring Boot application in its own module.

```
java-laboratory/
├── pom.xml                      # Parent POM (aggregator, manages dependency versions)
├── cache-experiment-h2/         # Module: H2 direct query vs Redis cache experiment
├── cache-experiment-mysql/      # Module: MySQL direct query vs Redis cache experiment
└── .claude/
    ├── CLAUDE.md               # This file
    └── rules/                  # Rule files
```

## Module Design Pattern

Each module is an independent Spring Boot application with internal path-based separation:

- `/api/direct/**` - Direct database query implementation
- `/api/cache/**` - Redis cache-first implementation

This design enables performance comparison of both approaches in a single process.

## Key Technologies

- **Java**: 17
- **Build**: Maven 3 (multi-module)
- **Web**: Spring Boot 3.2.5
- **ORM**: MyBatis-Plus 3.5.6
- **Database**: H2 (in-memory for experiments) / MySQL
- **Cache**: Redis
- **Utilities**: Lombok, Jackson

## Module Naming Convention

- Format: `{experiment-type}-{reference-group}`
- Examples: `cache-experiment-h2`, `cache-experiment-mysql`
- Package naming: `com.tomatos.lab.{module-name}`

## Data Layer Conventions

- Entities use Lombok `@Data` annotation
- Mappers extend `BaseMapper<T>` from MyBatis-Plus
- Complex queries use annotation-based SQL
- Performance metrics logged at controller layer with execution time
