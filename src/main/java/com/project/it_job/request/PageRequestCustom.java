package com.project.it_job.request;

import lombok.Data;


@Data
public class PageRequestCustom {
    private int pageNumber;
    private int pageSize;

    private String sortBy = "createdAtDesc";

    private String keyword;

}
