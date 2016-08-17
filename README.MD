#Descripción
API para la consulta del valor de cambio entre monedas de distintos países
#Instalación
##Prerequesitos
Para poder descargar, instalar y compilar esta aplicación son necesarios los siguientes requerimientos:

* JDK 1.8
* maven 3
* cliente git 


##Instrucciones
1. Descargar el código de la aplicación mediante `git clone https://github.com/marbaez/currency_exchange.git`
2. Acceder a la carpeta `currency_excachange`y compilar el proyecto ejecutando `mvn clean package`
3. Poner en marcha en servidor mediante el comando `java -jar target/currencyexchanger-0.0.1-SNAPSHOT.war`
4. Podemos comprobar que el servidor está arrancado y funcionando intentamos cargar la siguiente URL `http://localhost:8080/exchanges?base=ESP&destination=USA&destination=GBR`

#Uso
Para usar el servcio que nos indica el cambio entre monedas de distintos países tenemos que utilizar la URL `http://localhost:8080/exchanges` con los siguientes parámetros:
* base : indica el código del país de la moneda origen del cambio. El código del país debe ser el código alfabético de tres caracteres
* destination : mínimo una ocurrencia, aunque se pueden indicar varios países de destino. También debe de ser el código alfabéticod e tres caracteres.

##Ejemplo de petición al servicio
URL de la petición: `http://localhost:8080/exchanges?base=ESP&destination=USA&destination=GBR` 

Esta llamada nos devolvería la siguiente respuesta en formato JSON:

```javascript
[  
   {  
      "base_currency":"EUR",
      "destination_currency":"GBP",
      "exchange_rate":0.8706
   },
   {  
      "base_currency":"EUR",
      "destination_currency":"USD",
      "exchange_rate":1.1295
   }
]
```

##Validaciones y control de errores
###URL's no controladas
Como buena práctica hemos controlado el acceso al resto de posibles rutas, por lo que si intentamos acceder a una url no prevista nos toparemos con una respuesta 404 con el siguiente mensaje:

URL : `http://localhost:8080/fakrURL`

```javascript
{  
   "status":404,
   "error":"Not Found",
   "message":"There is no resource for path /fakeURL"
}
```
###Parámetros incorrectos
También se controla que se indiquen los parametros `base` y al menos un parametro `destination`, por lo que si no introducimos alguno de ellos en la URL, nos devolverá un código de respuesta de tipo `BAD_REQUEST` con el siguiente mensaje:

URL : `http://localhost:8080/exchanges?base=ESP`

```javascript
{  
   "status":400,
   "error":"Bad Request",
   "message":"At lest one destination country code must be provided."
}
```

###Formato de los parámetros incorrecto
Si alguno de los parámetros no tiene tres caracteres alfabéticos obtenedremos un mensaje de error que nos indica el problema.

URL : `http://localhost:8080/exchanges?base=ESAP&destination=USA&destination=GBR`

```javascript
{  
   "status":400,
   "error":"Bad Request",
   "message":"[ESAP] is not a valid country alpha three digit code. "
}
```

###Código de país desconocido
Si preguntamos por un código de país de tres caracteres que no exista recibiremos un mensaje de error 500 descriptivo.

URL : `http://localhost:8080/exchanges?base=KKK&destination=USA&destination=GBR`

```javascript
{  
   "status":500,
   "error":"Internal Server Error",
   "message":"Country API could not found info about requested country code -> KKK"
}
```