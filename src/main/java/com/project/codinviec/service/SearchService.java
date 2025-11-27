package com.project.codinviec.service;

import com.project.codinviec.dto.SearchDTO;

public interface SearchService {
    SearchDTO getSearch(String keyword);
}
