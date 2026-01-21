ALTER TABLE job
    ADD COLUMN responsibility TEXT;

UPDATE job
SET responsibility = 'Thực hiện công việc theo mô tả, phối hợp với các bộ phận liên quan, Hợp tác với team để đảm bảo chất lượng, Tham gia code review và mentoring các developer junior'
WHERE responsibility IS NULL;

