INSERT INTO "shop"("name")
VALUES('Setec')
ON CONFLICT("name") DO NOTHING;