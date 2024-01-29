INSERT INTO "category"("name")
VALUES('Телевизори')
ON CONFLICT("name") DO NOTHING;

INSERT INTO "category"("name")
VALUES('Фрижидери')
ON CONFLICT("name") DO NOTHING;

INSERT INTO "category"("name")
VALUES('Шпорети')
ON CONFLICT("name") DO NOTHING;

INSERT INTO "category"("name")
VALUES('Бела техника')
ON CONFLICT("name") DO NOTHING;
