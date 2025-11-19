package com.project.it_job.service;


import com.project.it_job.dto.GoogleInforDTO;
import com.project.it_job.entity.auth.User;

public interface GoogleService {
    User getUsersLogin(GoogleInforDTO googleInforDTO);
}
