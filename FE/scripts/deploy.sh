#!/bin/bash

# pull latest image
docker pull ghcr.io/jinan159/airbnb-fe:latest

# stop and remove container of old image
docker stop airbnb-fe
docker rm airbnb-fe

# start container
docker run -p 3000:3000 -d -it --name airbnb-fe airbnb-fe:latest