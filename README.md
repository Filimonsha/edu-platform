## How to run in docker
1. copy `environment/.env.db.example` to `environment/.env.db`
2. copy `environment/.env.example` to `environment/.env`
3. build docker image - `gradle bootBuildImage`
4. run spring and postgres containers - `docker-compose up -d`

