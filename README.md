# Eventify – Application de gestion d’événements

## Description

Eventify est une API REST développée avec Spring Boot permettant de gérer des événements.

* Les utilisateurs peuvent s’inscrire et participer à des événements.
* Les organisateurs peuvent créer et gérer leurs événements.
* L’administrateur supervise les utilisateurs et les événements.

L’application est sécurisée avec **Spring Security (Basic Authentication)** et une gestion des rôles basée sur la base de données.

---

## Technologies

* Java / Spring Boot
* Spring Security (stateless)
* Spring Data JPA
* BCryptPasswordEncoder
* Base de données relationnelle (postgres)

---

## Lancement du projet

1. Cloner le projet

```bash
git clone <repo-url>
cd eventify
```

2. Configurer la base de données dans `application.yml`

3. Lancer l’application

```bash
mvn spring-boot:run
```

L’API est disponible sur : `http://localhost:8080`

---

## Rôles

* `ROLE_USER`
* `ROLE_ORGANIZER`
* `ROLE_ADMIN`

Un utilisateur possède **un seul rôle**. Lors de l’inscription, le rôle par défaut est `ROLE_USER`.

---

## Endpoints

### Public

* `POST /api/public/users` → Inscription utilisateur
* `GET /api/public/events` → Liste des événements publics

### USER

* `GET /api/user/profile`
* `POST /api/user/events/{id}/register`
* `GET /api/user/registrations`

### ORGANIZER

* `POST /api/organizer/events`
* `PUT /api/organizer/events/{id}`
* `DELETE /api/organizer/events/{id}`

### ADMIN

* `GET /api/admin/users`
* `PUT /api/admin/users/{id}/role`
* `DELETE /api/admin/events/{id}`

---

## Sécurité

* Authentification **Basic Auth**
* Architecture **stateless**
* `SessionCreationPolicy.STATELESS`
* CSRF désactivé
* Mots de passe chiffrés avec **BCrypt**

### Règles d’accès

* `/api/public/**` → accès libre
* `/api/user/**` → ROLE_USER
* `/api/organizer/**` → ROLE_ORGANIZER
* `/api/admin/**` → ROLE_ADMIN

Un **CustomAuthenticationProvider** et un **UserDetailsService personnalisé** sont utilisés.

Un profil `test` permet de bypasser le mot de passe pour les tests.

---

## Gestion des erreurs

* `AuthenticationEntryPoint` personnalisé → erreurs 401
* `AccessDeniedHandler` personnalisé → erreurs 403
* Gestion centralisée avec `@RestControllerAdvice`

Format standard des erreurs :

```json
{
  "timestamp": "",
  "status": 403,
  "error": "Forbidden",
  "message": "",
  "path": ""
}
```

---

![Diagramme de classes](src/main/java/com/Eventify/Eventify/Util/EncryptionUtil.java/02025-12-16%2015-33-03.png)

