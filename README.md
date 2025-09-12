# 📰 BlogApp (Backend)

**BlogApp** es el backend de una aplicación de blogs desarrollado en **Java con Spring Boot**.  
Se encarga de gestionar publicaciones, usuarios y la comunicación con el frontend, proporcionando una API RESTful.

---

## ⚙️ Características principales

- 📑 **Gestión de publicaciones**: crear, leer, actualizar y eliminar blogs (CRUD).  
- 👤 **Gestión de usuarios**: registro, autenticación y roles básicos.  
- 🔗 **API RESTful** para conectar con el frontend (BlogApp_Frontend en TypeScript).  
- 🗄️ **Persistencia con base de datos** relacional (MySQL o PostgreSQL).  
- ✅ **Validaciones** y manejo de errores centralizado.  
- 🔒 Preparado para incluir **seguridad con Spring Security** (JWT, sesiones, etc.).  

---

## 🛠️ Tecnologías utilizadas

- **Java 17**  
- **Spring Boot 3.x**  
- **Spring Data JPA**  
- **Spring Web (REST API)**  
- **MySQL / PostgreSQL**  
- **Maven**  
- **JUnit 5** para pruebas  

---

## 📂 Estructura del proyecto

BlogApp
│── src/main/java/com/blogapp/ # Código fuente principal
│ ├── controller/ # Controladores REST
│ ├── model/ # Entidades (Post, User, etc.)
│ ├── repository/ # Repositorios JPA
│ ├── service/ # Lógica de negocio
│ └── BlogAppApplication.java # Clase principal
│
│── src/main/resources/
│ ├── application.properties # Configuración de DB y servidor
│
│── src/test/java/com/blogapp/ # Pruebas unitarias
│── pom.xml # Dependencias Maven


---

## 🚀 Instalación y ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/291efx/BlogApp.git
   
2. Abrir el proyecto en IntelliJ IDEA o cualquier IDE compatible con Maven.
3. Configurar la base de datos en application.properties:

   spring.datasource.url=jdbc:mysql://localhost:3306/blogapp
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update

4. Compilar y ejecutar:
   mvn spring-boot:run

5. La API estará disponible en:
   http://localhost:8080/api

📌 Endpoints principales

    GET /api/posts → Listar todas las publicaciones
    GET /api/posts/{id} → Ver publicación por ID
    POST /api/posts → Crear nueva publicación
    PUT /api/posts/{id} → Editar publicación existente
    DELETE /api/posts/{id} → Eliminar publicación

📸 Futuras mejoras

    🔐 Autenticación con JWT y Spring Security
    💬 Sistema de comentarios en publicaciones
    ❤️ Reacciones (likes/dislikes)
    🌍 Deploy en Heroku / AWS
    👨‍💻 Autor

Desarrollado por Erick Fiestas Plaza
📧 erick.fiestas.plaza.20@gmail.com
🌐 Portafolio
📝 Licencia

Este proyecto es de uso académico y personal.
Si deseas utilizarlo con fines comerciales, por favor contacta al autor.
