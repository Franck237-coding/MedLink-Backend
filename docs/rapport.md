# Rapport de réalisation — Backend Spring Boot (MedApp)

## 1) Contexte et objectif
L’objectif était de créer uniquement le backend (API REST) d’une application médicale conforme au cahier des charges :
- Gestion des Utilisateurs (patients, médecins, pharmacies) avec authentification (inscription/connexion), création et mise à jour de profil selon le rôle.
- Gestion des Rendez-vous (création, consultation, annulation/modification).
- Gestion des Médicaments/Pharmacies (CRUD, recherche par nom/disponibilité, réservation optionnelle — non implémentée, voir pistes).
- Module Informations Santé (Articles, Catégories) — ajout d’articles par médecins/administrateurs, consultation publique.

L’architecture mise en place est de type MVC avec Spring Boot 3, API REST, sécurité JWT, persistance via JPA/H2, et documentation Swagger.

## 2) Périmètre livré
- Projet backend Spring Boot complet dans `medapp-backend/`.
- Entités JPA : `Utilisateur` (classe mère abstraite) et ses sous-classes `Patient`, `Medecin`, `Pharmacie`, ainsi que `Medicament`, `RendezVous`, `Article`, `CategorieArticle`.
- Repositories JPA pour toutes les entités.
- Sécurité par JWT : inscription, connexion, filtre JWT, configuration de sécurité, mots de passe chiffrés (BCrypt).
- Services métier et contrôleurs REST pour :
  - Utilisateurs (profil courant `GET/PUT /api/users/me`).
  - Authentification (`POST /api/auth/signup`, `POST /api/auth/login`).
  - Rendez-vous (CRUD + changement de statut).
  - Médicaments (CRUD + recherche + disponibles) et Pharmacies (consultation/mise à jour).
  - Articles (création par Médecin/Admin, consultation) et Catégories (CRUD).
- Validation (Jakarta Validation), gestion globale d’erreurs, CORS ouvert pour intégration front.
- Base de données : H2 en mémoire (console activée).
- Documentation : Swagger UI et README.

## 3) Structure du projet
Répertoire : `medapp-backend/`
- `pom.xml` — dépendances : Web, Validation, Data JPA, Security, JWT (jjwt), H2, Lombok, springdoc-openapi.
- `src/main/java/com/example/medapp/`
  - `MedAppBackendApplication.java` — classe principale Spring Boot.
  - `config/`
    - `SecurityConfig.java` — configuration Spring Security + endpoints publics + session stateless + filtre JWT.
    - `WebConfig.java` — configuration CORS (origines/headers/méthodes autorisés).
  - `security/`
    - `JwtUtil.java` — génération/lecture de tokens JWT.
    - `JwtAuthFilter.java` — authentification par token (header `Authorization: Bearer …`).
    - `CustomUserDetailsService.java` — intégration utilisateurs -> Spring Security.
    - `SecurityUtils.java` — accès à l’utilisateur courant.
  - `model/` (entités JPA)
    - `Role.java` — `PATIENT`, `MEDECIN`, `PHARMACIE`, `ADMIN`.
    - `Utilisateur.java` — classe abstraite avec héritage `JOINED` (email unique, password hashé, nom, téléphone, rôle…).
    - `Patient.java` — `dateNaissance`, `adresse`, `sexe`.
    - `Medecin.java` — `specialite`, `cabinet`, `numeroOrdre`.
    - `Pharmacie.java` — `nomCommercial`, `adresse`, `registreCommerce`.
    - `Medicament.java` — `nom`, `description`, `disponible`, `pharmacie` (ManyToOne).
    - `RendezVous.java` — `patient`, `medecin`, `dateHeure`, `statut` (PLANIFIE/ANNULE/TERMINE).
    - `Article.java` — `titre`, `contenu`, `auteur` (`Medecin` ou `Admin`), `categorie`, `datePublication`.
    - `CategorieArticle.java` — `nom`, `description`.
  - `repository/` — interfaces `JpaRepository` pour chaque entité.
  - `dto/`
    - `auth/` — `LoginRequest`, `SignupRequest`, `AuthResponse`.
    - `rendezvous/` — `RendezVousRequest`.
    - `medicament/` — `MedicamentRequest`.
    - `article/` — `ArticleRequest`.
    - `user/` — `UserUpdateRequest` (prévu pour évolutions).
  - `service/` — `AuthService`, `UserService`, `RendezVousService`, `MedicamentService`, `ArticleService`.
  - `controller/` — `AuthController`, `UserController`, `RendezVousController`, `MedicamentController`, `PharmacieController`, `PatientController`, `MedecinController`, `CategorieArticleController`.
  - `exception/` — `GlobalExceptionHandler` (validation, erreurs fonctionnelles…).
