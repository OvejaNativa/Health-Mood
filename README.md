# Health-Mood E-Commerce Backend

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-blue)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-green)

## 📋 Descripción

Health-Mood es una aplicación backend de e-commerce desarrollada con Spring Boot 3.x, enfocada en productos de salud y bienestar. La aplicación implementa un sistema completo de gestión con autenticación JWT, roles de usuario, y un conjunto robusto de APIs REST.

## 🚀 Características Principales

- **Autenticación y Autorización**: Sistema JWT con roles (ADMIN, CUSTOMER)
- **Gestión de Productos**: CRUD completo con búsquedas avanzadas
- **Carrito de Compras**: Sistema de carrito personalizado por usuario
- **Gestión de Pedidos**: Control completo del flujo de pedidos
- **Sistema de Categorías**: Organización de productos por categorías
- **Blog/Posts**: Sistema de publicaciones (solo ADMIN puede crear/modificar)
- **Gestión de Imágenes**: Subida y gestión de imágenes de productos
- **Validación de Datos**: Validaciones robustas con Bean Validation
- **Seguridad por Endpoints**: Control granular de acceso a recursos

## 🛠️ Tecnologías

### Backend
- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Security 6.x** con JWT
- **Spring Data JPA**
- **Maven**

### Base de Datos
- **MySQL**
- **Hibernate ORM**

### Dependencias Principales
- **Lombok**: Reducción de código boilerplate
- **JWT (jsonwebtoken)**: Autenticación JWT
- **Spring Validation**: Validación de datos
- **Spring DevTools**: Desarrollo en caliente
- **Dotenv**: Gestión de variables de entorno

## 📁 Estructura del Proyecto

```
Health-Mood/
├── src/
│   ├── main/
│   │   ├── java/cl/healthmood/Health/Mood/
│   │   │   ├── controller/          # Controladores REST
│   │   │   │   ├── CartItemController.java
│   │   │   │   ├── CategoryController.java
│   │   │   │   ├── CustomerController.java
│   │   │   │   ├── ImageController.java
│   │   │   │   ├── PedidoController.java
│   │   │   │   ├── PostController.java
│   │   │   │   └── ProductController.java
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── CustomerRequest.java
│   │   │   │   ├── CustomerResponse.java
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── LoginResponse.java
│   │   │   │   ├── PedidoRequest.java
│   │   │   │   ├── PedidoResponse.java
│   │   │   │   ├── ProductRequest.java
│   │   │   │   └── ProductResponse.java
│   │   │   ├── mapper/              # Mappers DTO ↔ Entity
│   │   │   │   ├── CustomerMapper.java
│   │   │   │   ├── PedidoMapper.java
│   │   │   │   ├── PostMapper.java
│   │   │   │   └── ProductMapper.java
│   │   │   ├── model/               # Entidades JPA
│   │   │   │   ├── CartItem.java
│   │   │   │   ├── Category.java
│   │   │   │   ├── Customer.java
│   │   │   │   ├── Image.java
│   │   │   │   ├── OrderItem.java
│   │   │   │   ├── Payment.java
│   │   │   │   ├── Pedido.java
│   │   │   │   ├── Post.java
│   │   │   │   ├── Product.java
│   │   │   │   └── Role.java
│   │   │   ├── repository/          # Repositorios JPA
│   │   │   │   ├── CartItemRepository.java
│   │   │   │   ├── CategoryRepository.java
│   │   │   │   ├── CustomerRepository.java
│   │   │   │   ├── ImageRepository.java
│   │   │   │   ├── PedidoRepository.java
│   │   │   │   ├── PostRepository.java
│   │   │   │   └── ProductRepository.java
│   │   │   ├── security/            # Configuración de Seguridad
│   │   │   │   ├── config/
│   │   │   │   │   └── SecurityConfig.java
│   │   │   │   ├── controller/
│   │   │   │   │   └── AuthController.java
│   │   │   │   ├── jwt/
│   │   │   │   │   ├── JwtAuthenticationEntryPoint.java
│   │   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   │   └── JwtTokenProvider.java
│   │   │   │   └── service/
│   │   │   │       └── CustomUserDetailsService.java
│   │   │   ├── service/             # Servicios de Negocio
│   │   │   │   ├── impl/
│   │   │   │   │   ├── CustomerServiceImpl.java
│   │   │   │   │   ├── PedidoServiceImpl.java
│   │   │   │   │   ├── PostServiceImpl.java
│   │   │   │   │   └── ProductServiceImpl.java
│   │   │   │   ├── CustomerService.java
│   │   │   │   ├── PedidoService.java
│   │   │   │   ├── PostService.java
│   │   │   │   └── ProductService.java
│   │   │   └── HealthMoodApplication.java
│   │   └── resources/
│   │       ├── application.yml      # Configuración principal
│   │       ├── application-local.yml # Configuración local
│   │       └── schema.sql           # Script DDL
│   └── test/
│       └── java/
│           └── cl/healthmood/Health/Mood/
│               └── HealthMoodApplicationTests.java
├── .env                            # Variables de entorno
├── pom.xml                         # Configuración Maven
└── README.md                       # Este archivo
```

