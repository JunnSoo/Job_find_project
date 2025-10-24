package com.project.it_job.service;

import com.project.it_job.entity.Industry;

import java.util.List;
import java.util.Optional;

public interface IndustryService {
    List<Industry> getAll();
    Optional<Industry> getById(int id);
    Industry create(Industry industry);
    Industry update(int id, Industry industry);
    boolean delete(int id);
}