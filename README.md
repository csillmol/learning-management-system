# learning-management-system

## Leírás
Ez a Spring Boot alkamazás egy tanulónyilvántartó rendszert valósít meg.

### Meghatározott végpontok
#### SUBJECT
- GET ByID
- GET ByExamType
- GET ByCredit
- POST
- PUT
- DELETE
#### COURSE
- GET ByID
- GET BySubjectName
- GET ByLecturerName
- POST
- PUT
- DELETE
#### STUDENT
- GET ByID
- GET ByName
- GET ByGpa
- POST
- PUT
- DELETE

### Használt technológiák
- Java 17
- Spring Boot 2.7.0.
- Maven 3.8.5
- H2 adatbázis 2.1.212
- Flyway 8.5.11
- Docker 20.10.16

## Program indítása
Kérlek először futtasd a docker_build.bat fájlt.

Kérlek futtasd a docker_run.bat fájlt.

Ezután elérhetővé válik a dokumentáció a következő címen:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Tesztelés
Kérlek futtasd a docker_testrun.bat fájlt a unit- és integrációs tesztek futtatásához.

A Postman teszteléshez kérlek importáld a Postman programba a
./etc/newman mappában elérhető LearningManagementSystem.postman_collection.json fájlt.
A tesztek futásához szükséges a docker_run.bat fájl futása.

