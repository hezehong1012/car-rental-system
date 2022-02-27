# car-rental-system
简单汽车出租服务，初始化
   initCarList = new LinkedList<Car>(){{
            add(new Car((long)1, "Toyota Camry", "Toyota Camry_001"));
            add(new Car((long)2, "Toyota Camry", "Toyota Camry_002"));
            add(new Car((long)3, "BMW 650", "BMW 650_001"));
            add(new Car((long)4, "BMW 650", "BMW 650_002"));
        }};
   包含查阅所有待出租信，已出租信息，出租汽车，归还汽车功能
# HTTP STATUS 
# 200 success | 500 failed

1.Health check
Request http://47.93.252.65:8080/car-manager/health/alive
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
Request http://47.93.252.65:8080/car-manager/rental/allResidueStockCar
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
Request http://47.93.252.65:8080/car-manager/rental/rend
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
Request http://47.93.252.65:8080/car-manager/rental/allUserRentalCar
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
Request http://47.93.252.65:8080/car-manager/rental/return
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