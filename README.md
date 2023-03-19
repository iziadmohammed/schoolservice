
# Edraky School Service

## Deployment

To deploy this project using docker
```bash
./mvnw clean install

docker-compose build 

docker-compose up
```
## Notes

* Sorry i didn't have time to make test cases because i had a critical family emergency situation.
* I was thinking to put grade id and school id in school time table to increase performance and remove unnecessary joins but i didn't .
* this is not the ideal way to handle school system off course .
* you can find swagger rest documentation at http://localhost:8080/swagger-ui/
* migration script can be found under /main/resources/
* there is two profiles one for h2 database(test) and other for mysql database(prod), to switch between those two profile simply change 
``spring.profiles.active`` in ``application.properties`` file to be prod or test
 
*Ziad mohamed*\
*Senior Software Engineer*
