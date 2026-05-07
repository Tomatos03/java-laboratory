# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Quick Start

This is a multi-module Maven project for Java technology experiments. Each module is an independent Spring Boot application.

```bash
# Compile and run a module
mvn clean compile -pl cache-experiment-h2
mvn spring-boot:run -pl cache-experiment-h2

# Run tests
mvn test -pl cache-experiment-h2
```

## Documentation

- @rules/architecture.md — Project structure and design patterns
- @rules/java-conventions.md — Java code conventions and MyBatis-Plus usage
- @rules/build-commands.md — Maven commands and development workflow

## Key Principles

- **One experiment = one module**: Each module is a standalone Spring Boot application
- **Dual implementation pattern**: `/api/direct/**` for direct DB queries, `/api/cache/**` for cached queries
- **Performance comparison**: Both implementations run in the same process for easy benchmarking
- **Docker-managed dependencies**: Redis and databases run in Docker containers per module
