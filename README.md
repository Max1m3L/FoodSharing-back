
----

#### Проект, который позволит осуществлять продажи уценённой готовой продукции заведениями общественного питания, как правило с истекающим (не истёкшим) сроком годности
#### *Грубо говоря, это Ядекс.Еда, только лучше:)* 

----

- Авторизация вся прописана в [этом](src/main/java/com/maxlvshv/foodsharingback/security) файле, включая так же роли ADMIN, USER, где админ так же: 
    - Создает, блокирует, активирует, удаляет магазины 
    - Управляет пользователями
    - Видит все магазины

- CRUD операции и проч. всё описано [тут](openapi.yaml)
- [Docker](Dockerfile) и [docker-compose](docker-compose.yaml)
- Так же мы этот проект развёртывали на серверах, так что есть опыт DevOps
- У этого репозитория я развернул CI

---

Быстрый запуск: 
1. В Postgres(pgAdmin) создать БД foodsharing, где username=postgres и password=root (иначе не запустится) 
2. Запустить [FoodSharingBackApplication](src/main/java/com/maxlvshv/foodsharingback/FoodSharingBackApplication.java)




  
