CREATE TABLE user_table (
                            userID BIGSERIAL PRIMARY KEY,
                            username VARCHAR(50) UNIQUE NOT NULL,
                            email VARCHAR(100) UNIQUE NOT NULL,
                            password VARCHAR(255)
);

CREATE TABLE profile_table (
                               profileID BIGSERIAL PRIMARY KEY,
                               first_name VARCHAR(50),
                               last_name VARCHAR(100),
                               bio VARCHAR(200),
                               userID BIGINT UNIQUE,
                               CONSTRAINT fk_profile_user
                                   FOREIGN KEY (userID)
                                       REFERENCES user_table(userID)
                                       ON DELETE CASCADE
);

CREATE TABLE post_table (
                            postID BIGSERIAL PRIMARY KEY,
                            title VARCHAR(50),
                            content VARCHAR(100),
                            profileID BIGINT,
                            CONSTRAINT fk_post_profile
                                FOREIGN KEY (profileID)
                                    REFERENCES profile_table(profileID)
                                    ON DELETE CASCADE
);
