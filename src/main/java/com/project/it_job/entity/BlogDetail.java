package com.project.it_job.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "blog_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @OneToOne(mappedBy = "blog")
    @MapsId // dùng chung khóa chính với blog
    @JoinColumn(name = "id") // xác định khóa ngoại
    private Blog blog;

}
