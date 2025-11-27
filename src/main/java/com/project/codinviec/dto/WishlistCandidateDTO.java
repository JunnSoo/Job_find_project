package com.project.codinviec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishlistCandidateDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String avatar;
    private List<BasicUserDTO> wistlistCandidates;
}
