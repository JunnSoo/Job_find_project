create table if not exists available_skill_experience
(
	id int auto_increment
		primary key,
	id_group_core int null,
	available_skill_id int null,
	experience_id int null,
	user_id varchar(100) null
);

create table if not exists available_skills
(
	id int auto_increment
		primary key,
	name varchar(50) null
);

create table if not exists award
(
	id int auto_increment
		primary key,
	user_id varchar(100) null,
	award_name varchar(255) null,
	organization varchar(255) null,
	date timestamp null,
	description text null
);

create table if not exists blog
(
	id int auto_increment
		primary key,
	title varchar(255) null,
	picture varchar(255) null,
	short_description text null,
	description longtext null,
	created_date timestamp default CURRENT_TIMESTAMP not null,
	updated_date timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	updated_person varchar(255) null
);

create table if not exists category
(
	id int auto_increment
		primary key,
	name varchar(255) null,
	parent_id int null,
	created_date timestamp default CURRENT_TIMESTAMP not null,
	updated_date timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	updated_person varchar(255) null
);

create table if not exists certificate
(
	id int auto_increment
		primary key,
	user_id varchar(100) null,
	certificate_name varchar(255) null,
	organization varchar(255) null,
	date date null,
	link varchar(255) null,
	description text null
);

create table if not exists company_size
(
	id int auto_increment
		primary key,
	min_employees int not null,
	max_employees int not null
);

create table if not exists company
(
	id varchar(255) not null
		primary key,
	name varchar(255) null,
	description text null,
	address text null,
	website text null,
	company_size_id int null,
	logo varchar(255) null,
	created_date timestamp default CURRENT_TIMESTAMP not null,
	updated_date timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	updated_person varchar(255) null,
	constraint fk_company_companysize
		foreign key (company_size_id) references company_size (id)
			on update cascade on delete set null
);

create table if not exists degree_level
(
	id int auto_increment
		primary key,
	name varchar(255) not null
);

create table if not exists employment_type
(
	id int auto_increment
		primary key,
	name varchar(255) not null
);

create table if not exists experience
(
	id int auto_increment
		primary key,
	name varchar(255) not null
);

create table if not exists group_core_skill
(
	id int auto_increment
		primary key,
	name varchar(255) null
);

create table if not exists industry
(
	id int auto_increment
		primary key,
	name varchar(255) not null
);

create table if not exists job
(
	id int auto_increment
		primary key,
	job_position varchar(255) not null,
	company_id char(36) not null,
	detail_address text null,
	description_job text null,
	requirement text null,
	benefits text null,
	province_id int null,
	industry_id int null,
	job_level_id int null,
	degree_level_id int null,
	employment_type_id int null,
	experience_id int null
);

create table if not exists job_level
(
	id int auto_increment
		primary key,
	name varchar(255) not null
);

create table if not exists language
(
	id int auto_increment
		primary key,
	name varchar(50) null
);

create table if not exists language_skill
(
	id int auto_increment
		primary key,
	language_id int null,
	level_language_id int null,
	user_id varchar(100) null
);

create table if not exists level_language
(
	id int auto_increment
		primary key,
	name varchar(50) null
);

create table if not exists payment_method
(
	id int auto_increment
		primary key,
	name text not null,
	created_date timestamp default CURRENT_TIMESTAMP not null
);

create table if not exists payment_status
(
	id int auto_increment
		primary key,
	name varchar(50) not null,
	constraint uk_payment_status_name
		unique (name)
);

create table if not exists project
(
	id int auto_increment
		primary key,
	user_id varchar(50) null,
	name varchar(255) null,
	start_date date null,
	end_date date null,
	project_url varchar(255) null,
	company varchar(255) null
);

create table if not exists province
(
	id int auto_increment
		primary key,
	name varchar(255) not null
);

create table if not exists report
(
	id int auto_increment
		primary key,
	title text not null,
	description text not null,
	hinh_anh text null,
	status_id int null,
	created_report char(36) not null,
	reported_user char(36) null,
	reported_job int null
);

create table if not exists report_status
(
	id int auto_increment
		primary key,
	name varchar(55) not null
);

create table if not exists review
(
	id int auto_increment
		primary key,
	title varchar(255) null,
	description text null,
	company_id varchar(255) null,
	rated int null,
	user_id varchar(255) null
);

