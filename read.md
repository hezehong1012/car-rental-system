1.Health check
Request http://localhost:8080/health/alive
Method  GET
Response
       {
           "status": 200,
           "requestId": null,
           "date": null,
           "message": "success",
           "result": "hello world!"
       }

2.GET ALL RESIDUE STOCK CAR
Request http://localhost:8080/rental/allResidueStockCar
Method  GET
Response
    {
        "status": 200,
        "requestId": null,
        "date": null,
        "message": "success",
        "result": [
            {
                "id": 1,
                "branch": "Toyota Camry",
                "name": "Toyota Camry_001"
            },
            {
                "id": 3,
                "branch": "BMW 650",
                "name": "BMW 650_001"
            },
            {
                "id": 4,
                "branch": "BMW 650",
                "name": "BMW 650_002"
            }
        ]
    }
              
3.REND CAR
Request http://localhost:8080/rental/rend
Method  post
Request 
   {
       "userId":"hezehong015",
       "carId": 2
   }
Response
    {
        "status": 200,
        "requestId": null,
        "date": null,
        "message": "success",
        "result": null
    }

3.GET ALL REND CAR LIST 
Request http://localhost:8080/rental/allUserRentalCar
Method  get
Request 
Response
    {
        "status": 200,
        "requestId": null,
        "date": null,
        "message": "success",
        "result": [
            {
                "id": 5,
                "userId": "hezehong015",
                "carId": 2
            }
        ]
    }

4.RETURN CAR 
Request http://localhost:8080/rental/return
Method  get
Request 
Response
    {
        "status": 200,
        "requestId": null,
        "date": null,
        "message": "success",
        "result": null
    }