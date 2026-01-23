CREATE TABLE IF NOT EXISTS job_status(
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(100) NOT NULL
);

INSERT INTO job_status (id, name) VALUES
                                            (1,  'Đang đăng tuyển'),
                                            (2,  'Đóng tuyển'),
                                            (3,  'Quá hạn');

ALTER TABLE job
    ADD COLUMN job_status_id INT;

UPDATE job
SET job_status_id = 1
WHERE job_status_id IS NULL;

ALTER TABLE job
    ADD CONSTRAINT fk_job_job_status
        FOREIGN KEY (job_status_id)
            REFERENCES job_status(id);

ALTER TABLE job
    MODIFY job_status_id INT NOT NULL;
