-- ============================================
-- V2: CODINVIEC SAMPLE DATA (IDEMPOTENT)
-- Sử dụng INSERT IGNORE để bỏ qua nếu data đã tồn tại
-- ============================================

-- Role
INSERT IGNORE INTO role (id, role_name, description)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'ADMIN', 'Quản trị viên hệ thống'),
       ('550e8400-e29b-41d4-a716-446655440002', 'HR', 'Nhà tuyển dụng'),
       ('550e8400-e29b-41d4-a716-446655440003', 'USER', 'Người dùng');

-- Company Size
INSERT IGNORE INTO company_size (id, min_employees, max_employees)
VALUES (1, 1, 10),
       (2, 11, 50),
       (3, 51, 200),
       (4, 201, 500),
       (5, 501, 1000),
       (6, 1001, 5000),
       (7, 5001, 10000),
       (8, 10001, 999999);

-- Industry
INSERT IGNORE INTO industry (id, name)
VALUES (1, 'Công nghệ thông tin'),
       (2, 'Phần mềm'),
       (3, 'Thương mại điện tử'),
       (4, 'Tài chính - Ngân hàng'),
       (5, 'Viễn thông'),
       (6, 'Game'),
       (7, 'Blockchain'),
       (8, 'AI/Machine Learning'),
       (9, 'Cloud Computing'),
       (10, 'Cybersecurity'),
       (11, 'Mobile App Development'),
       (12, 'Web Development'),
       (13, 'Data Science'),
       (14, 'DevOps'),
       (15, 'UI/UX Design');

-- Job Level
INSERT IGNORE INTO job_level (id, name)
VALUES (1, 'Intern'),
       (2, 'Fresher'),
       (3, 'Junior'),
       (4, 'Middle'),
       (5, 'Senior'),
       (6, 'Lead'),
       (7, 'Manager'),
       (8, 'Director'),
       (9, 'VP'),
       (10, 'C-Level');

-- Degree Level
INSERT IGNORE INTO degree_level (id, name)
VALUES (1, 'Không yêu cầu'),
       (2, 'Trung cấp'),
       (3, 'Cao đẳng'),
       (4, 'Đại học'),
       (5, 'Thạc sĩ'),
       (6, 'Tiến sĩ');

-- Employment Type
INSERT IGNORE INTO employment_type (id, name)
VALUES (1, 'Full-time'),
       (2, 'Part-time'),
       (3, 'Contract'),
       (4, 'Freelance'),
       (5, 'Internship'),
       (6, 'Remote'),
       (7, 'Hybrid');

-- Experience
INSERT IGNORE INTO experience (id, name)
VALUES (1, 'Chưa có kinh nghiệm'),
       (2, 'Dưới 1 năm'),
       (3, '1-2 năm'),
       (4, '2-3 năm'),
       (5, '3-5 năm'),
       (6, '5-7 năm'),
       (7, '7-10 năm'),
       (8, 'Trên 10 năm');

-- Language
INSERT IGNORE INTO language (id, name)
VALUES (1, 'Tiếng Việt'),
       (2, 'Tiếng Anh'),
       (3, 'Tiếng Nhật'),
       (4, 'Tiếng Hàn'),
       (5, 'Tiếng Trung'),
       (6, 'Tiếng Pháp'),
       (7, 'Tiếng Đức'),
       (8, 'Tiếng Tây Ban Nha');

-- Level Language
INSERT IGNORE INTO level_language (id, name)
VALUES (1, 'Cơ bản'),
       (2, 'Trung bình'),
       (3, 'Khá'),
       (4, 'Tốt'),
       (5, 'Thành thạo'),
       (6, 'Bản ngữ');

-- Available Skills
INSERT IGNORE INTO available_skills (id, name)
VALUES (1, 'Java'),
       (2, 'Python'),
       (3, 'JavaScript'),
       (4, 'TypeScript'),
       (5, 'C++'),
       (6, 'C#'),
       (7, 'PHP'),
       (8, 'Ruby'),
       (9, 'Go'),
       (10, 'Rust'),
       (11, 'Swift'),
       (12, 'Kotlin'),
       (13, 'React'),
       (14, 'Angular'),
       (15, 'Vue.js'),
       (16, 'Node.js'),
       (17, 'Spring Boot'),
       (18, 'Django'),
       (19, 'Laravel'),
       (20, 'Flutter'),
       (21, 'React Native'),
       (22, 'MySQL'),
       (23, 'PostgreSQL'),
       (24, 'MongoDB'),
       (25, 'Redis'),
       (26, 'Docker'),
       (27, 'Kubernetes'),
       (28, 'AWS'),
       (29, 'Azure'),
       (30, 'Git'),
       (31, 'Linux'),
       (32, 'Agile'),
       (33, 'Scrum');

