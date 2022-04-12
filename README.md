# Weather
Each weather data is a JSON object describing hourly temperatures recorded at a given location on a given date. Each such object has the following properties:

id: the unique integer ID of the object
date: the date, in YYYY-MM-DD format, denoting the date of the record
lat: the latitude (up to 4 decimal places) of the location of the record
lon: the longitude (up to 4 decimal places) of the location of the record
city: the name of the city of the record
state: the name of the state of the record
temperatures: an array of 24 float values, each up to one decimal place, denoting the hourly temperatures of the record in Celsius
 
Example POST request to http://localhost:8000/weather

Request body:
```json
{
   "date": "2019-06-11",
   "lat": 41.8818,
   "lon": -87.6231,
   "city": "Chicago",
   "state": "Illinois",
   "temperatures": [24.0, 21.5, 24.0, 19.5, 25.5, 25.5, 24.0, 25.0, 23.0, 22.0, 18.0, 18.0, 23.5, 23.0, 23.0, 25.5, 21.0, 20.5, 20.0, 18.5, 20.5, 21.0, 25.0, 20.5]
}
```

GET request to /weather:
the response body is an array of matching records, ordered by their ids in increasing order
accepts an optional query string parameter, date, in the format YYYY-MM-DD, for example /weather/?date=2019-06-11. When this parameter is present, only the records with the matching date are returned.
accepts an optional query string parameter, city, for example /weather/?city=London. When this parameter is present, only the records with the matching city are returned. The value of this parameter is case insensitive, so "London" and "london" are equivalent. Moreover, it might contain several values, separated by commas (e.g., city=london,Milan), meaning that records with a city matching any of these values must be returned.
accepts an optional query string parameter, sort, that can take one of two values: either "date" or "-date", for example /weather/?sort=date. If the value is "date", then the ordering is by date in ascending order. If it is "-date", then the ordering is by date in descending order. If there are two records with the same date, the one with the smaller id must come first.
