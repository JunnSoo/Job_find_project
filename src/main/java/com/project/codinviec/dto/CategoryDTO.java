package com.project.codinviec.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private int id;
    private String name;
    private int parentId;
    private List<CategoryDTO> children;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
