CREATE DATABASE IF NOT EXISTS codinviec_db;

USE codinviec_db;

CREATE TABLE IF NOT EXISTS role
(
    id             CHAR(36)                            NOT NULL PRIMARY KEY,
    role_name      VARCHAR(80)                         NOT NULL,
    description    VARCHAR(255)                        NULL,
    created_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_person CHAR(36)                            NULL,
    CONSTRAINT uk_role_name UNIQUE (role_name)
);

CREATE TABLE IF NOT EXISTS company_size
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    min_employees INT NOT NULL,
    max_employees INT NOT NULL
);

CREATE TABLE IF NOT EXISTS province
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ward
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    province_id INT          NULL
);

CREATE TABLE IF NOT EXISTS industry
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS job_level
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS degree_level
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS employment_type
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS experience
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS language
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS level_language
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS available_skills
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS group_core_skill
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS category
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255)                        NOT NULL,
    parent_id      INT                                 NULL,
    created_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_person VARCHAR(255)                        NULL
);

CREATE TABLE IF NOT EXISTS payment_method
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         TEXT                                NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS payment_status
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT uk_payment_status_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS report_status
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(55) NOT NULL
);

-- ============================================
-- MAIN ENTITIES
-- ============================================

CREATE TABLE IF NOT EXISTS company
(
    id              CHAR(36)                            NOT NULL PRIMARY KEY,
    name            VARCHAR(255)                        NOT NULL,
    description     TEXT                                NULL,
    address         TEXT                                NULL,
    website         TEXT                                NULL,
    company_size_id INT                                 NULL,
    logo            VARCHAR(255)                        NULL,
    created_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_person  VARCHAR(255)                        NULL
);

CREATE TABLE IF NOT EXISTS user
(
    id               CHAR(36)                             NOT NULL PRIMARY KEY,
    email            VARCHAR(255)                         NOT NULL,
    password         VARCHAR(250)                         NOT NULL,
    first_name       VARCHAR(255)                         NULL,
    last_name        VARCHAR(255)                         NULL,
    role_id          CHAR(36)                             NULL,
    avatar           VARCHAR(255)                         NULL,
    phone            VARCHAR(30)                          NULL,
    gender           VARCHAR(30)                          NULL,
    education        TEXT                                 NULL,
    address          TEXT                                 NULL,
    website_link     TEXT                                 NULL,
    birth_date       TIMESTAMP                            NULL,
    is_find_job      TINYINT(1) DEFAULT 0                 NULL,
    company_id       CHAR(36)                             NULL,
    created_date     TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date     TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_person   VARCHAR(255)                         NULL,
    group_soft_skill VARCHAR(255)                         NULL,
    CONSTRAINT uk_user_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS job
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    job_position       VARCHAR(255)                        NOT NULL,
    company_id         CHAR(36)                            NOT NULL,
    detail_address     TEXT                                NULL,
    description_job    TEXT                                NULL,
    requirement        TEXT                                NULL,
    benefits           TEXT                                NULL,
    province_id        INT                                 NULL,
    industry_id        INT                                 NULL,
    job_level_id       INT                                 NULL,
    degree_level_id    INT                                 NULL,
    employment_type_id INT                                 NULL,
    experience_id      INT                                 NULL,
    created_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================
-- USER PROFILE TABLES
-- ============================================

CREATE TABLE IF NOT EXISTS project
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_id     CHAR(36)     NOT NULL,
    name        VARCHAR(255) NOT NULL,
    start_date  DATE         NULL,
    end_date    DATE         NULL,
    project_url VARCHAR(255) NULL,
    company     VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS certificate
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    user_id          CHAR(36)     NOT NULL,
    certificate_name VARCHAR(255) NOT NULL,
    organization     VARCHAR(255) NULL,
    date             DATE         NULL,
    link             VARCHAR(255) NULL,
    description      TEXT         NULL
);

CREATE TABLE IF NOT EXISTS award
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    user_id      CHAR(36)     NOT NULL,
    award_name   VARCHAR(255) NOT NULL,
    organization VARCHAR(255) NULL,
    date         TIMESTAMP    NULL,
    description  TEXT         NULL
);

CREATE TABLE IF NOT EXISTS language_skill
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    language_id       INT      NOT NULL,
    level_language_id INT      NOT NULL,
    user_id           CHAR(36) NOT NULL
);

