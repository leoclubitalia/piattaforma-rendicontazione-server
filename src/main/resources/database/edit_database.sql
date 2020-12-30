USE rendicontation;

-- per v0.2.0
ALTER TABLE service ADD column deleted BOOL DEFAULT FALSE NOT NULL;
ALTER TABLE activity ADD column deleted BOOL DEFAULT FALSE NOT NULL;

UPDATE service SET deleted = FALSE WHERE id != 0;
UPDATE activity SET deleted = FALSE WHERE id != 0;

ALTER TABLE service DROP INDEX title;
ALTER TABLE activity DROP INDEX title;