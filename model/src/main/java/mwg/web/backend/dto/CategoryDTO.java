package mwg.web.backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private Long categoryId;

    private String categoryName;

    private Long amount;

    private boolean voluntary;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