create table if not exists role
(
	id char(36) not null
		primary key,
	role_name varchar(80) not null,
	description varchar(255) null,
	created_date timestamp default CURRENT_TIMESTAMP not null,
	updated_date timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	updated_person char(36) null,
	constraint uk_role_name
		unique (role_name)
);

create table if not exists soft_skills_name
(
	id int auto_increment
		primary key,
	name varchar(50) null,
	id_user varchar(255) null
);

create table if not exists user
(
	id varchar(255) not null
		primary key,
	email varchar(255) null,
	password varchar(250) null,
	first_name varchar(255) null,
	last_name varchar(255) null,
	role_id varchar(255) null,
	avatar varchar(255) null,
	phone varchar(30) null,
	gender varchar(30) null,
	education text null,
	address text null,
	linkweb text null,
	birth_date timestamp null,
	is_find_job tinyint(1) null,
	company_id varchar(255) null,
	created_date timestamp default CURRENT_TIMESTAMP not null,
	updated_date timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	updated_person varchar(255) null,
	group_soft_skill varchar(255) null,
	constraint fk_user_company
		foreign key (company_id) references company (id)
			on update cascade on delete set null,
	constraint fk_user_role
		foreign key (role_id) references role (id)
			on update cascade on delete set null
);

create table if not exists access_token
(
	id int auto_increment
		primary key,
	user_id char(36) not null,
	token text not null,
	expiry_date datetime not null,
	is_revoked tinyint(1) default 0 not null,
	create_date datetime default CURRENT_TIMESTAMP not null,
	constraint fk_access_token_user
		foreign key (user_id) references user (id)
			on update cascade on delete cascade
);

create index idx_access_user
	on access_token (user_id);

create table if not exists cv_user
(
	id int auto_increment
		primary key,
	candidate_id char(36) not null,
	version int not null,
	file_url varchar(255) not null,
	title varchar(255) null,
	created_at timestamp default CURRENT_TIMESTAMP not null,
	is_active tinyint(1) default 0 not null,
	constraint uk_cv_user_candidate_version
		unique (candidate_id, version),
	constraint fk_cv_user_candidate
		foreign key (candidate_id) references user (id)
			on update cascade on delete cascade
);

create index idx_cv_user_candidate
	on cv_user (candidate_id);

create table if not exists refresh_token
(
	id int auto_increment
		primary key,
	user_id char(36) not null,
	token text not null,
	expiry_date datetime not null,
	is_revoked tinyint(1) default 0 not null,
	create_date datetime default CURRENT_TIMESTAMP not null,
	constraint fk_refresh_token_user
		foreign key (user_id) references user (id)
			on update cascade on delete cascade
);

create index idx_refresh_user
	on refresh_token (user_id);

create table if not exists service_product
(
	id int auto_increment
		primary key,
	name varchar(255) not null,
	description text null,
	price double default 0 not null,
	images varchar(255) null,
	user_id char(36) null,
	job_id int null,
	created_date timestamp default CURRENT_TIMESTAMP not null,
	updated_date timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	constraint fk_service_user
		foreign key (user_id) references user (id)
			on update cascade on delete set null,
	constraint service_product_job_id_fk
		foreign key (job_id) references job (id)
);

create table if not exists payment
(
	id int auto_increment
		primary key,
	title text null,
	description text null,
	payment_method_id int not null,
	status int not null,
	id_service_product int not null,
	user_id char(36) not null,
	created_date timestamp default CURRENT_TIMESTAMP not null,
	updated_date timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	constraint fk_payment_method
		foreign key (payment_method_id) references payment_method (id)
			on update cascade,
	constraint fk_payment_service
		foreign key (id_service_product) references service_product (id)
			on update cascade,
	constraint fk_payment_status
		foreign key (status) references payment_status (id)
			on update cascade,
	constraint fk_payment_user
		foreign key (user_id) references user (id)
			on update cascade on delete cascade
);

create index idx_payment_method
	on payment (payment_method_id);

create index idx_payment_service
	on payment (id_service_product);

create index idx_payment_status
	on payment (status);

create index idx_payment_user
	on payment (user_id);

create index idx_service_user
	on service_product (user_id);

create table if not exists ward
(
	id int auto_increment
		primary key,
	name varchar(255) not null,
	id_province int null
);

create table if not exists wishlist_candidate
(
	hr_id char(36) not null,
	ungvien_id varchar(255) not null,
	primary key (hr_id, ungvien_id)
);

create table if not exists wishlist_job
(
	job_id int not null,
	user_id varchar(255) not null,
	primary key (job_id, user_id)
);

