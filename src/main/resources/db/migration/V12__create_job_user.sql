CREATE TABLE IF NOT EXISTS job_user (
                          job_id  int NOT NULL,
                          user_id  CHAR(36)  NOT NULL,
                          PRIMARY KEY (job_id, user_id)
);
ALTER TABLE job_user
    ADD CONSTRAINT fk_job_user_job
        FOREIGN KEY (job_id)
            REFERENCES job(id)
            ON DELETE CASCADE;

ALTER TABLE job_user
    ADD CONSTRAINT fk_job_user_user
        FOREIGN KEY (user_id)
            REFERENCES user(id)
            ON DELETE CASCADE;

ALTER TABLE user
    ADD COLUMN cv TEXT;