CREATE TABLE IF NOT EXISTS available_skill_experience
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    id_group_core      INT      NULL,
    available_skill_id INT      NOT NULL,
    experience_id      INT      NOT NULL,
    user_id            CHAR(36) NOT NULL
);

CREATE TABLE IF NOT EXISTS soft_skills_name
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(50) NOT NULL,
    user_id CHAR(36)    NOT NULL
);

CREATE TABLE IF NOT EXISTS cv_user
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    candidate_id CHAR(36)                             NOT NULL,
    version      INT                                  NOT NULL,
    file_url     VARCHAR(255)                         NOT NULL,
    title        VARCHAR(255)                         NULL,
    created_at   TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_active    TINYINT(1) DEFAULT 0                 NOT NULL,
    CONSTRAINT uk_cv_user_candidate_version UNIQUE (candidate_id, version)
);

CREATE INDEX idx_cv_user_candidate ON cv_user (candidate_id);

-- ============================================
-- CONTENT TABLES
-- ============================================

CREATE TABLE IF NOT EXISTS blog
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    title             VARCHAR(255)                         NOT NULL,
    picture           VARCHAR(255)                         NULL,
    short_description TEXT                                 NULL,
    description       LONGTEXT                             NULL,
    is_high_light     TINYINT(1) DEFAULT 0                 NOT NULL,
    created_date      TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date      TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_person    VARCHAR(255)                         NULL
);


CREATE TABLE IF NOT EXISTS review
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255)                        NULL,
    description  TEXT                                NULL,
    company_id   CHAR(36)                            NULL,
    rated        INT                                 NULL,
    user_id      CHAR(36)                            NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS report
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    title          TEXT                                NOT NULL,
    description    TEXT                                NOT NULL,
    image_url      TEXT                                NULL,
    status_id      INT                                 NULL,
    created_report CHAR(36)                            NOT NULL,
    reported_user  CHAR(36)                            NULL,
    reported_job   INT                                 NULL,
    created_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- ============================================
-- WISHLIST TABLES
-- ============================================

CREATE TABLE IF NOT EXISTS wishlist_job
(
    job_id  INT      NOT NULL,
    user_id CHAR(36) NOT NULL,
    PRIMARY KEY (job_id, user_id)
);

CREATE TABLE IF NOT EXISTS wishlist_candidate
(
    hr_id        CHAR(36) NOT NULL,
    candidate_id CHAR(36) NOT NULL,
    PRIMARY KEY (hr_id, candidate_id)
);

-- ============================================
-- PAYMENT TABLES
-- ============================================

CREATE TABLE IF NOT EXISTS service_product
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255)                        NOT NULL,
    description  TEXT                                NULL,
    price        DOUBLE    DEFAULT 0                 NOT NULL,
    images       VARCHAR(255)                        NULL,
    user_id      CHAR(36)                            NULL,
    job_id       INT                                 NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_service_user ON service_product (user_id);

CREATE TABLE IF NOT EXISTS payment
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    title              TEXT                                NULL,
    description        TEXT                                NULL,
    payment_method_id  INT                                 NOT NULL,
    status             INT                                 NOT NULL,
    service_product_id INT                                 NOT NULL,
    user_id            CHAR(36)                            NOT NULL,
    created_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_payment_method ON payment (payment_method_id);
CREATE INDEX idx_payment_service ON payment (service_product_id);
CREATE INDEX idx_payment_status ON payment (status);
CREATE INDEX idx_payment_user ON payment (user_id);

