CREATE UNIQUE INDEX "udx.user.id"
    ON "user" (id);

CREATE INDEX "idx.user.login"
    ON "user" (login);