## 🔐 Sistema de Seguridad

### Roles y Permisos

| Recurso | ADMIN | CUSTOMER | Anónimo |
|---------|-------|----------|---------|
| **Productos** | ✅ CRUD completo | ✅ Solo lectura | ✅ Solo lectura |
| **Categorías** | ✅ CRUD completo | ✅ Solo lectura | ✅ Solo lectura |
| **Posts** | ✅ CRUD completo | ✅ Solo lectura | ✅ Solo lectura |
| **Imágenes** | ✅ CRUD completo | ✅ Solo lectura | ✅ Solo lectura |
| **Customers** | ✅ Ver todos | ✅ Solo perfil propio | ❌ |
| **Cart Items** | ✅ Ver todos | ✅ Solo los propios | ❌ |
| **Pedidos** | ✅ Ver todos | ✅ Solo los propios | ❌ |

### Endpoints de Autenticación

```http
POST /api/auth/register  # Registro de usuario
POST /api/auth/login     # Inicio de sesión
```

## 🚀 Instalación y Configuración

### Prerrequisitos

- Java 21+
- Maven 3.8+
- MySQL 8.0+

### 1. Clonar el repositorio

```bash
git clone https://github.com/OvejaNativa/Health-Mood.git
cd Health-Mood
```

### 2. Configurar base de datos

Crear una base de datos MySQL:

```sql
CREATE DATABASE health_mood;
CREATE USER 'health_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON health_mood.* TO 'health_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configurar variables de entorno

Crear archivo `.env` en la raíz del proyecto:

```env
# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/health_mood
DB_USERNAME=health_user
DB_PASSWORD=password

# JWT Configuration
JWT_SECRET=your-super-secret-jwt-key-here
JWT_EXPIRATION=86400000

# Server Configuration
SERVER_PORT=8080
```

### 4. Compilar y ejecutar

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## 📚 API Endpoints

### Productos
```http
GET    /api/products/list              # Listar todos los productos
GET    /api/products/paginated         # Productos paginados
GET    /api/products/{id}              # Obtener producto por ID
POST   /api/products                   # Crear producto (ADMIN)
PUT    /api/products/{id}              # Actualizar producto (ADMIN)
DELETE /api/products/{id}              # Eliminar producto (ADMIN)
GET    /api/products/search            # Buscar productos por nombre
GET    /api/products/category/{id}     # Productos por categoría
GET    /api/products/price-range       # Productos por rango de precio
```

### Categorías
```http
GET    /api/categories                 # Listar categorías
GET    /api/categories/{id}            # Obtener categoría por ID
POST   /api/categories                 # Crear categoría (ADMIN)
PUT    /api/categories/{id}            # Actualizar categoría (ADMIN)
DELETE /api/categories/{id}            # Eliminar categoría (ADMIN)
```

### Carrito
```http
GET    /api/cart-items                 # Obtener items del carrito
POST   /api/cart-items                 # Agregar item al carrito
PUT    /api/cart-items/{id}            # Actualizar item del carrito
DELETE /api/cart-items/{id}            # Eliminar item del carrito
```

### Pedidos
```http
GET    /api/pedidos                    # Obtener pedidos
GET    /api/pedidos/{id}               # Obtener pedido por ID
POST   /api/pedidos                    # Crear pedido
PUT    /api/pedidos/{id}               # Actualizar pedido
DELETE /api/pedidos/{id}               # Eliminar pedido
```

### Posts
```http
GET    /api/posts                      # Listar posts
GET    /api/posts/{id}                 # Obtener post por ID
POST   /api/posts                      # Crear post (ADMIN)
PUT    /api/posts/{id}                 # Actualizar post (ADMIN)
DELETE /api/posts/{id}                 # Eliminar post (ADMIN)
```

## 🧪 Testing

Ejecutar las pruebas:

```bash
mvn test
```

## 🔧 Configuración de Perfiles

### Local (application-local.yml)
```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## 📄 Documentación API

Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación de la API (si tienes Swagger configurado) en:

```
http://localhost:8080/swagger-ui.html
```

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-caracteristica`)
3. Commit tus cambios (`git commit -am 'Agrega nueva característica'`)
4. Push a la rama (`git push origin feature/nueva-caracteristica`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👥 Autores

- **OvejaNativa** - [GitHub](https://github.com/OvejaNativa)
- **Ariel Astorga** - [GitHub](https://github.com/astorgaAriel)
- **MoreDevJ** - [GitHub](https://github.com/moredevj)  
- **Carolina Hurtado** - [GitHub](https://github.com/carohurtadosaa)

## 🆘 Soporte

Si tienes alguna pregunta o problema, por favor abre un issue en GitHub o contacta al desarrollador.

---

**Health-Mood** - E-commerce Backend para productos de salud y bienestar 🌿
