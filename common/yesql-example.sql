-- https://github.com/krisajenkins/yesql#usage

-- Counts the users in a given country.
SELECT count(*) AS count
FROM user
WHERE country_code = :country_code
