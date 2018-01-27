# _stand-up-specials_

#### _Web API, that lets your applications fetch data from the database, 1/26/2018_

#### By _**telpuhova**_

## Description

_Based on simple REST principles, this API returns data in JSON format about standup comics, their specials, and other people's reviews on them._

### Endpoint Reference
| METHOD | ENDPOINT | USAGE | RETURNS |
| :-------------     | :------------- | :------------- | :------------- |
| POST | /comedians/new | create | comedian |
| POST | /comedians/:id/specials/new | create | special |
| POST | /comedians/:cId/specials/:sId/reviews/new | create | review |
| -------------     | ------------- | ------------- | ------------- |
| GET | /comedians | read | list of comedians |
| GET | /comedians/:id/specials | read | list of  specials |
| GET | /comedians/:cId/specials/:sId/reviews | read | list of reviews |
| -------------     | ------------- | ------------- | ------------- |
| GET | /comedians/:id | read | comedian |
| GET | /comedians/:cId/specials/:sId | read | special |
| GET | /comedians/:cId/specials/:sId/reviews/:rId | read | review |
| -------------     | ------------- | ------------- | ------------- |
| POST | /comedians/:id/update | update | comedian |
| POST | /comedians/:cId/specials/:sId/update | update | special |
| POST | /comedians/:cId/specials/:sId/reviews/:rId/update | update | review |
| -------------     | ------------- | ------------- | ------------- |
| GET | /comedians/:id/delete | delete | 0 |
| GET | /comedians/:cId/specials/:sId/delete | delete | 0 |
| GET | /comedians/:cId/specials/:sId/reviews/:rId/delete | delete | 0 |
| GET | /delete_all | delete | 0 |


### Some screenshots from route testing with Postman:
* adding new comedian:

![alt text](https://raw.githubusercontent.com/telpuhova/stand-up-specials/master/src/main/resources/public/comedians_new.jpg)

* get the list of all comedians:

![alt text](https://raw.githubusercontent.com/telpuhova/stand-up-specials/master/src/main/resources/public/comedians.jpg)

* getting info about a specific comedian:

![alt text](https://raw.githubusercontent.com/telpuhova/stand-up-specials/master/src/main/resources/public/comedians_id.jpg)

* updating a comedian:

![alt text](https://raw.githubusercontent.com/telpuhova/stand-up-specials/master/src/main/resources/public/comedians_id_update.jpg)

* deleting a comedian:

![alt text](https://raw.githubusercontent.com/telpuhova/stand-up-specials/master/src/main/resources/public/comedians_id_delete.jpg)


## Setup

* _clone repository_
* _run App.java_

## Technologies Used

_Java, Spark, Postgres, H2_

Copyright (c) 2017 **_telpuhova_**
