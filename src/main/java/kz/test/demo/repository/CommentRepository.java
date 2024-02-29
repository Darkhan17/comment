package kz.test.demo.repository;

import kz.test.demo.domain.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>,
        QuerydslPredicateExecutor<Comment> {

}
