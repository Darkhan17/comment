package kz.test.demo.domain.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentDTO {
    @NotNull
    private String text;
    @NotNull
    private String productId;
    @NotNull
    private String authorId;
}
