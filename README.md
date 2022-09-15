# SpringBoot Simple Webflux

### Task
- Fatch API
- Save Data
- Rate Limit


### End Point
| Method | URL | Title | Body JSON |
| ------ | ------ | ------ | ------ |
| POST | http://localhost:8080/users/signup | Signup | {"email": "ajii@gmail.com", "username": "ajii", "password": "halo"} |
| POST | http://localhost:8080/users/login | Login | { "username": "aji", "password": "halo" } |
| GET | http://localhost:8080/api/country/{search} | none | none |

1. Run Mysql ```docker-compose up -d```

2. Install dependency ```./mvnw install ```

3. Run Project in IDE

4. Curl ```Signup```
    ```sh
    curl  --request POST \
          --url http://localhost:8080/users/signup \
          --header 'Content-Type: application/json' \
          --data '{
                  "email": "aji@gmail.com",
                  "username": "ajii", 
                  "password": "halo"
          }'

    {"status":"success"}
    ```

    Curl ```Login```
    ```sh
    curl  --request POST \
          --url http://localhost:8080/users/login \
          --header 'Content-Type: application/json' \
          --data '{
                  "username": "ajii",
                  "password": "halo"
          }'

    {"token":"eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhamlpIiwicm9sZXMiOiJBRE1JTixVU0VSIiwiZXhwIjoxNjU4MjE1NDg1LCJ1c2VySWQiOjEsImlhdCI6MTY1ODEyOTA4NSwidXNlcm5hbWUiOiJhamlpIn0.tqrQmEHo4Ske6E9_hIkKzIoZox4r7aaKsyxOFzEgCX7efoelwWdYNNx2WsA93SL4XU5Vomf2N07I_HcbXZubXFjG_suVJ11ImTEHWQSEJ5M0G48noTfolqmzoqxS1eyuWIWej6EH4IS5MdBA-yelKuErqXluB06mPdlRQzOfTibSCNfxxybYFibV9mFdPyRw7hVOfdra7PehXxSdRqZIfad2Q3hZpuT6GJqHa-lXwaewGPRgFFsZq11omrZhfJ6EKswPlJKnKcAWUyvuPTJYsRa_IFVKBOIzRSYiQKHYSoriUsw8vhPPPVKe8YyixE7hQFwLDMfbLCGoctvOHNGuaw"}
    ```

    Curl ```Country```
    ```sh
    curl  --request GET \
          --url http://localhost:8080/api/country/SG \
          --header 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhamlpIiwicm9sZXMiOiJVU0VSLEFETUlOIiwiZXhwIjoxNjU4MjE2NDY4LCJ1c2VySWQiOjEsImlhdCI6MTY1ODEzMDA2OCwidXNlcm5hbWUiOiJhamlpIn0.vKzL37mAh7hReo2qrT8JnFSgqOjJpJ8Bjg7YzbVGSAOtimob7-dyREmpPXi2a-ifc3X86O84qFQ0YAvaiAYjLhfXi-9rBu-izljc4-0UO0GXnnbAeVeYkjp7EFBjpN7iDXTCX344higYlYx-og9I3VaqTaJ2Gj3_h7q3VIklWXOsRse3x_tejbmiIA7339LVyv3oRwAZ4_NhvgqDcMl9s3B2OHYk2QwzzRhKwC5B67s2HVjTVkYsbfIeqzQfbB_gdPensG9LEg7dg09JQGBMCW0jklj2QG9Uk4Lqq8v_e0VYitjKZaopGlwJPiV0I_qtcJAGzQU-spzbGrwQb3Yp4g'

    {"fullName":"Republic of Singapore","population":5685807,"currency":"SGD - Singapore dollar","rateToIDR":10718.384119}
    ```

5. Testing ```./mvnw test```
    ```sh
    [INFO] 
    [INFO] Results:
    [INFO] 
    [INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
    [INFO] 
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  10.749 s
    [INFO] Finished at: 2022-07-18T07:49:40+07:00
    [INFO] ------------------------------------------------------------------------
    ```

6. Database log_currency
    ```sh
    Database changed
    mysql> SELECT * FROM db.log_currency;
      +----+----------------------------+----------------------------+--------------------------+------------+-------------------------+--------------+--------+
      | id | created                    | currency                   | full_name                | population | priod                   | rate_idr     | search |
      +----+----------------------------+----------------------------+--------------------------+------------+-------------------------+--------------+--------+
      | 24 | 2022-07-18 15:04:53.506832 | SGD - Singapore dollar     | Republic of Singapore    |    5685807 | 2022-07-17 ~ 2022-07-18 | 10718.384119 | SG     |
      | 25 | 2022-07-18 15:05:01.563660 | SGD - Singapore dollar     | Republic of Singapore    |    5685807 | 2022-07-17 ~ 2022-07-18 | 10718.384119 | SG     |
      | 26 | 2022-07-18 15:21:05.332144 | USD - United States dollar | United States of America |  329484123 | 2022-07-17 ~ 2022-07-18 |     14963.75 | USA    |
      +----+----------------------------+----------------------------+--------------------------+------------+-------------------------+--------------+--------+
    3 rows in set (0.04 sec)

    mysql>
    ```

### Contact
https://www.linkedin.com/in/aji-indra-jaya

License
----

MIT
