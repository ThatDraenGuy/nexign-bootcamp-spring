package com.draen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.draen.data"})
public class Config {
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        var entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setPackagesToScan("com.draen.domain.entity");
//        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return entityManagerFactory;
//    }
}