-- Group Core Skill
INSERT IGNORE INTO group_core_skill (id, name)
VALUES (1, 'Backend Development'),
       (2, 'Frontend Development'),
       (3, 'Full-stack Development'),
       (4, 'Mobile Development'),
       (5, 'DevOps'),
       (6, 'Data Science'),
       (7, 'Machine Learning'),
       (8, 'Cloud Architecture'),
       (9, 'Security'),
       (10, 'UI/UX Design'),
       (11, 'QA/Testing'),
       (12, 'Project Management');

-- Category
INSERT IGNORE INTO category (id, name, parent_id)
VALUES (1, 'Công nghệ thông tin', NULL),
       (2, 'Phần mềm', 1),
       (3, 'Web Development', 1),
       (4, 'Mobile Development', 1),
       (5, 'Data Science', 1),
       (6, 'AI/ML', 1),
       (7, 'DevOps', 1),
       (8, 'Cybersecurity', 1),
       (9, 'Tài chính', NULL),
       (10, 'Ngân hàng', 9),
       (11, 'Bảo hiểm', 9);

-- Payment Method
INSERT IGNORE INTO payment_method (id, name)
VALUES (1, 'Chuyển khoản ngân hàng'),
       (2, 'Ví điện tử'),
       (3, 'Thẻ tín dụng'),
       (4, 'Thẻ ghi nợ'),
       (5, 'Tiền mặt'),
       (6, 'PayPal'),
       (7, 'Momo'),
       (8, 'ZaloPay'),
       (9, 'VNPay');

-- Payment Status
INSERT IGNORE INTO payment_status (id, name)
VALUES (1, 'Pending'),
       (2, 'Processing'),
       (3, 'Completed'),
       (4, 'Failed'),
       (5, 'Cancelled'),
       (6, 'Refunded');

-- Report Status
INSERT IGNORE INTO report_status (id, name)
VALUES (1, 'Pending'),
       (2, 'Under Review'),
       (3, 'Resolved'),
       (4, 'Rejected'),
       (5, 'Closed');

-- Province (63 tỉnh thành Việt Nam)
INSERT IGNORE INTO province (id, name)
VALUES (1, 'Hà Nội'),
       (2, 'Hồ Chí Minh'),
       (3, 'Đà Nẵng'),
       (4, 'Hải Phòng'),
       (5, 'Cần Thơ'),
       (6, 'An Giang'),
       (7, 'Bà Rịa - Vũng Tàu'),
       (8, 'Bắc Giang'),
       (9, 'Bắc Kạn'),
       (10, 'Bạc Liêu'),
       (11, 'Bắc Ninh'),
       (12, 'Bến Tre'),
       (13, 'Bình Định'),
       (14, 'Bình Dương'),
       (15, 'Bình Phước'),
       (16, 'Bình Thuận'),
       (17, 'Cà Mau'),
       (18, 'Cao Bằng'),
       (19, 'Đắk Lắk'),
       (20, 'Đắk Nông'),
       (21, 'Điện Biên'),
       (22, 'Đồng Nai'),
       (23, 'Đồng Tháp'),
       (24, 'Gia Lai'),
       (25, 'Hà Giang'),
       (26, 'Hà Nam'),
       (27, 'Hà Tĩnh'),
       (28, 'Hải Dương'),
       (29, 'Hậu Giang'),
       (30, 'Hòa Bình'),
       (31, 'Hưng Yên'),
       (32, 'Khánh Hòa'),
       (33, 'Kiên Giang'),
       (34, 'Kon Tum'),
       (35, 'Lai Châu'),
       (36, 'Lâm Đồng'),
       (37, 'Lạng Sơn'),
       (38, 'Lào Cai'),
       (39, 'Long An'),
       (40, 'Nam Định'),
       (41, 'Nghệ An'),
       (42, 'Ninh Bình'),
       (43, 'Ninh Thuận'),
       (44, 'Phú Thọ'),
       (45, 'Phú Yên'),
       (46, 'Quảng Bình'),
       (47, 'Quảng Nam'),
       (48, 'Quảng Ngãi'),
       (49, 'Quảng Ninh'),
       (50, 'Quảng Trị'),
       (51, 'Sóc Trăng'),
       (52, 'Sơn La'),
       (53, 'Tây Ninh'),
       (54, 'Thái Bình'),
       (55, 'Thái Nguyên'),
       (56, 'Thanh Hóa'),
       (57, 'Thừa Thiên Huế'),
       (58, 'Tiền Giang'),
       (59, 'Trà Vinh'),
       (60, 'Tuyên Quang'),
       (61, 'Vĩnh Long'),
       (62, 'Vĩnh Phúc'),
       (63, 'Yên Bái');

