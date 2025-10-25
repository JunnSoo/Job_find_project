package com.project.it_job.request;

import lombok.Data;


@Data
public class PageRequest {
    private int pageNumer;
    private int pageSize;

    private String sortBy = "createdAtDesc";

    private String keyword;

}
