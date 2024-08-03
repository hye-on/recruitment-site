CREATE TABLE user (
  id          BINARY(16)      NOT NULL COMMENT '유저 id',
  name        VARCHAR(100)    NOT NULL COMMENT '유저 이름',
  email       VARCHAR(320)    NOT NULL COMMENT '이메일',
  created_at  DATETIME        NOT NULL COMMENT '생성일',
  updated_at  DATETIME        NOT NULL COMMENT '수정일',
  PRIMARY KEY (id)
);

CREATE TABLE company (
    id            BINARY(16)      NOT NULL COMMENT '회사 id',
    name          VARCHAR(100)    NOT NULL COMMENT '회사명',
    country_name  VARCHAR(50)     NOT NULL COMMENT '국가명',
    location_name VARCHAR(100)    NOT NULL COMMENT '지역명',
    created_at    DATETIME        NOT NULL COMMENT '생성일',
    updated_at    DATETIME        NOT NULL COMMENT '수정일',
    PRIMARY KEY (id)
);

CREATE TABLE job (
    id          BINARY(16)      NOT NULL COMMENT '채용공고 id',
    company_id  BINARY(16)      NOT NULL COMMENT '회사 id',
    position    VARCHAR(100)    NOT NULL COMMENT '채용 포지션',
    description VARCHAR(255)    NOT NULL COMMENT '채용 내용',
    created_at  DATETIME        NOT NULL COMMENT '생성일',
    updated_at  DATETIME        NOT NULL COMMENT '수정일',
    PRIMARY KEY (id),
    CONSTRAINT fk_job_company FOREIGN KEY (company_id) REFERENCES company(id)
);

CREATE TABLE skill (
   id          BINARY(16)      NOT NULL COMMENT '기술 id',
   name        VARCHAR(100)    NOT NULL COMMENT '기술 이름',
   created_at  DATETIME        NOT NULL COMMENT '생성일',
   updated_at  DATETIME        NOT NULL COMMENT '수정일',
   PRIMARY KEY (id)
);

CREATE TABLE job_skill (
    id          BINARY(16)      NOT NULL COMMENT '잡 스킬 아이디',
    job_id      BINARY(16)      NOT NULL COMMENT '채용공고 id',
    skill_id    BINARY(16)      NOT NULL COMMENT '기술 id',
    created_at  DATETIME        NOT NULL COMMENT '생성일',
    updated_at  DATETIME        NOT NULL COMMENT '수정일',
    PRIMARY KEY (id),
    CONSTRAINT fk_jobskill_job FOREIGN KEY (job_id) REFERENCES job(id),
    CONSTRAINT fk_jobskill_skill FOREIGN KEY (skill_id) REFERENCES skill(id)
);

CREATE TABLE job_application_history (
    id          BINARY(16)      NOT NULL COMMENT '지원 내역 id',
    job_id      BINARY(16)      NOT NULL COMMENT '채용공고 id',
    user_id     BINARY(16)      NOT NULL COMMENT '유저 id',
    created_at  DATETIME        NOT NULL COMMENT '생성일',
    updated_at  DATETIME        NOT NULL COMMENT '수정일',
    PRIMARY KEY (id),
    CONSTRAINT fk_jobapphist_job FOREIGN KEY (job_id) REFERENCES job(id),
    CONSTRAINT fk_jobapphist_user FOREIGN KEY (user_id) REFERENCES user(id)
);