# Microservicios Twitter
El proyecto "Twitter Clone" es una aplicación web que simula la funcionalidad básica de la plataforma de redes sociales Twitter. Permite a los usuarios iniciar sesión, enviar mensajes (llamados "tweets") y ver los tweets enviados por otros usuarios en un feed de noticias.
## Características principales

- **Inicio de sesión seguro**: La aplicación utiliza Amazon Cognito para gestionar la autenticación de los usuarios, proporcionando un inicio de sesión seguro mediante JWT (JSON Web Tokens).

- **Interfaz de usuario intuitiva**: La interfaz de usuario de la aplicación es limpia y fácil de usar, permitiendo a los usuarios escribir y enviar tweets de manera sencilla.

- **Envío de tweets**: Los usuarios pueden escribir y enviar mensajes de hasta 280 caracteres, compartiendo sus pensamientos y actualizaciones con otros usuarios de la plataforma.

- **Feed de noticias en tiempo real**: La aplicación muestra un feed de noticias en tiempo real que actualiza automáticamente para mostrar los tweets más recientes de todos los usuarios.

## Tecnologías utilizadas

- **Java**: El backend de la aplicación está desarrollado en Java, utilizando el framework Spark para la creación de API RESTful.

- **Amazon Web Services (AWS)**: La aplicación se despliega en la nube de AWS, utilizando servicios como AWS Lambda, API Gateway, Amazon Cognito y Amazon S3 para proporcionar una infraestructura escalable y segura.

- **MongoDB**: La base de datos utilizada para almacenar los tweets y la información de los usuarios es MongoDB, un sistema de gestión de bases de datos NoSQL.

- **HTML, CSS y JavaScript**: La interfaz de usuario de la aplicación está desarrollada utilizando tecnologías web estándar como HTML, CSS y JavaScript, proporcionando una experiencia de usuario receptiva y atractiva.

## Funcionamiento

https://github.com/JaiderArleyGonzalez/MICROSERVICIOS-TWITTER/assets/89174283/a32ea6f8-c420-4fcd-99cb-f65d1afa3791

## Prerrequisitos

