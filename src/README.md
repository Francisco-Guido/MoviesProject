In this project a movie api is consumed.
There is a single exposed endpoint ('/api/directors') where we must send a parameter ('threshold').
It performs a query to the movies api and, based on the parameter that we send, it will bring us all the directors (alphabetically ordered) that have more movies than the parameter ('threshold') that we indicate.

The project was developed with Java 17, we can run it and use the swagger in the port: http://localhost:8080/swagger-ui/index.html

----------------------------------------------------------------------

En este proyecto se consume una api de peliculas.
Hay un único endpoint expuesto ('/api/directors') en donde debemos enviar un parámetro ('threshold')
El mismo realiza una consulta a la api de peliculas y, en base al parámetro que enviemos, nos traera todos los directores (ordenados alfabeticamente) que tienen más peliculas que el parametro ('threshold') que indiquemos.

El proyecto fue desarrollado con Java 17, podemos ejecutarlo y utilizar el swagger en el puerto: http://localhost:8080/swagger-ui/index.html