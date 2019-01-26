DROP TABLE IF EXISTS titlebasics;

CREATE TABLE titlebasics (
tconst varchar(255),
titleType varchar(255),
primaryTitle varchar(255),
originalTitle varchar(255),
isAdult  varchar(255),
startYear  varchar(255),
endYear  varchar(255),
runtimeMinutes  varchar(255),
genres varchar(255)
);

DROP TABLE IF EXISTS namebasics;

CREATE TABLE namebasics (
nconst   varchar(255),
primaryName  varchar(255),
birthYear  varchar(255),
deathYear  varchar(255),
primaryProfession  varchar(255),
knownForTitles  varchar(255)
);

DROP TABLE IF EXISTS titleprincipals;

CREATE TABLE titleprincipals (
tconst varchar(255),
ordering varchar(255),
nconst varchar(255),
category varchar(255),
job varchar(255),
characters varchar(255)
);
