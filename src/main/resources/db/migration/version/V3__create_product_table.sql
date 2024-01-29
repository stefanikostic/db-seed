CREATE TABLE "product" (
    "name" VARCHAR(50),
    "link" VARCHAR,
    "image" VARCHAR,
    "category_id" INT NOT NULL,
    "shop_name" VARCHAR(50) NOT NULL,
    "is_available" BOOLEAN,
    "original_price" FLOAT,
    "promotional_price" FLOAT,
    PRIMARY KEY("name", "link"),
    CONSTRAINT "product.fk_category"
            FOREIGN KEY("category_id")
            REFERENCES "category"("id"),
    CONSTRAINT "product.fk_shop"
                FOREIGN KEY("shop_name")
                REFERENCES "shop"("name")
);