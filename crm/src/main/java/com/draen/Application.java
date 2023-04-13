package com.draen;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        var entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setPackagesToScan("com.draen.domain.entity");
//        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return entityManagerFactory;
//    }
}