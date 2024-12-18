CREATE TABLE IF NOT EXISTS apg_req_count (
	response_time timestamp NOT NULL,
	van_id varchar NOT NULL,
	van_name varchar NULL,
	bank_id varchar NOT NULL,
	bank_name varchar NULL,
	success_count numeric NULL DEFAULT 0,
	error_count numeric NULL DEFAULT 0,
	timeout_count numeric NULL DEFAULT 0,
	otp_count numeric NULL,
	routing_type varchar NULL,
	periods varchar NULL
);

CREATE TABLE IF NOT EXISTS apg_req_count_date (
	gather_date varchar NULL,
	van_id varchar NULL,
	van_name varchar NULL,
	bank_id varchar NULL,
	bank_name varchar NULL,
	success_count numeric NOT NULL DEFAULT 0,
	error_count numeric NOT NULL DEFAULT 0,
	timeout_count numeric NOT NULL DEFAULT 0,
	otp_count numeric NOT NULL DEFAULT 0,
	routing_type varchar NULL,
	periods varchar NULL
);

CREATE TABLE IF NOT EXISTS apg_req_count_his (
	response_time timestamp NOT NULL,
	van_id varchar NOT NULL,
	van_name varchar NULL,
	bank_id varchar NOT NULL,
	bank_name varchar NULL,
	success_count numeric NULL DEFAULT 0,
	error_count numeric NULL DEFAULT 0,
	timeout_count numeric NULL DEFAULT 0,
	otp_count numeric NULL,
	routing_type varchar NULL,
	periods varchar NULL
);

CREATE TABLE IF NOT EXISTS apg_req_count_hour (
	gather_date timestamp NULL,
	van_id varchar NULL,
	van_name varchar NULL,
	bank_id varchar NULL,
	bank_name varchar NULL,
	success_count numeric NOT NULL DEFAULT 0,
	error_count numeric NOT NULL DEFAULT 0,
	timeout_count numeric NOT NULL DEFAULT 0,
	otp_count numeric NOT NULL DEFAULT 0,
	routing_type varchar NULL,
	periods varchar NULL
);

CREATE TABLE IF NOT EXISTS apg_rsp_time (
	response_time timestamp NOT NULL,
	van_id varchar NOT NULL,
	van_name varchar NULL,
	avg_wait_3d_time varchar NULL DEFAULT '0'::character varying,
	avg_apg_proc_time varchar NULL DEFAULT '0'::character varying,
	avg_van_proc_time varchar NULL DEFAULT '0'::character varying,
	bank_id varchar NULL,
	bank_name varchar NULL,
	routing_type varchar NULL,
	periods varchar NULL
);

CREATE TABLE IF NOT EXISTS apg_rsp_time_date (
	gather_date varchar NOT NULL,
	van_id varchar NOT NULL,
	van_name varchar NULL,
	avg_wait_3d_time varchar NULL DEFAULT '0'::character varying,
	avg_apg_proc_time varchar NULL DEFAULT '0'::character varying,
	avg_van_proc_time varchar NULL DEFAULT '0'::character varying,
	bank_id varchar NULL,
	bank_name varchar NULL,
	routing_type varchar NULL,
	periods varchar NULL
);

CREATE TABLE IF NOT EXISTS apg_rsp_time_his (
	response_time timestamp NOT NULL,
	van_id varchar NOT NULL,
	van_name varchar NULL,
	avg_wait_3d_time varchar NULL DEFAULT '0'::character varying,
	avg_apg_proc_time varchar NULL DEFAULT '0'::character varying,
	avg_van_proc_time varchar NULL DEFAULT '0'::character varying,
	bank_id varchar NULL,
	bank_name varchar NULL,
	routing_type varchar NULL,
	periods varchar NULL
);

CREATE TABLE IF NOT EXISTS bank (
	bank_id varchar NOT NULL,
	bank_name varchar NULL,
	CONSTRAINT bank_pkey PRIMARY KEY (bank_id)
);

CREATE TABLE IF NOT EXISTS payment_log (
	hubrrn varchar NULL,
	order_id varchar NULL,
	customer_id varchar NULL,
	company_name varchar NULL,
	bank_id varchar NULL,
	bank_name varchar NULL,
	van_id varchar NULL,
	van_name varchar NULL,
	service_id varchar NULL,
	periods varchar NULL,
	success_flag varchar NULL,
	routing_type varchar NULL,
	trans_amnt varchar NULL,
	response_code varchar NULL,
	response_msg varchar NULL,
	tx_bgn_time varchar NULL,
	tx_send_to_van_time varchar NULL,
	tx_van_response_time varchar NULL,
	tx_response_time varchar NULL,
	apg_ip varchar NULL
);

CREATE TABLE IF NOT EXISTS schedule_log (
	close_date timestamp NOT NULL,
	prg_id varchar NOT NULL,
	schedule_id varchar NOT NULL,
	ok_count numeric NULL DEFAULT 0,
	error_count numeric NULL DEFAULT 0,
	other_count numeric NULL DEFAULT 0,
	proc_time numeric NULL DEFAULT 0,
	remark1 varchar NULL,
	remark2 varchar NULL
);

CREATE TABLE IF NOT EXISTS tsettle (
	gather_date varchar NOT NULL,
	settle_type varchar NOT NULL,
	settle_gb varchar NOT NULL,
	settle_code varchar NOT NULL,
	cnt numeric NULL,
	amt numeric NULL,
	CONSTRAINT pk_tsettle PRIMARY KEY (gather_date, settle_type, settle_gb, settle_code)
);

CREATE TABLE IF NOT EXISTS van (
	van_id varchar NOT NULL,
	van_name varchar NULL,
	CONSTRAINT van_pkey PRIMARY KEY (van_id)
);



