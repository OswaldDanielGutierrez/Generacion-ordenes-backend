GENERACIÓN DE ÓRDENES DE COMPRA:
Este proyecto de backend implementa funcionalidades para la generación de órdenes de compra a través de una API RESTful. Proporciona endpoints para recibir formularios con datos de los clientes y generar archivos PDF de las órdenes de compra.

TECNOLOGÍAS UTILIZADAS:
Java 17
Spring Boot 3.2.5
Spring Web para la creación de endpoints RESTful
Lombok para la generación automática de getters, setters, constructores y otros métodos
Validación de datos con Spring Boot Starter Validation
openpdf 2.0.2 para la generación de archivos PDF

ESTRUCTURA DEL PROYECTO:
El proyecto se organiza en tres paquetes principales:

controller: Contiene los controladores REST que gestionan las peticiones HTTP.
entity: Define las entidades de datos utilizadas en el proyecto.
service: Contiene la lógica de negocio y servicios para procesar los datos recibidos y generar las órdenes de compra.

ENDPOINTS:
POST /api/v1/mekatico/guardarOrden: Permite recibir datos de formularios con información del cliente y los productos seleccionados para generar una orden de compra.

GET /api/v1/mekatico/generarPdf: Genera un archivo PDF de la orden de compra basado en los datos recibidos previamente.
