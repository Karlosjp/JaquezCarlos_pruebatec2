# JaquezCarlos_pruebatec2
<em> # Prueba Técnica Nº 2 - Java Avanzado - Consigna </em>

Proyecto realizado para la prueba tecnica de java avanzado. Consiste en desarrollar un turnero donde se puedan almacenar en base de datos turnos para diferentes tramites y ciudadanos. 
Para el proyecto es necesario interactuar con una BBDD, JSP, JPA y manejar coleciones y operaciones CRUD.

# Funcionalidades del proyecto
- `Registrar ciudadanos`: Registrar ciudadanos: Para poder realzar las operaciones es necesario registrar ciudadanos en la BBDD.
- `Agregar un nuevo turno`: Si hay ciudadanos registrados se podran crear nuevos turnos para tramitar en un futuro. Estos se crean en estado "EN_ESPERA".
- `Listar turnos`: Los turnos se muestran para la fecha seleccionada, estos muestran colores en funcion de su estado "EN_ESPERA" (Naranja), "EN_PROCESO" (Verde), "YA_ATENDIDO" (Negro). 
- `Filtrado de turnos`: Si se desea se puede agregar el filtro de estado.
- `Gestionar turno`: Puedes seleccionar un turno especifico para ver su informacion y atender al ciudadano que lo ha solicitado. Si entras a gestionar su estado cambia a "EN_PROCESO".

# Instalacion
El proyecto utiliza una BBDD mysql llamada `turnero`. Usuario root, contrasña "".
Creamos la conexion con la BBDD y actualizamos las dependencias de maven (pom.xml)
En la carpeta **Documentos y extras** esta el conector para la BBDD 8.1 y el sql con la base de datos

# Uso
El proyecto se abrira en index.jpg (Home). Lo primero seria registrar a los ciudadanos (con todos sus datos: dni, nombre, apellidos, direccion...) que van a utilizar el servicio de turnero.

## Crear ciudadano
Una vez creado el ciudadano se podra proceder a asignarle los turnos que solicite (Si no hay ciudadanos en la BBDD no se podran asignar turnos).

## Asignar nuevo turno
Para asignar un turno seleccionamos al ciudadano que, previamente registrado, y escribimos una breve descripcion de lo que se realizara en el tramite, a continuacion seleccionamos una fecha igual o posterior al dia que se realiza la peticion del turno y pulsamos registrar.

## Revisar turnos
Aqui podremos ver todos los turnos que se han solicitado hasta el momento en una fecha concreta, por defecto la del sistema.
### Filtrar solo fecha
* Indicar en el selector de fecha.
* Luego pulsar el boton "Buscar"
### Filtrar fecha y estado
* Indicar la fecha que de los turnos que se quieren ver y seleccionar el estado que se desea buscar. 
* Luego pulsar el boton "Buscar"

### Gestionar turno
Todos los turnos tienen un boton "Gestionar". Pulsar sobre la tarjeta del turno que se este buscando y pasara a la siguiente pagina.

## Completar turno.
Una vez seleccioado un turno para gestionar se abrira una nueva pagina con toda la informacion de este:
* Datos del ciudadano que l ha solicitado
* Numero del turno
* Fecha para la que se ha solicitado
* Estado del turno
* Descripcion del tramite
Una vez terminado el tramite para el que se solicito pulsar sobre "Completar" y el turno pasara a estado "YA_ATENDIDO". Esto nos devolvera al Home de la web
