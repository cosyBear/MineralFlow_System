//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContextInitializer;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.support.TestPropertySourceUtils;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@Testcontainers
//@ContextConfiguration(initializers = AbstractDatabaseTest.DataSourceInitializer.class)
//@ActiveProfiles("test")
//public abstract class AbstractDatabaseTest {
//    private static final MySQLContainer<?> DATABASE;
//
//    static {
//        DATABASE = new MySQLContainer<>("mysql:8.0.30")
//                .withDatabaseName("warehouse_db")
//                .withUsername("user")
//                .withPassword("password")
//                .withInitScript("init.sql");
//        DATABASE.start();
//    }
//
//    @BeforeAll
//    static void checkContainerRunning() {
//        Assertions.assertTrue(DATABASE.isRunning(), "Database container should be running");
//    }
//
//    public static class DataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        @Override
//        public void initialize(ConfigurableApplicationContext applicationContext) {
//            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
//                    applicationContext,
//                    "spring.datasource.url=" + DATABASE.getJdbcUrl(),
//                    "spring.datasource.username=" + DATABASE.getUsername(),
//                    "spring.datasource.password=" + DATABASE.getPassword(),
//                    "spring.jpa.hibernate.ddl-auto=create-drop" // Ensure fresh schema for each test
//            );
//        }
//    }
//}
