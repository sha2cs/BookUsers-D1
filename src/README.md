 DOCUMENTACIÓN DEL PROYECTO – BOOKUSERS

 1. Integrantes del grupo

- Manuel José Londoño Vergara
- Mateo Mahecha Gutiérrez

Programa académico: Ingeniería Informática – UNIPAZ.

 2. Lenguaje de programación

Java: Es el lenguaje que utilizamos para crear el programa. Con él desarrollamos las funciones que permiten registrar usuarios, guardar sus datos, buscarlos, editarlos y eliminarlos.

3. Entorno de desarrollo (IDE)

IntelliJ IDEA: Es el programa que utilizamos para escribir, organizar y ejecutar el código de nuestro proyecto. También nos permite encontrar y corregir errores durante el desarrollo.

4. Interfaz gráfica

Java Swing: Es la herramienta que utilizamos para crear la parte visual del programa. Gracias a ella podemos trabajar con ventanas, botones, formularios, listas y mensajes que permiten utilizar BookUsers de una manera sencilla.

Además, utilizamos imágenes para decorar las diferentes ventanas y darle al programa una apariencia más agradable y organizada.

 5. Kit de desarrollo

JDK 27: Es la versión de las herramientas de Java que utilizamos para desarrollar y ejecutar nuestro proyecto.

6. Estructura del proyecto

El proyecto está organizado en seis clases principales. Cada una tiene una función diferente para que el programa pueda trabajar de manera ordenada.

6.1. Usuarios.java

Es la clase que contiene la información de cada persona registrada. Allí se guardan datos como el número de documento, nombres, apellidos, teléfono, correo, contraseña y fotografía.

También se encarga de generar automáticamente el nombre de usuario y permite consultar o actualizar los datos de cada persona.

6.2. GestorUsuarios.java

Es la clase encargada de guardar y administrar los usuarios del programa. Permite registrar nuevos usuarios, consultar los que ya existen, buscar uno en particular, actualizar sus datos y eliminarlos.

También se encarga de conservar la información para que los usuarios registrados no se pierdan al cerrar el programa.

6.3. VentanaPrincipal.java

Es la primera ventana que aparece al iniciar BookUsers. Desde allí podemos ingresar a las opciones principales del programa: registrar usuarios, ver los usuarios guardados y consultar los créditos de los integrantes que realizaron el proyecto.

6.4. VentanaCrearUsuario.java

Es la ventana donde se registran los nuevos usuarios. Contiene los espacios para escribir sus datos y un botón para seleccionar una fotografía.

Antes de guardar, el programa revisa que los campos necesarios estén completos y que el correo tenga el símbolo @. Esta misma ventana también permite modificar los datos de un usuario que ya esté registrado.

6.5. VentanaVerUsuarios.java

Es la ventana donde podemos ver los usuarios que se han registrado. Desde allí podemos seleccionar una persona, modificar sus datos, eliminar su registro o generar un archivo PDF con su información y fotografía.

6.6. PanelConFondo.java

Es la clase que permite colocar imágenes de fondo en las ventanas del programa. Gracias a ella podemos utilizar diferentes imágenes para decorar las interfaces y darle a BookUsers una presentación visual más agradable.

7. Generación de documentos PDF

OpenPDF: Es la herramienta que utilizamos para crear documentos PDF desde el programa. Nos permite generar una ficha individual con los datos y la fotografía del usuario seleccionado, para que esa información se pueda guardar como un documento y consultar posteriormente.