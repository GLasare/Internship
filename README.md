In order to run the application, you have to just run DemoApplication class in demo/src/main/java/com.example.demo.

Example request:
POST http://localhost:8080/logistics/optimize
Content-Type: application/json

{
  "transfers": [
    {"weight": 5, "cost": 10},
    {"weight": 10, "cost": 20},
    {"weight": 3, "cost": 5},
    {"weight": 8, "cost": 15}
  ],
  "maxWeight": 26
}

Response:
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 08 Jan 2025 15:19:13 GMT

{
  "transfers": [
    {
      "weight": 8,
      "cost": 15
    },
    {
      "weight": 3,
      "cost": 5
    },
    {
      "weight": 10,
      "cost": 20
    },
    {
      "weight": 5,
      "cost": 10
    }
  ],
  "totalCost": 50
}
