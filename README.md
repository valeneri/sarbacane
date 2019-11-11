# sarbacane
Upload a .csv file (with emails and phones) into a mongodb database

#PREREQUISITES
You need apache maven and docker installed on your machine

#build jar
- mvn clean install

#build images
- docker-compose build
- docker-compose up

#endpoint
POST : http://localhost:8080/documents/upload 

