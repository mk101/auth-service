CREATE TABLE "user" (
    id UUID NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name  VARCHAR(30) NOT NULL,
    login      VARCHAR(30) NOT NULL,
    password   VARCHAR(150) NOT NULL
);

CREATE TABLE user_role (
    user_id UUID NOT NULL,
    role    VARCHAR(10) NOT NULL
);
