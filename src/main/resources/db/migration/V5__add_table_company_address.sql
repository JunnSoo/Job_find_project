CREATE TABLE IF NOT EXISTS company_address
(
    id             VARCHAR(36)  NOT NULL,
    company_id     VARCHAR(36)  NOT NULL,
    province_id    INT          NOT NULL,
    ward_id        INT          NOT NULL,

    detail         VARCHAR(500) NOT NULL,
    is_head_office BOOLEAN DEFAULT FALSE,

    PRIMARY KEY (id),

    INDEX          idx_company (company_id),
    INDEX          idx_province (province_id),
    INDEX          idx_ward (ward_id)
);

ALTER TABLE company DROP COLUMN address;


ALTER TABLE company_address
    ADD CONSTRAINT fk_company_address_company
        FOREIGN KEY (company_id)
            REFERENCES company (id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

ALTER TABLE company_address
    ADD CONSTRAINT fk_company_address_province
        FOREIGN KEY (province_id)
            REFERENCES province (id)
            ON UPDATE CASCADE
            ON DELETE RESTRICT;

ALTER TABLE company_address
    ADD CONSTRAINT fk_company_address_ward
        FOREIGN KEY (ward_id)
            REFERENCES ward (id)
            ON UPDATE CASCADE
            ON DELETE RESTRICT;

INSERT INTO company_address (id,
                             company_id,
                             province_id,
                             ward_id,
                             detail,
                             is_head_office)
VALUES (1,
        '650e8400-e29b-41d4-a716-446655440001',
        1,
        1,
        'Tầng 10, Toà nhà ABC',
        TRUE),
       (2,
        '650e8400-e29b-41d4-a716-446655440002',
        2,
        2,
        'Tầng 10, Toà nhà ABC',
        TRUE),
       (3,
        '650e8400-e29b-41d4-a716-446655440003',
        3,
        3,
        'Tầng 10, Toà nhà ABC',
        TRUE),
       (4,
        '650e8400-e29b-41d4-a716-446655440004',
        4,
        4,
        'Tầng 10, Toà nhà ABC',
        TRUE),
       (5,
        '650e8400-e29b-41d4-a716-446655440005',
        5,
        5,
        'Tầng 10, Toà nhà ABC',
        TRUE);