-- ============================================
-- FOREIGN KEY CONSTRAINTS
-- ============================================
use codinviec_db;
-- Ward foreign keys
ALTER TABLE ward
    ADD CONSTRAINT fk_ward_province
        FOREIGN KEY (province_id) REFERENCES province (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

-- Category foreign keys
ALTER TABLE category
    ADD CONSTRAINT fk_category_parent
        FOREIGN KEY (parent_id) REFERENCES category (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

-- Company foreign keys
ALTER TABLE company
    ADD CONSTRAINT fk_company_company_size
        FOREIGN KEY (company_size_id) REFERENCES company_size (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

-- User foreign keys
ALTER TABLE user
    ADD CONSTRAINT fk_user_company
        FOREIGN KEY (company_id) REFERENCES company (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_user_role
        FOREIGN KEY (role_id) REFERENCES role (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

-- Job foreign keys
ALTER TABLE job
    ADD CONSTRAINT fk_job_company
        FOREIGN KEY (company_id) REFERENCES company (id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_job_province
        FOREIGN KEY (province_id) REFERENCES province (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_job_industry
        FOREIGN KEY (industry_id) REFERENCES industry (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_job_job_level
        FOREIGN KEY (job_level_id) REFERENCES job_level (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_job_degree_level
        FOREIGN KEY (degree_level_id) REFERENCES degree_level (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_job_employment_type
        FOREIGN KEY (employment_type_id) REFERENCES employment_type (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_job_experience
        FOREIGN KEY (experience_id) REFERENCES experience (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

-- Project foreign keys
ALTER TABLE project
    ADD CONSTRAINT fk_project_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE;

-- Certificate foreign keys
ALTER TABLE certificate
    ADD CONSTRAINT fk_certificate_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE;

-- Award foreign keys
ALTER TABLE award
    ADD CONSTRAINT fk_award_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE;

-- Language Skill foreign keys
ALTER TABLE language_skill
    ADD CONSTRAINT fk_language_skill_language
        FOREIGN KEY (language_id) REFERENCES language (id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_language_skill_level
        FOREIGN KEY (level_language_id) REFERENCES level_language (id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_language_skill_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE;

-- Available Skill Experience foreign keys
ALTER TABLE available_skill_experience
    ADD CONSTRAINT fk_skill_experience_group_core
        FOREIGN KEY (id_group_core) REFERENCES group_core_skill (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_skill_experience_skill
        FOREIGN KEY (available_skill_id) REFERENCES available_skills (id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_skill_experience_experience
        FOREIGN KEY (experience_id) REFERENCES experience (id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_skill_experience_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE;

-- Soft Skills Name foreign keys
ALTER TABLE soft_skills_name
    ADD CONSTRAINT fk_soft_skills_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE;

-- CV User foreign keys
ALTER TABLE cv_user
    ADD CONSTRAINT fk_cv_user_candidate
        FOREIGN KEY (candidate_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE;

-- Review foreign keys
ALTER TABLE review
    ADD CONSTRAINT fk_review_company
        FOREIGN KEY (company_id) REFERENCES company (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_review_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

-- Report foreign keys
ALTER TABLE report
    ADD CONSTRAINT fk_report_status
        FOREIGN KEY (status_id) REFERENCES report_status (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_report_created_user
        FOREIGN KEY (created_report) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_report_reported_user
        FOREIGN KEY (reported_user) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_report_reported_job
        FOREIGN KEY (reported_job) REFERENCES job (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

-- Wishlist Job foreign keys
ALTER TABLE wishlist_job
    ADD CONSTRAINT fk_wishlist_job_job
        FOREIGN KEY (job_id) REFERENCES job (id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_wishlist_job_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE;

-- Wishlist Candidate foreign keys
ALTER TABLE wishlist_candidate
    ADD CONSTRAINT fk_wishlist_candidate_hr
        FOREIGN KEY (hr_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_wishlist_candidate_candidate
        FOREIGN KEY (candidate_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE;

-- Service Product foreign keys
ALTER TABLE service_product
    ADD CONSTRAINT fk_service_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE SET NULL,
    ADD CONSTRAINT fk_service_product_job
        FOREIGN KEY (job_id) REFERENCES job (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

-- Payment foreign keys
ALTER TABLE payment
    ADD CONSTRAINT fk_payment_method
        FOREIGN KEY (payment_method_id) REFERENCES payment_method (id)
            ON UPDATE CASCADE ON DELETE RESTRICT,
    ADD CONSTRAINT fk_payment_service
        FOREIGN KEY (service_product_id) REFERENCES service_product (id)
            ON UPDATE CASCADE ON DELETE RESTRICT,
    ADD CONSTRAINT fk_payment_status
        FOREIGN KEY (status) REFERENCES payment_status (id)
            ON UPDATE CASCADE ON DELETE RESTRICT,
    ADD CONSTRAINT fk_payment_user
        FOREIGN KEY (user_id) REFERENCES user (id)
            ON UPDATE CASCADE ON DELETE CASCADE;
