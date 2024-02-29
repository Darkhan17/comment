package kz.test.demo.controller;


import com.querydsl.core.types.Predicate;
import jakarta.validation.Valid;
import kz.test.demo.domain.model.Comment;
import kz.test.demo.domain.model.dto.CreateCommentDTO;
import kz.test.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("comments")
@Validated
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(
            @RequestBody @Valid CreateCommentDTO createCommentDTO
    ) {
        Comment comment = commentService.createComment(createCommentDTO);
        return ResponseEntity.ok(comment);
    }


    @GetMapping
    public ResponseEntity<Page<Comment>> getComments(
            @QuerydslPredicate(root = Comment.class) Predicate predicate,
            Pageable pageable
    ) {
        return ResponseEntity.ok(commentService.getComments(predicate, pageable));
    }

}
