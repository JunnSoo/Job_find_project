CREATE TABLE IF NOT EXISTS status_special
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS status_special_job
(
    id_job            INT NOT NULL,
    id_status_special INT NOT NULL,
    PRIMARY KEY (id_job, id_status_special)
);

CREATE TABLE IF NOT EXISTS status_special_company
(
    id_company        CHAR(36) NOT NULL,
    id_status_special INT NOT NULL,
    PRIMARY KEY (id_company, id_status_special)
);

ALTER TABLE status_special_job
    ADD CONSTRAINT fk_status_special_job_job
        FOREIGN KEY (id_job) REFERENCES job(id)
            ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE status_special_job
    ADD CONSTRAINT fk_status_special_job_status_special
        FOREIGN KEY (id_status_special) REFERENCES status_special(id)
            ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE status_special_company
    ADD CONSTRAINT fk_status_special_company_company
        FOREIGN KEY (id_company) REFERENCES company(id)
            ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE status_special_company
    ADD CONSTRAINT fk_status_special_company_status_special
        FOREIGN KEY (id_status_special) REFERENCES status_special(id)
            ON UPDATE CASCADE ON DELETE CASCADE;


INSERT INTO status_special (id, title) VALUES
                                       (1,'hot'),
                                       (2,'urgent'),
                                       (3,'superhot'),
                                       (4,'featured'),
                                       (5,'spotlight');

INSERT INTO status_special_company (id_company, id_status_special) VALUES
("650e8400-e29b-41d4-a716-446655440001", 1),
("650e8400-e29b-41d4-a716-446655440002", 2),
("650e8400-e29b-41d4-a716-446655440003", 3),
("650e8400-e29b-41d4-a716-446655440004", 4),
("650e8400-e29b-41d4-a716-446655440005", 5);

INSERT INTO status_special_job (id_job, id_status_special) VALUES
(1,1),
(2,2),
(3,3),
(4,4),
(5,5);




