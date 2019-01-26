# RestAPI backend realization with parsing IMbd databases.


Requirements:
- Create MySql database
mysql -u root -p -e "create database imdbDB "

- Build package with mvn
mvn clean package

- The application needs to be provided with tsv files downloaded from IMbd site :
title.basics.tsv
name.basics.tsv
title.principals.tsv
In the same folder, where the application starts.

- Run the application with at least 4GB heap size
java -Xmx4g -jar imdbq-0.0.1-SNAPSHOT.jar

Starting takes about 15 minutes to import all data and build indicies.


API description:
- Suggest API
GET http://localhost:8080/query/suggest?name=Will+Smith

[{"nconst":"nm0000226","primaryName":"Will Smith","birthYear":"1968","deathYear":null},
{"nconst":"nm0810337","primaryName":"Will Smith","birthYear":"1971","deathYear":null},
{"nconst":"nm10029623","primaryName":"Will Smith","birthYear":null,"deathYear":null}
....

Returns an actors list with this name.

- Shared titles
GET http://localhost:8080/query/sharedtitle?nconst1=nm0000102&nconst2=nm0000093
{
  "titleBasicRecs":[
  {
    "tconst": "tt0117665",
    "primaryTitle": "Sleepers",
    "genres": "Crime,Drama,Thriller"
  }
  ]
}

Given names of 2 acrors, returns the list of titles their shared.

- Is the action typecasted?
GET http://localhost:8080/query/typecast?nconst=nm0000091

{
"typeCasted": true
}

Returns true if the actor is typecasted (e.g. at least half of his work in one genre)

- Get Kevin Bacon's degree.
GET http://localhost:8080/query/kevinbacondegree?nconst=nm0000091

{
"degree": 4
}

Get degree of the actor to Kevin Bacon. Assuming that Kevin has degree 0, and actors who played with him in the same titles have degree 1, etc




