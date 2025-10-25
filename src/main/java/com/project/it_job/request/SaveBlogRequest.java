package com.project.it_job.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveBlogRequest {
    private String title;
    private String picture;
    private String shortDescription;
}
