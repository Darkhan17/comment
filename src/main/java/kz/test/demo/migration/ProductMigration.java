package kz.test.demo.migration;

import com.google.gson.Gson;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import kz.test.demo.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@ChangeUnit(id = "product-migration", order = "2", author = "Test-user", systemVersion = "1")
public class ProductMigration extends MigrationReader {

    private final static String COLLECTION_NAME = "products";
    private final MongoTemplate mongoTemplate;

    public ProductMigration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @Execution
    public void migrate() {
        String fileName = "products.json";
        InputStreamReader inputStreamReader = getInputStreamReader(fileName);
        Product[] products = new Gson().fromJson(inputStreamReader, Product[].class);
        mongoTemplate.insertAll(List.of(products));
    }

    @RollbackExecution
    public void rollbackExecution(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(COLLECTION_NAME);
    }
}
