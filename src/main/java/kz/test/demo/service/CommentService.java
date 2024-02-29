package kz.test.demo.service;

import com.querydsl.core.types.Predicate;
import kz.test.demo.domain.model.Comment;
import kz.test.demo.domain.model.dto.CreateCommentDTO;
import kz.test.demo.domain.model.exception.ApiException;
import kz.test.demo.mapper.CommentMapper;
import kz.test.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ProductService productService;
    private final UserService userService;
    public Comment createComment(CreateCommentDTO createCommentDTO) {
        Comment comment = commentMapper.map(createCommentDTO);

        if (!productService.existsById(comment.getProductId())) {
            throw new ApiException("Product not found", HttpStatus.BAD_REQUEST.value());
        }

        if (!userService.existById(comment.getAuthorId())) {
            throw new ApiException("User not found", HttpStatus.BAD_REQUEST.value());
        }

        return commentRepository.save(comment);
    }

    public Page<Comment> getComments(Predicate predicate, Pageable pageable) {
        return commentRepository.findAll(predicate, pageable);
    }
}
