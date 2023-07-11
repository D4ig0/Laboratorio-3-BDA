

# Laboratorio 3 BDA
----


Laboratorio 3 de la asignatura de Base de Datos Avanzadas de la Universidad de Santiago de Chile ([USACH][9]).


# Tabla de contenidos
------

- [Laboratorio 3 BDA](#laboratorio-3-bda)
- [Tabla de contenidos](#tabla-de-contenidos)
- [Integrantes](#integrantes)
- [Uso](#uso)
- [Funcionalidades realizadas](#funcionalidades-realizadas)
- [Diagramas de Arquitectura](#diagramas-de-arquitectura)
  - [Vista Física](#vista-física)
  - [MER](#mer)


# Integrantes 
----
* [Samoth Godoy Madueño][8]
* [Matías Figueroa Contreras][6]
* [Cristóbal Marchant Osorio][7]
* [Diego Oliva López][5]
* [Juan Ramírez Montero][4]

# Uso
---


# Funcionalidades realizadas


---
**1.-**   Crear formulario de registro de usuario y autenticación (JWT u otros).

**2.-**  Crear trigger que almacene las queries de inserción, actualización o eliminación realizadas por el backend de su aplicación con la respectiva información de usuario, tiempo de llamada, etc.

**3.-**  Crear procedimiento almacenado que entregue un reporte con los usuarios que más queries de inserción, actualización o eliminación que ejecutan con las respectivas consultas.

**4.-**  Crear pantalla que muestre el listado de emergencias con su estado. Se debe poder activar o desactivar la emergencia.

**5.-**  En la pantalla anterior se debe mostrar el total de tareas activas en la emergencia, este cálculo se debe hacer con una función en la BD.

**6.-**  Se debe guardar la ubicación de la emergencia como punto en la BD.

**7.-**  Se debe guardar la ubicación de cada voluntario en la BD como punto

**8.-**  Generar datos de prueba de al menos 20 voluntarios dentro de Chile con su ubicación correspondiente.

**9.-**  Se debe obtener una lista de todos los voluntarios inscritos en una emergencia dentro del radio R de la emergencia. Se debe agregar el número **R** como parámetro en la llamada REST.

**10.-**  Se debe mostrar en una mapa todas las tareas  para una región seleccionada.


# Diagramas de Arquitectura 
---





[1]: https://nodejs.org/es
[2]: https://www.jetbrains.com/es-es/idea/download/#section=windows
[3]: https://www.postgresql.org/download/
[4]: https://github.com/jnramirezm
[5]: https://github.com/D4ig0
[6]: https://github.com/MatiasFigueroaContreras
[7]: https://github.com/cristowo
[8]: https://github.com/Samoth1
[9]: https://www.usach.cl/