package kz.test.demo.migration;

import com.google.gson.Gson;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import kz.test.demo.domain.model.Product;
import kz.test.demo.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Slf4j
@ChangeUnit(id = "user-migration", order = "1", author = "Test-user", systemVersion = "1")
public class UserMigration extends MigrationReader{

    private final static String COLLECTION_NAME = "users";
    private final MongoTemplate mongoTemplate;

    public UserMigration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @Execution
    public void migrate() {
        String fileName = "users.json";
        InputStreamReader inputStreamReader = getInputStreamReader(fileName);
        User[] users = new Gson().fromJson(inputStreamReader, User[].class);
        mongoTemplate.insertAll(List.of(users));
    }

    @RollbackExecution
    public void rollbackExecution(MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(COLLECTION_NAME);
    }
}
