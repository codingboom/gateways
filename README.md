# Money Transfer

How to start the moneytransfer application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/gateways-0.0.1-SNAPSHOT.jar`
1. To check that your application is running enter url `http://localhost:8080`


DeviceController - /device
---

**POST - /create** - creates a new gateway

Sample curl request:
```
curl --location --request POST 'http://localhost:8080/gateway/create' \
--header 'Content-Type: application/json' \
--data-raw '{
	"serial_number" : "12",
	"name" : "asda",
	"ipv4_address" : "123.123.123.123"
}'
```

**POST - /update** - updates an existing gateway

Sample curl request:
```$xslt
curl --location --request POST 'http://localhost:8080/gateway/update' \
--header 'Content-Type: application/json' \
--data-raw '{
	"serial_number" : "12",
	"name" : "asda",
	"ipv4_address" : "123.123.123.123"
}'
```

**POST - /delete/{serialNumber}** - deletes an existing gateway

Sample curl request:
```$xslt
curl --location --request POST 'http://localhost:8080/gateway/delete/1234'
```

**GET - /{serialNumber}** - Fetches gateway information and corresponding devices

Sample curl request:
```
curl --location --request GET 'http://localhost:8080/gateway/1234'
```

**GET - /get-all-info** - Fetches all gateway information and corresponding devices

Sample curl request:
```
curl --location --request GET 'http://localhost:8080/gateway/get-all-info'
```

**POST - /add-device** - Adds device to a gateway

Sample curl request:
```$xslt
curl --location --request POST 'http://localhost:8080/gateway/add-device' \
--header 'Content-Type: application/json' \
--data-raw '{
	"uid" : "3",
	"serial_number" : "12"
}'
```

**POST - /remove-device** - Adds device to a gateway

Sample curl request:
```$xslt
curl --location --request POST 'http://localhost:8080/gateway/remove-device' \
--header 'Content-Type: application/json' \
--data-raw '{
	"uid" : "3",
	"serial_number" : "12"
}'
```

DeviceController - /device
---

**POST - /create** - creates a new gateway

Sample curl request:
```
curl --location --request POST 'http://localhost:8080/device/create' \
--header 'Content-Type: application/json' \
--data-raw '{
	"uid" : "3",
	"vendor" : "asda",
	"date_created" : "11/11/1994",
	"status" : "ONLINE"
}'
```

**POST - /update** - Updates an existing device

Sample curl request:
```$xslt
curl --location --request POST 'http://localhost:8080/device/create' \
--header 'Content-Type: application/json' \
--data-raw '{
	"uid" : "3",
	"vendor" : "asda",
	"date_created" : "11/11/1994",
	"status" : "ONLINE"
}'
```

**POST - /delete/{uid}** - deletes an existing device

Sample curl request:
```$xslt
curl --location --request POST 'http://localhost:8080/device/delete/1234'
```

