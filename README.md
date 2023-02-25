
# Stc file serivce


## Deployment

To deploy this project run
```bash
./mvnw clean package -DskipTests
cp target/fileservice-0.0.1-SNAPSHOT.jar src/main/docker

cd src/main/docker
docker-compose down
```
* Sorry i didn't have time to test docker fully.
* I used user email in Authorization Header as token to check user permission.
* Permission group will be created on startup from data.sql file  with admin as name.
* You can use admin@stc.com as Edit user and viewer@stc.com as VIEW user.
* Postman collection can be found in Postman folder.
