# Spring Security en etapas progresivas

## 🔰 Etapa 1: Seguridad Básica (usuarios en memoria)

- Autenticación con usuario y contraseña hardcodeados.
- Autorización por roles (ej: USER, ADMIN).
- httpBasic() o formLogin() para probar con Postman o navegador.

## ⚙️ Etapa 2: Seguridad con Base de Datos

- Usuarios y roles se guardan en una tabla.
- Spring Security consulta la DB para validar credenciales.
- Se usa UserDetailsService personalizado.
- Contraseñas cifradas con BCrypt.

## 🪪 Etapa 3: Seguridad con JWT

- API completamente RESTful.
- Login devuelve un JWT (no hay sesión, es stateless).
- Rutas protegidas según el token.
- Middleware que valida el JWT en cada request.
- Roles embebidos en el token.