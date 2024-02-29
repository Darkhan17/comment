package kz.test.demo.config;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import io.mongock.driver.mongodb.springdata.v4.SpringDataMongoV4Driver;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@Slf4j
public class MongockConfig {

    @Bean
    public MongockApplicationRunner mongockApplicationRunner(
            ApplicationContext springContext,
            MongoTemplate mongoTemplate
    ) {

        SpringDataMongoV4Driver driver = SpringDataMongoV4Driver.withDefaultLock(mongoTemplate);
        driver.enableTransaction();

        return MongockSpringboot.builder()
                                .setDriver(driver)
                                .setEnabled(true)
                                .addMigrationScanPackage("kz.test.demo.migration")
                                .setEventPublisher(springContext)
                                .setSpringContext(springContext)
                                .setTransactionEnabled(false)
                                .buildApplicationRunner();
    }

    @Bean
    public MongoTransactionManager transactionManager(MongoTemplate mongoTemplate) {
        TransactionOptions transactionalOptions = TransactionOptions.builder()
                                                                    .readConcern(ReadConcern.MAJORITY)
                                                                    .readPreference(ReadPreference.primary())
                                                                    .writeConcern(WriteConcern.MAJORITY.withJournal(true))
                                                                    .build();

        return new MongoTransactionManager(mongoTemplate.getMongoDatabaseFactory(), transactionalOptions);
    }

}
