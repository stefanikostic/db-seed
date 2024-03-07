INSERT INTO "shop"("name")
VALUES('Setec')
ON CONFLICT("name") DO NOTHING;

INSERT INTO "shop"("name")
VALUES('Neptun')
ON CONFLICT("name") DO NOTHING;