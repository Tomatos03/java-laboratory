---
paths:
  - "**/*.java"
---

# Java Conventions

## Naming & Structure

- Package structure: `com.tomatos.lab.{module}.{layer}`
  - Layers: `entity`, `mapper`, `service`, `controller`, `config`
- Entity classes: Use Lombok `@Data` for getters/setters/equals/hashCode
- Service layer: Separate `direct` and `cache` packages for implementation variants
- Controllers: Separate `direct` and `cache` packages mirroring service structure

## MyBatis-Plus Usage

**Constraint**: Database operation framework must be unified to use MyBatis-Plus across all modules.

- All mappers extend `BaseMapper<T>` from MyBatis-Plus
- Use annotation-based SQL for complex queries (avoid XML mappers when possible)
- Mapper files go in `src/main/resources/mapper/` if needed
- Enable SQL logging in `application.yml` for debugging:
  ```yaml
  mybatis-plus:
    configuration:
      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  ```

## Performance Instrumentation

- Use `@TimingTrack` annotation from `common-service` module for automatic execution time logging
- Add the annotation to controller methods to track API performance
- The annotation automatically logs method name and execution time in milliseconds
- Example:
  ```java
  @TimingTrack
  @GetMapping("/users/{id}")
  public User getUser(@PathVariable Long id) {
      return userService.findById(id);
  }
  ```
- Do not manually add timing code when `@TimingTrack` is available

## Redis Integration

- Redis configuration in `config/RedisConfig.java`
- Use Spring's `RedisTemplate` or `StringRedisTemplate` for operations
- Cache keys should be prefixed with module name: `{module}:{entity}:{id}`
- Set appropriate TTL for cached data (typically 1-24 hours depending on use case)

## Testing

- Unit tests in `src/test/java/` mirroring source structure
- Use Spring Boot Test starter for integration tests
- Test both direct and cache implementations separately