-- Ward (Một số quận/huyện chính)
INSERT IGNORE INTO ward (id, name, province_id)
VALUES
-- Hà Nội (1)
(1, 'Quận Ba Đình', 1),
(2, 'Quận Hoàn Kiếm', 1),
(3, 'Quận Tây Hồ', 1),
(4, 'Quận Cầu Giấy', 1),
(5, 'Quận Đống Đa', 1),
(6, 'Quận Hai Bà Trưng', 1),
(7, 'Quận Thanh Xuân', 1),
(8, 'Quận Hà Đông', 1),
(9, 'Quận Nam Từ Liêm', 1),
(10, 'Quận Bắc Từ Liêm', 1),
-- Hồ Chí Minh (2)
(11, 'Quận 1', 2),
(12, 'Quận 3', 2),
(13, 'Quận 7', 2),
(14, 'Quận Bình Thạnh', 2),
(15, 'Quận Tân Bình', 2),
(16, 'Quận Phú Nhuận', 2),
(17, 'Quận Thủ Đức', 2),
(18, 'Quận Gò Vấp', 2),
-- Đà Nẵng (3)
(19, 'Quận Hải Châu', 3),
(20, 'Quận Thanh Khê', 3),
(21, 'Quận Sơn Trà', 3),
(22, 'Quận Ngũ Hành Sơn', 3);

-- ============================================
-- MAIN ENTITIES
-- ============================================

-- Company
INSERT IGNORE INTO company (id, name, description, address, website, company_size_id, logo)
VALUES ('650e8400-e29b-41d4-a716-446655440001', 'FPT Software', 'Công ty phần mềm hàng đầu Việt Nam',
        'Hà Nội, Việt Nam', 'https://www.fpt-software.com', 6, 'https://example.com/logo/fpt.png'),
       ('650e8400-e29b-41d4-a716-446655440002', 'Viettel Solutions', 'Giải pháp công nghệ thông tin',
        'Hà Nội, Việt Nam', 'https://www.viettel-solutions.com', 6, 'https://example.com/logo/viettel.png'),
       ('650e8400-e29b-41d4-a716-446655440003', 'TMA Solutions', 'Công ty phần mềm quốc tế', 'Hồ Chí Minh, Việt Nam',
        'https://www.tmasolutions.com', 5, 'https://example.com/logo/tma.png'),
       ('650e8400-e29b-41d4-a716-446655440004', 'CMC Corporation', 'Tập đoàn công nghệ thông tin', 'Hà Nội, Việt Nam',
        'https://www.cmc.com.vn', 6, 'https://example.com/logo/cmc.png'),
       ('650e8400-e29b-41d4-a716-446655440005', 'VNG Corporation', 'Công ty công nghệ và giải trí',
        'Hồ Chí Minh, Việt Nam', 'https://www.vng.com.vn', 5, 'https://example.com/logo/vng.png'),
       ('650e8400-e29b-41d4-a716-446655440006', 'Tech Startup XYZ', 'Startup công nghệ trẻ', 'Đà Nẵng, Việt Nam',
        'https://www.techstartup.xyz', 2, 'https://example.com/logo/startup.png');

-- User (Password mặc định: 123456)
INSERT IGNORE INTO user (id, email, password, first_name, last_name, role_id, avatar, phone, gender, education, address,
                         website_link, birth_date, is_find_job, company_id, group_soft_skill)
