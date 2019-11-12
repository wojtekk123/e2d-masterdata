package pl.codeconcept.e2d.e2dmasterdata.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({
        @ComponentScan("pl.codeconcept.e2d.e2dmasterdata.service"),
        @ComponentScan("pl.codeconcept.e2d.e2dmasterdata.database"),
        @ComponentScan("pl.codeconcept.e2d.e2dmasterdata.api")}
)
//@EnableJpaRepositories
//@EnableTransactionManagement
public class ApplicationConfig {

//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("pl.codeconcept.e2d.e2dmasterdata.database.entity");
//        factory.afterPropertiesSet();
//
//        return factory.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }
}