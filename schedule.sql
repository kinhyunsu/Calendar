-- 사용자 테이블
CREATE TABLE USER (
                      userId BIGINT PRIMARY KEY AUTO_INCREMENT,
                      email VARCHAR(100) NOT NULL,
                      userName VARCHAR(100) NOT NULL,
                      createAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                      updateAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 일정(SCHEDULE) 테이블
CREATE TABLE SCHEDULE (
                          scheduledId BIGINT PRIMARY KEY AUTO_INCREMENT,
                          userId BIGINT NOT NULL,
                          title VARCHAR(100) NOT NULL,
                          content VARCHAR(255),
                          password VARCHAR(255),
                          createAt DATETIME DEFAULT CURRENT_TIMESTAMP,
                          updateAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          CONSTRAINT fk_userId FOREIGN KEY (userId) REFERENCES USER(userId) ON DELETE CASCADE
);