VALUES
-- Admin
('750e8400-e29b-41d4-a716-446655440001', 'admin@gmail.com',
 '$2a$12$OKXQyj2v95r2eP5JVWa.kOEyS.OdeDhC.mn4qS990Ix6/Wntd.KIu', 'Admin', 'System',
 '550e8400-e29b-41d4-a716-446655440001', NULL, '0123456789', 'Male', 'Đại học', 'Hà Nội', 'https://admin.itjob.com',
 '1990-01-01', 0, NULL, 'Leadership, Communication'),
-- HR của FPT
('750e8400-e29b-41d4-a716-446655440002', 'hr@gmail.com', '$2a$12$OKXQyj2v95r2eP5JVWa.kOEyS.OdeDhC.mn4qS990Ix6/Wntd.KIu',
 'Nguyễn', 'Văn A', '550e8400-e29b-41d4-a716-446655440002', NULL, '0987654321', 'Male', 'Đại học', 'Hà Nội', NULL,
 '1985-05-15', 0, '650e8400-e29b-41d4-a716-446655440001', 'Recruitment, Communication'),
-- HR của Viettel
('750e8400-e29b-41d4-a716-446655440003', 'hr2@gmail.com',
 '$2a$12$OKXQyj2v95r2eP5JVWa.kOEyS.OdeDhC.mn4qS990Ix6/Wntd.KIu', 'Trần', 'Thị B',
 '550e8400-e29b-41d4-a716-446655440002', NULL, '0912345678', 'Female', 'Đại học', 'Hà Nội', NULL, '1988-08-20', 0,
 '650e8400-e29b-41d4-a716-446655440002', 'HR Management');

-- Job
INSERT IGNORE INTO job (id, job_position, company_id, detail_address, description_job, requirement, benefits, province_id,
                        industry_id, job_level_id, degree_level_id, employment_type_id, experience_id)
VALUES (1, 'Java Developer', '650e8400-e29b-41d4-a716-446655440001', 'Tòa nhà FPT, Hà Nội',
        'Tìm kiếm Java Developer có kinh nghiệm phát triển ứng dụng web và mobile',
        'Kinh nghiệm 2-3 năm với Java, Spring Boot, MySQL', 'Lương cạnh tranh, bảo hiểm đầy đủ, đào tạo', 1, 2, 4, 4, 1, 4),
       (2, 'React Frontend Developer', '650e8400-e29b-41d4-a716-446655440002', 'Tòa nhà Viettel, Hà Nội',
        'Tuyển Frontend Developer chuyên về React và TypeScript', 'Thành thạo React, Redux, TypeScript, có portfolio',
        'Lương 15-25 triệu, làm việc linh hoạt', 1, 2, 3, 4, 1, 3),
       (3, 'Full-stack Developer', '650e8400-e29b-41d4-a716-446655440003', 'Quận 1, Hồ Chí Minh',
        'Tuyển Full-stack Developer cho dự án startup', 'Node.js, React, MongoDB, có kinh nghiệm startup',
        'Equity, remote work, flexible hours', 2, 2, 4, 4, 6, 4),
       (4, 'DevOps Engineer', '650e8400-e29b-41d4-a716-446655440004', 'Cầu Giấy, Hà Nội',
        'Tuyển DevOps Engineer quản lý infrastructure', 'Docker, Kubernetes, AWS, CI/CD',
        'Lương cao, môi trường chuyên nghiệp', 1, 1, 5, 4, 1, 5),
       (5, 'Data Scientist', '650e8400-e29b-41d4-a716-446655440005', 'Quận 7, Hồ Chí Minh',
        'Tuyển Data Scientist cho dự án AI', 'Python, Machine Learning, TensorFlow, PhD preferred',
        'Lương rất cao, research opportunities', 2, 8, 5, 5, 1, 6),
       (6, 'Mobile Developer (Flutter)', '650e8400-e29b-41d4-a716-446655440006', 'Đà Nẵng',
        'Tuyển Mobile Developer phát triển app Flutter', 'Flutter, Dart, có app trên store',
        'Startup environment, equity', 3, 11, 3, 4, 1, 3);

-- ============================================
-- USER PROFILE DATA
-- ============================================