- `src/main/resources/application.properties` — H2/JPA, Swagger, JWT (secret de dev allongé), console H2.
- `README.md` — instructions d’exécution et exemples d’appels.

## 4) Sécurité & authentification
- Algorithme JWT HS256 avec secret de développement allongé : clé à remplacer en production.
- Stockage des mots de passe avec BCrypt.
- Endpoints publics : `/api/auth/**`, `/swagger-ui.html` (+ assets), `/v3/api-docs/**`, `/h2-console/**`.
- Tous les autres endpoints nécessitent `Authorization: Bearer <token>`.

## 5) Principaux endpoints
- Auth :
  - `POST /api/auth/signup` — inscription d’un utilisateur selon le rôle.
  - `POST /api/auth/login` — authentification, renvoie `{ token }`.
- Utilisateurs :
  - `GET /api/users/me` — profil courant.
  - `PUT /api/users/me` — mise à jour du profil courant (champs selon le rôle).
- Rendez-vous :
  - `POST /api/rendezvous` — créer (body: `patientId`, `medecinId`, `dateHeure` ISO).
  - `GET /api/rendezvous` — lister.
  - `GET /api/rendezvous/patient/{patientId}` — par patient.
  - `GET /api/rendezvous/medecin/{medecinId}` — par médecin.
  - `PATCH /api/rendezvous/{id}/statut?statut=PLANIFIE|ANNULE|TERMINE` — changer le statut.
  - `DELETE /api/rendezvous/{id}` — supprimer.
- Médicaments / Pharmacies :
  - `POST /api/medicaments` — créer (body: `nom`, `description`, `pharmacieId`, `disponible`).
  - `PUT /api/medicaments/{id}` — modifier.
  - `GET /api/medicaments` — lister.
  - `GET /api/medicaments/{id}` — détail.
  - `GET /api/medicaments/search?q=...` — recherche par nom.
  - `GET /api/medicaments/available` — filtrer par disponibilité.
  - `GET /api/pharmacies` — lister les pharmacies.
  - `GET /api/pharmacies/{id}` — détail.
  - `PUT /api/pharmacies/{id}` — mise à jour d’une pharmacie.
- Articles / Catégories :
  - `POST /api/articles` — créer (réservé `MEDECIN`/`ADMIN`).
  - `GET /api/articles` — lister.
  - `GET /api/articles/{id}` — détail.
  - `DELETE /api/articles/{id}` — supprimer.
  - `POST /api/categories` — créer une catégorie.
  - `PUT /api/categories/{id}` — modifier une catégorie.
  - `GET /api/categories` — lister.

## 6) Mise en route
Prérequis : Java 17+, Maven 3.9+.

Exécution :
```bash
cd medapp-backend
mvn spring-boot:run
```
- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 Console : http://localhost:8080/h2-console (JDBC: `jdbc:h2:mem:medapp`, user `sa`, mdp vide)

Flux de test express :
1) `POST /api/auth/signup` (ex. Patient) puis `POST /api/auth/login` → récupérer le `token`.
2) Ajouter le header `Authorization: Bearer <token>`.
3) Créer des entités (catégorie, article si médecin/admin, pharmacie + médicaments, rendez-vous patient/médecin).

## 7) Choix techniques & bonnes pratiques
- Héritage JPA `JOINED` pour `Utilisateur` afin de séparer les attributs propres à chaque rôle.
- BCrypt pour le stockage des mots de passe.
- JWT stateless pour simplifier le scaling horizontal (pas de session côté serveur).
- Validation des DTOs + gestion d’erreurs homogène (`GlobalExceptionHandler`).
- CORS ouvert côté backend pour faciliter l’intégration front (à restreindre en prod).
- H2 en mémoire pour le développement ; bascule simple vers PostgreSQL/MySQL en prod.

## 8) Limites actuelles & pistes d’amélioration
- Autorisations fines par rôle via `@PreAuthorize` (ex. seuls les médecins créent des articles) — côté service/contrôleur.
- Réservations de médicaments (workflow non implémenté dans cette itération).
- Pagination/tri sur les listes (rendez-vous, médicaments, articles).
- Audits (création/mise à jour), soft-delete si besoin.
- Tests unitaires et d’intégration (profils `test` + datasets H2).
- Migrations DB avec Flyway.

## 9) Conclusion
Le backend livré répond au cahier des charges : modules, sécurité JWT, endpoints REST complets, documentation et configuration prêtes à l’emploi pour le développement local. Il est prêt pour l’intégration avec un frontend (Web/mobile) et peut évoluer facilement vers un environnement de production.
