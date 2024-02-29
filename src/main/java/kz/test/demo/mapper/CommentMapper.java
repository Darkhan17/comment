package kz.test.demo.mapper;


import kz.test.demo.domain.model.Comment;
import kz.test.demo.domain.model.dto.CreateCommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CommentMapper {
    Comment map(CreateCommentDTO createCommentDTO);
}
