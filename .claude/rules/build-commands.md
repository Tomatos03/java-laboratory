# Build & Development Commands

## Maven Commands

```bash
# Compile entire project
mvn clean compile

# Compile specific module
mvn clean compile -pl cache-experiment-h2

# Run specific module (starts Spring Boot app)
mvn spring-boot:run -pl cache-experiment-h2

# Package all modules
mvn clean package -DskipTests

# Run all tests
mvn test

# Run tests for specific module
mvn test -pl cache-experiment-h2

# Run single test class
mvn test -pl cache-experiment-h2 -Dtest=UserServiceTest

# Run single test method
mvn test -pl cache-experiment-h2 -Dtest=UserServiceTest#testFindById
```

## Docker Dependencies

Each module declares external dependencies (Redis, MySQL) in `docs/docker/docker-compose.yml`.

```bash
# Start dependency services (run from module directory)
cd cache-experiment-h2/docs/docker
docker compose up -d

# Stop services
docker compose down

# View logs
docker compose logs -f redis
```

## Development Workflow

1. Start Docker services for the module you're working on
2. Run the Spring Boot application: `mvn spring-boot:run -pl {module-name}`
3. Access the application at `http://localhost:8080`
4. For H2 module, access H2 console at `http://localhost:8080/h2-console`
5. Run tests: `mvn test -pl {module-name}`

## Module-Specific Ports

- `cache-experiment-h2`: Port 8080 (configured in `application.yml`)
- `cache-experiment-mysql`: Port 8080 (may need adjustment if running simultaneously)
