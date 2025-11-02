package com.project.it_job.service;

import com.project.it_job.dto.SearchDTO;

public interface SearchService {
    SearchDTO getSearch(String keyword);
}
