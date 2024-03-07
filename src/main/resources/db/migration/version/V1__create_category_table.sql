CREATE TABLE "category" (
    "id" INT GENERATED ALWAYS AS IDENTITY,
    "name" VARCHAR(100) UNIQUE,
    "super_category_id" INT NULL,
    PRIMARY KEY("id")
);