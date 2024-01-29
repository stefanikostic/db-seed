CREATE TABLE "category" (
    "id" INT GENERATED ALWAYS AS IDENTITY,
    "name" VARCHAR(50) UNIQUE,
    "super_category_id" INT,
    PRIMARY KEY("id")
);