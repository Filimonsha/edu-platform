## How to run in docker
1. copy `environment/.db.env.example` to `environment/.db.env`
1. build docker image - `gradle bootBuildImage`
2. run spring and postgres containers - `docker-compose up -d`