ALTER TABLE "user"
    ADD CONSTRAINT "pk.user.id" PRIMARY KEY (id);

ALTER TABLE user_role
    ADD CONSTRAINT "pk.user_role.user_id,role" PRIMARY KEY (user_id, role);

ALTER TABLE user_role
    ADD CONSTRAINT "fk.user_role.user_id"
    FOREIGN KEY (user_id) REFERENCES "user"(id);