1. **Java Development Kit (JDK)**: Asegúrate de tener instalado al menos JDK 17 o superior en tu sistema. Puedes descargarlo e instalarlo desde [la página oficial de OpenJDK](https://www.oracle.com/co/java/technologies/downloads/).

2. **Maven**: Asegúrate de tener Maven instalado en tu sistema. Maven es una herramienta de gestión de proyectos para Java que se utiliza para compilar y construir la aplicación, así como para gestionar las dependencias. Puedes descargar Maven desde [el sitio web oficial de Apache Maven](https://maven.apache.org) y seguir las instrucciones de instalación para tu sistema operativo.

3. **Docker Desktop**: Asegúrate de tener Docker Desktop instalado en tu sistema. Docker Desktop es una aplicación que permite ejecutar contenedores Docker en sistemas Windows y macOS. Puedes descargar Docker Desktop desde [el sitio web oficial de Docker](https://www.docker.com/products/docker-desktop/).

## Instalación

### Creación de bucket en Amazon S3
1. **Creación del bucket**: Comenzamos creando un bucket de uso general en Amazon S3. Este bucket será utilizado para almacenar los archivos estáticos de nuestra página web. Es importante elegir un nombre único y descriptivo para el bucket.
2. **Configuración de permisos de objetos**: Para garantizar que nuestra página web sea accesible al público, habilitamos las ACL (Listas de Control de Acceso) para los objetos en el bucket. Además, desbloqueamos todo el acceso público, lo que permite que cualquier persona pueda ver la página web estática que montaremos.

    ![Configuración de propiedades en Amazon S3](/media/S3.png)

3. **Carga de archivos**: Cargamos cada uno de los archivos de nuestra página web en el bucket de S3. Es esencial asegurarse de que todos los archivos tengan los permisos de lectura habilitados, lo que permitirá que los usuarios puedan acceder a la página sin problemas.

    ![Carga de archivos en Amazon S3](/media/cargue.png)

    ![Configuración de permisos en Amazon S3](/media/permisos3.png)

### Configuración de Amazon Cognito
1. **Creación del grupo de usuarios**: Iniciamos la configuración de Amazon Cognito creando un grupo de usuarios. Este grupo, llamado "TwittUser", nos permitirá gestionar los usuarios de nuestra aplicación. Configuramos el inicio de sesión con el correo electrónico, lo que proporciona una forma conveniente y segura de autenticar a los usuarios.
2. **Configuración de la política de contraseñas**: Optamos por una política de contraseñas personalizada, sin requerimientos de caracteres especiales. Esto simplifica el proceso de registro y hace que sea más fácil para los usuarios crear y recordar contraseñas seguras.
3. **Gestión de la autenticación y recuperación de cuentas**: Deshabilitamos la autenticación multifactor para simplificar el proceso de inicio de sesión. Sin embargo, dejamos habilitada la recuperación automática de cuentas a través del correo electrónico para ayudar a los usuarios a recuperar el acceso a sus cuentas si olvidan sus contraseñas.
4. **Registro automático y verificación de cuentas**: Permitimos el registro automático de usuarios y configuramos Cognito para que envíe automáticamente mensajes de verificación y confirmación. Esto garantiza que las cuentas de usuario se verifiquen correctamente antes de permitir el acceso.
5. **Configuración de atributos de usuario**: Mantenemos el valor del atributo original activo cuando hay una actualización pendiente y agregamos el nombre de la persona como atributo obligatorio adicional. Esto nos permite recopilar información adicional sobre los usuarios y personalizar su experiencia.
6. **Gestión de correos electrónicos**: Configuramos Cognito para que se encargue de enviar correos electrónicos relacionados con la autenticación y la verificación de cuentas. Esto garantiza que los usuarios reciban los mensajes necesarios para completar el proceso de registro y verificación.
7. **Configuración del cliente de la aplicación**: Establecemos el nombre del grupo de usuarios como "UserClient" y utilizamos la interfaz de usuario alojada de Cognito para proporcionar una experiencia de registro y inicio de sesión intuitiva.
8. **Configuración del dominio de la interfaz de usuario**: Definimos un dominio específico para la interfaz de usuario alojada de Cognito y configuramos un cliente confidencial con un nombre de cliente de aplicación. Esto asegura que la interfaz de usuario esté correctamente integrada con nuestra aplicación web.
9. **Flujos de autenticación**: Añadimos los flujos de autenticación ALLOW_USER_PASSWORD_AUTH, ALLOW_USER_SRP_AUTH y ALLOW_CUSTOM_AUTH para proporcionar múltiples opciones de autenticación y adaptarnos a las preferencias de los usuarios.
10. **Configuración de OAuth 2.0**: Es importante configurar los tipos de concesión de OAuth 2.0 y dejar solo "Concesión de código de autorización" para garantizar la seguridad y el flujo adecuado de tokens de acceso en nuestra aplicación.

Con estos pasos, configuramos Amazon Cognito para proporcionar autenticación segura y manejo de usuarios en nuestra aplicación web "Twitter Clone".

### Creación de máquina virtual EC2 para MongoDB

1. Desde el panel de servicios, selecciona "EC2" para acceder al panel de control de EC2.
2. Haz clic en el botón "Launch Instance" para iniciar el proceso de creación de una nueva instancia de EC2.
3. Deja toda la configuración por defecto y proporciona el par de claves necesarias.
4. Conéctate a la máquina virtual usando SSH:
    ```bash
   ssh -i "dockerprimerweb.pem" ec2-user@ec2-54-82-247-174.compute-1.amazonaws.com
    ```
5. Instala Docker en la instancia:
    ```bash
   sudo yum install docker
    ```
6. Inicia el servicio de Docker:
    ```bash
   sudo service docker start
    ```
7. Configura el usuario en el grupo de Docker para evitar tener que usar "sudo" cada vez:
    ```bash
   sudo usermod -a -G docker ec2-user
    ```
8. Ejecuta el contenedor de Docker para MongoDB:
    ```bash
   docker run -d -p 27017:27017 --name stream mongo
    ```

### Creación de funciones Lambda

Para cada función Lambda necesaria, sigue estos pasos:

1. Crea una nueva función Lambda.
2. Configura la función desde cero, especificando el nombre, tiempo de ejecución (Java 17), y rol de ejecución.
3. Carga el archivo .jar de tu aplicación en la configuración de tiempo de ejecución.
   ![Carga del archivo .jar](/media/jar.png)
4. Edita la configuración del tiempo de ejecución y define el controlador de la función.
   ![Edición de la configuración del tiempo de ejecución](/media/controlador.png)

### Creación del API Gateway

Sigue estos pasos para configurar el API Gateway:

1. Abre el servicio de API Gateway desde la consola de AWS.
2. Crea una nueva API REST con el nombre "Twitter".
3. Selecciona un endpoint de tipo regional.
4. Crea un nuevo recurso y un método asociado, seleccionando la función Lambda correspondiente.
   ![Creación de recurso y método](/media/recurso.png)
5. Habilita CORS para el recurso.
   ![Habilitación de CORS](/media/cors.png)
6. Edita la configuración del método para agregar parámetros de consulta si es necesario.
   ![Edición de configuración del método](/media/email.png)
7. Configura la solicitud de integración y establece una plantilla de asignación si es necesario.
   ![Configuración de solicitud de integración](/media/plantilla.png)
8. Configura un autorizador utilizando el grupo de usuarios de Cognito.
   ![Configuración de autorizador](/media/cognito.png)
9. Asocia el autorizador con la solicitud del método para habilitar la seguridad.
   ![Asociación de autorizador con método](/media/seguridad.png)
10. Repite estos pasos para cada microservicio necesario.
    ![Proceso para los demás microservicios](/media/microservicios.png)

## Arquitectura

![](/media/arquitectura.png)

La arquitectura del sistema se centra en brindar una experiencia segura y eficiente para los usuarios a través de una aplicación web desplegada en Amazon S3. Esta aplicación ofrece una interfaz gráfica intuitiva y funcionalidades clave, como la autenticación de usuarios y la interacción con servicios de backend para gestionar publicaciones y transmisiones.

El proceso comienza cuando un usuario accede a la aplicación a través de un navegador web. Al ingresar, el usuario se encuentra con una página desplegada en Amazon S3 que le ofrece la opción de iniciar sesión. Esta funcionalidad de inicio de sesión es gestionada por Amazon Cognito, un servicio que proporciona autenticación y autorización seguras para aplicaciones web y móviles. Al autenticarse correctamente, Cognito emite un token JWT (JSON Web Token) al usuario, que será utilizado para autorizar las solicitudes posteriores.

Una vez autenticado, el usuario es redirigido a otra página dentro del mismo entorno de Amazon S3. Esta página, que también hace uso del token JWT proporcionado por Cognito, actúa como la interfaz principal de la aplicación, ofreciendo al usuario las funcionalidades necesarias para interactuar con el sistema.

Todas las solicitudes realizadas por el usuario, como enviar un mensaje o consultar las transmisiones disponibles, son gestionadas por un API Gateway de Amazon. Este API Gateway actúa como punto de entrada para las solicitudes HTTP y cuenta con un autorizador integrado que verifica la presencia del token JWT en el encabezado de autorización de cada solicitud. Esto garantiza que solo los usuarios autenticados y autorizados puedan acceder a los recursos protegidos por la aplicación.

El API Gateway está conectado a tres funciones Lambda de AWS: UserService, PostService y StreamService. Cada una de estas funciones cumple un papel específico en la gestión de datos y la lógica de negocio de la aplicación:

* **UserService:** Esta función se encarga de gestionar los datos de los usuarios, como almacenar información de perfil y configuración. Utiliza una base de datos MongoDB alojada en un servidor que corre en el puerto 27017 para almacenar y recuperar la información de los usuarios de manera eficiente.

* **PostService:** Responsable de gestionar las publicaciones de los usuarios. Cuando un usuario envía un mensaje a través de la aplicación, PostService recibe la solicitud y guarda el mensaje, junto con detalles adicionales como el correo del usuario y la fecha de creación, en la base de datos MongoDB.

* **StreamService:** Esta función se encarga de proporcionar una lista de transmisiones disponibles para que los usuarios las vean. Obtiene todos los mensajes almacenados en la base de datos MongoDB y los presenta en un formato adecuado para su visualización en la interfaz de usuario.

## Componentes
La aplicación está compuesta por varios componentes que interactúan entre sí para proporcionar funcionalidades completas y seguras. A continuación se describen cada uno de estos componentes:

### MockMongoService

La clase `MockMongoService` simula la funcionalidad de un servicio de MongoDB al proporcionar una implementación de la interfaz `MongoService`. Esta clase se utiliza principalmente en entornos de desarrollo y pruebas para evitar el acceso a una base de datos real.

### MongoService

La clase `MongoService` es responsable de interactuar con una base de datos MongoDB para realizar operaciones como conexión, desconexión, inserción y recuperación de datos. Utiliza el cliente MongoDB Java para establecer una conexión con la base de datos y manejar las operaciones CRUD en la colección de mensajes.

### PostService

La clase `PostService` implementa la interfaz `RequestHandler` de AWS Lambda y se encarga de gestionar las publicaciones realizadas por los usuarios. Cuando recibe una solicitud de publicación, verifica el tipo de entrada y utiliza el `MongoService` para agregar la publicación a la base de datos MongoDB.

### StreamService

La clase `StreamService` se encarga de obtener todas las transmisiones disponibles desde el servicio MongoDB y devolverlas en formato JSON. Utiliza el `MongoService` para recuperar los mensajes almacenados en la base de datos y los formatea adecuadamente antes de devolverlos como una cadena JSON.

### UsuarioService

La clase `UsuarioService` implementa la interfaz `RequestHandler` de AWS Lambda y maneja las solicitudes relacionadas con la gestión de usuarios. Puede agregar usuarios a una lista interna utilizando un documento `Document` y proporciona funcionalidades para manejar diferentes tipos de entrada, como documentos o mapas.

## Flujo de trabajo

1. **Inicio de sesión**: Los usuarios inician sesión en la aplicación utilizando sus credenciales de usuario.

2. **Envío de tweets**: Una vez autenticados, los usuarios pueden escribir y enviar tweets desde la interfaz de usuario de la aplicación.

3. **Visualización de tweets**: La aplicación muestra los tweets enviados por los usuarios en un feed de noticias en tiempo real, lo que permite a los usuarios ver las actualizaciones más recientes de forma instantánea.

4. **Cierre de sesión**: Los usuarios pueden cerrar sesión en la aplicación en cualquier momento, lo que los desconecta de sus cuentas y garantiza la seguridad de su información personal.

## Testing
Los tests se realizaron para garantizar la calidad y la funcionalidad correcta del código en las clases `PostService` y `StreamService`. Estos tests son esenciales por las siguientes razones:

1. **Validación de la Lógica del Código**: Los tests proporcionan una forma de validar que la lógica implementada en las clases `PostService` y `StreamService` funcione como se espera. Esto asegura que las operaciones de agregar publicaciones y obtener transmisiones se realicen correctamente sin errores.

2. **Garantía de Compatibilidad de Entradas**: Los tests `testAddPostWithDocumentInput()` y `testAddPostWithMapInput()` aseguran que el método `addPost()` de `PostService` pueda manejar tanto entradas de tipo `Document` como de tipo `Map`. Esto garantiza que la funcionalidad sea compatible con diferentes tipos de datos de entrada, lo que aumenta la flexibilidad y la usabilidad del código.

3. **Manejo de Excepciones**: El test `testUnsupportedInputType()` verifica que se lance una excepción `IllegalArgumentException` cuando se proporciona un tipo de entrada no admitido al método `handleRequest()`. Esto es crucial para garantizar que el código maneje correctamente situaciones inesperadas y proporcione mensajes de error claros y significativos.

4. **Validación de la Generación de JSON**: El test `testGetStream()` en `StreamServiceTest` asegura que el método `getStream()` de `StreamService` genere correctamente un JSON que represente todas las transmisiones almacenadas. Esto verifica que la generación de JSON se realice correctamente, lo que es crucial para la interoperabilidad y la comunicación eficaz entre sistemas.


#### testAddPostWithDocumentInput()
- **Descripción:** Este método de prueba verifica si el método `addPost()` de `PostService` maneja correctamente una entrada de tipo `Document`.
- **Contexto inicial (Given):** Se crea un objeto `Document` simulado con un título y contenido de prueba.
- **Acción (When):** Se llama al método `handleRequest()` de `RequestHandlerMock` con el objeto `Document` simulado como entrada.
- **Resultado esperado (Then):** Se espera que el método `addPost()` de `PostService` maneje correctamente el objeto `Document`.

#### testAddPostWithMapInput()
- **Descripción:** Este método de prueba verifica si el método `addPost()` de `PostService` maneja correctamente una entrada de tipo `Map`.
- **Contexto inicial (Given):** Se crea un objeto `Document` simulado con un título y contenido de prueba.
- **Acción (When):** Se llama al método `handleRequest()` de `RequestHandlerMock` con el objeto `Document` simulado como entrada.
- **Resultado esperado (Then):** Se espera que el método `addPost()` de `PostService` maneje correctamente el objeto `Document`.

#### testUnsupportedInputType()
- **Descripción:** Este método de prueba verifica si el método `handleRequest()` de `RequestHandlerMock` lanza una excepción `IllegalArgumentException` cuando se proporciona un tipo de entrada no admitido.
- **Contexto inicial (Given):** Se crea un objeto de tipo no admitido.
- **Acción (When):** Se llama al método `handleRequest()` de `RequestHandlerMock` con el objeto de tipo no admitido como entrada.
- **Resultado esperado (Then):** Se espera que se lance una excepción `IllegalArgumentException`.

### `StreamServiceTest.java`

#### testGetStream()
- **Descripción:** Este método de prueba verifica si el método `getStream()` de `StreamService` genera correctamente un JSON que representa todas las transmisiones almacenadas en el servicio Mongo.
- **Contexto inicial (Given):** Se crea una lista de documentos simulados que representan las transmisiones.
- **Acción (When):** Se llama al método `getStream()` de `StreamService` después de configurar el servicio Mongo simulado.
- **Resultado esperado (Then):** Se espera que el método `getStream()` de `StreamService` genere un JSON que contiene todas las transmisiones simuladas en el formato adecuado.

Para probarlos se ejecuta el siguiente comando:

```
mvn test
```

Se obtendrá lo siguiente:

![](/media/test.png)

## Consideraciones
* La ejecución de los Test depende en gran medida de que la base de datos se encuentre encendida.
* si se desea ejecutar de manera local funcionando correctamente se debe cambiar la dirección a la cual se referencia la base de datos mongo en el atributo URL_DATABASE de la clase MongoService.
  * puedes ejecutar el siguiente comando para tener tu propia base de datos MongoDB local:
  
    ```
    docker run -d -p 27017:27017 --name stream mongo
    ```
## Autores
- Jaider Arley González Arias
- Miguel Angel Barrera Diaz
