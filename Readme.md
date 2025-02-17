# MacgOrdenadores

MacgOrdenadores es una aplicación web para gestionar un almacén de ordenadores. Permite listar, añadir, editar y eliminar ordenadores, así como buscar por diferentes criterios.

## Características

- Listar todos los ordenadores
- Añadir un nuevo ordenador
- Editar un ordenador existente
- Eliminar un ordenador
- Buscar ordenadores por peso, número de teclas, si es Intel, y nombre

## Tecnologías Utilizadas

- Java
- Spring Boot
- JPA (Java Persistence API)
- H2 Database (para desarrollo)
- Maven
- JavaScript
- Axios
- Bootstrap

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/tu-usuario/MacgOrdenadores.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd MacgOrdenadores
    ```
3. Compila y ejecuta la aplicación:
    ```sh
    mvn spring-boot:run
    ```

## Uso

1. Abre tu navegador web y navega a `http://localhost:8080`.
2. Utiliza la interfaz para gestionar los ordenadores en el almacén.

## API Endpoints

- `GET /api/ordenadores`: Obtener todos los ordenadores
- `GET /api/ordenadores/{id}`: Obtener un ordenador por ID
- `POST /api/ordenadores`: Crear un nuevo ordenador
- `PUT /api/ordenadores/{id}`: Actualizar un ordenador existente
- `DELETE /api/ordenadores/{id}`: Eliminar un ordenador
- `GET /api/ordenadores/buscar/peso`: Buscar ordenadores por peso
- `GET /api/ordenadores/buscar/numeroTeclas`: Buscar ordenadores por número de teclas
- `GET /api/ordenadores/buscar/esIntel`: Buscar ordenadores por si es Intel
- `GET /api/ordenadores/buscar/nombre`: Buscar ordenadores por nombre

## Contribución

Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.