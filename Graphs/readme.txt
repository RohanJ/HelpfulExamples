Graph traversal and shortest path algorithm

You are living in a country with N cities. Each city is identified by an unique integer ID in the [1..N] range. The cities are connected by M bidirectional roads. All the roads have the same length.

Given the ID of your home city and the ID of a destination city:

Write a function that prints to the standard output (stdout) the minimum number of roads you need to travel in order to reach the destination city.
the destination city will always be accessible from your home city.

The inputs are as follows:
N
which is an integer number giving the number of cities
home_city
which is an integer number giving the ID of your home city
dest_city
which is an integer number giving the ID of the destination city
firstCityRoads
which is an array of integers giving the ID of the first city connected by each road
secondCityRoads
which is an array of integers giving the ID of the second city connected by each road
The ith road connects the ith city in the firstCityRoad array and the ith city in the secondCityRoad array.



For example, if
N: 7
home_city: 1
dest_city: 4
firstCityRoads: [1, 2, 3, 2, 5]
secondCityRoads: [3, 3, 4, 4, 6]

Then the output will be: 2
