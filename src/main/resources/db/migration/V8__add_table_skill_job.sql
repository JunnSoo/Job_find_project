CREATE TABLE IF NOT EXISTS available_skills_job
(
    id_job            INT NOT NULL,
    id_available_skills INT NOT NULL,
    PRIMARY KEY (id_job, id_available_skills)
);

ALTER TABLE available_skills_job
    ADD CONSTRAINT fk_available_skills_job_job
        FOREIGN KEY (id_job) REFERENCES job(id)
            ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE available_skills_job
    ADD CONSTRAINT fk_available_skills_job_available_skills
        FOREIGN KEY (id_available_skills) REFERENCES available_skills(id)
            ON UPDATE CASCADE ON DELETE CASCADE;

INSERT INTO available_skills_job (id_job, id_available_skills)
VALUES
    (1,1),
    (1,2),

    (2,3),
    (2,4),
    (3,5),
    (3,6),
    (4,7),
    (4,8),
    (5,9),
    (5,10),
    (6,11),
    (6,12);

ALTER TABLE job
ADD COLUMN is_agreed_salary BOOLEAN NOT NULL DEFAULT FALSE,
ADD COLUMN salary DOUBLE PRECISION NOT NULL DEFAULT 0;