-- Language Skill
INSERT IGNORE INTO language_skill (id, language_id, level_language_id, user_id)
VALUES (1, 1, 6, '750e8400-e29b-41d4-a716-446655440001'), -- Admin: Tiếng Việt - Bản ngữ
       (2, 2, 5, '750e8400-e29b-41d4-a716-446655440001'), -- Admin: Tiếng Anh - Thành thạo
       (3, 1, 6, '750e8400-e29b-41d4-a716-446655440002'), -- HR1: Tiếng Việt - Bản ngữ
       (4, 2, 4, '750e8400-e29b-41d4-a716-446655440002'), -- HR1: Tiếng Anh - Tốt
       (5, 1, 6, '750e8400-e29b-41d4-a716-446655440003'), -- HR2: Tiếng Việt - Bản ngữ
       (6, 2, 3, '750e8400-e29b-41d4-a716-446655440003'); -- HR2: Tiếng Anh - Khá

-- ============================================
-- CONTENT DATA
-- ============================================

-- Blog
INSERT IGNORE INTO blog (id, title, picture, short_description, description, is_high_light, updated_person)
VALUES (1, '10 Kỹ năng cần thiết cho Developer năm 2024', '', 'Tổng hợp các kỹ năng quan trọng nhất',
        'Nội dung chi tiết về các kỹ năng...', 0, '750e8400-e29b-41d4-a716-446655440001'),
       (2, 'Hướng dẫn viết CV IT hiệu quả', '', 'Cách viết CV thu hút nhà tuyển dụng', 'Chi tiết cách viết CV...', 0,
        '750e8400-e29b-41d4-a716-446655440001'),
       (3, 'Xu hướng công nghệ 2024', '', 'Các xu hướng công nghệ nổi bật', 'Phân tích các xu hướng...', 0,
        '750e8400-e29b-41d4-a716-446655440001');

-- Review
INSERT IGNORE INTO review (id, title, description, company_id, rated, user_id)
VALUES (1, 'Môi trường làm việc tốt', 'Công ty có môi trường làm việc chuyên nghiệp, đồng nghiệp thân thiện',
        '650e8400-e29b-41d4-a716-446655440003', 5, '750e8400-e29b-41d4-a716-446655440002'),
       (2, 'Lương và phúc lợi tốt', 'Lương cạnh tranh, có nhiều phúc lợi cho nhân viên',
        '650e8400-e29b-41d4-a716-446655440004', 4, '750e8400-e29b-41d4-a716-446655440003');

-- Report
INSERT IGNORE INTO report (id, title, description, image_url, status_id, created_report, reported_user, reported_job)
VALUES (1, 'Báo cáo vi phạm nội dung', 'Job posting có nội dung không phù hợp', 'https://example.com/report/1.jpg', 1,
        '750e8400-e29b-41d4-a716-446655440001', NULL, 1),
       (2, 'Báo cáo spam', 'Job posting spam', NULL, 2, '750e8400-e29b-41d4-a716-446655440001', NULL, 2);

-- ============================================
-- PAYMENT DATA
-- ============================================

-- Service Product
INSERT IGNORE INTO service_product (id, name, description, price, images, user_id, job_id)
VALUES (1, 'Premium Job Posting', 'Đăng tin tuyển dụng nổi bật trong 30 ngày', 500000,
        'https://example.com/service/premium.jpg', '750e8400-e29b-41d4-a716-446655440002', 1),
       (2, 'Featured Job', 'Tin tuyển dụng được ưu tiên hiển thị', 300000, 'https://example.com/service/featured.jpg',
        '750e8400-e29b-41d4-a716-446655440002', 2),
       (3, 'Company Profile Premium', 'Nâng cấp hồ sơ công ty', 1000000, 'https://example.com/service/company.jpg',
        '750e8400-e29b-41d4-a716-446655440003', NULL);

-- Payment
INSERT IGNORE INTO payment (id, title, description, payment_method_id, status, service_product_id, user_id)
VALUES (1, 'Thanh toán Premium Job Posting', 'Thanh toán cho dịch vụ đăng tin nổi bật', 1, 3, 1,
        '750e8400-e29b-41d4-a716-446655440002'),
       (2, 'Thanh toán Featured Job', 'Thanh toán cho dịch vụ tin ưu tiên', 3, 3, 2,
        '750e8400-e29b-41d4-a716-446655440002'),
       (3, 'Thanh toán Company Profile', 'Thanh toán nâng cấp hồ sơ công ty', 1, 1, 3,
        '750e8400-e29b-41d4-a716-446655440003');
