# MesCours — Gestion des cours

MesCours est une application de gestion des cours et des professeurs. L’interface Angular permet de consulter et rechercher les cours, de les administrer selon le rôle de l’utilisateur et d’accéder à la liste des professeurs. Deux services Spring Boot fournissent les API et utilisent chacun une base PostgreSQL.

| Projet | Responsabilités | URL locale |
| --- | --- | --- |
| [`users-microservice`](backend/users-microservice) | Inscription, vérification de l’adresse e-mail, authentification et émission des jetons JWT. | `http://localhost:8082/users` |
| [`cours`](backend/cours) | API des cours et des professeurs, accès aux données et contrôle des accès par JWT. | `http://localhost:8081/cours` |
| [`MesCours`](frontend/MesCours) | Interface Angular pour la connexion, l’inscription, les cours et les professeurs. | `http://localhost:4200` |

## Technologies et structure

- Backend avec Java 17, Spring Boot 4.1.2 pour les utilisateurs et 4.1.1 pour les cours, Spring Security, Spring Data JPA, PostgreSQL et les wrappers Maven.
- Frontend avec Angular 20.3, TypeScript 5.9, RxJS 7.8 et Bootstrap 5.3.

```text
backend/
├── users-microservice/
│   ├── pom.xml
│   └── src/
└── cours/
    ├── pom.xml
    └── src/
frontend/
└── MesCours/
    ├── angular.json
    ├── package.json
    ├── package-lock.json
    └── src/
.gitignore
README.md
```

## Prérequis

- Git et un JDK compatible Java 17. Les wrappers Maven inclus téléchargent la version propre à chaque service au premier lancement.
- Node.js 22.12 ou ultérieur dans la série 22, avec npm. Cette version est compatible avec l’Angular CLI du projet.
- PostgreSQL accessible depuis les deux services.
- Un compte SMTP autorisé à envoyer les e-mails de vérification. La configuration actuelle utilise Gmail sur le port `587` avec STARTTLS.

## Installation

```powershell
git clone https://github.com/AmenallahBensalem/cours.git
cd cours
```

Créez les bases PostgreSQL `users_db` et `cours_db` sur `localhost:5432`. Le compte de chaque service doit avoir accès à sa base et pouvoir créer les tables. Vous pouvez surcharger l’URL JDBC propre à chaque service avec `SPRING_DATASOURCE_URL`.

Définissez les variables dans l’environnement du processus avant de lancer chaque backend.

| Variable | Service | Valeur attendue |
| --- | --- | --- |
| `SPRING_DATASOURCE_USERNAME` | Les deux | Identifiant PostgreSQL du service concerné |
| `SPRING_DATASOURCE_PASSWORD` | Les deux | Mot de passe PostgreSQL du service concerné |
| `APP_JWT_SECRET` | Les deux | Même clé aléatoire de signature JWT dans les deux services |
| `SPRING_MAIL_USERNAME` | Utilisateurs | Adresse du compte SMTP |
| `SPRING_MAIL_PASSWORD` | Utilisateurs | Mot de passe SMTP ou mot de passe d’application |

Lancez ensuite les trois projets dans des terminaux distincts, chacun ouvert à la racine du dépôt au départ.

**Service des utilisateurs**

```powershell
cd backend/users-microservice
.\mvnw.cmd spring-boot:run
```

**Service des cours**

```powershell
cd backend/cours
.\mvnw.cmd spring-boot:run
```

Sur macOS ou Linux, utilisez `./mvnw` pour les deux commandes Maven.

**Frontend Angular**

```powershell
cd frontend/MesCours
npm ci
npm start
```

Ouvrez [`http://localhost:4200`](http://localhost:4200). Sur une base utilisateurs neuve, créez les rôles applicatifs après le premier démarrage du service et avant la première inscription. Exécutez cette instruction dans `users_db`.

```sql
INSERT INTO roles (role) VALUES ('USER'), ('ADMIN')
ON CONFLICT (role) DO NOTHING;
```

L’inscription attribue le rôle `USER`. Pour accéder aux actions d’administration, attribuez le rôle `ADMIN` à un compte vérifié.

## Connexions entre les services

- Angular appelle `/login`, `/register` et `/verifyEmail/{code}` sous `http://localhost:8082/users`. L’URL de base se trouve dans [`auth.ts`](frontend/MesCours/src/app/services/auth.ts).
- Les URL de l’API des cours (`http://localhost:8081/cours/api`) et des professeurs (`http://localhost:8081/cours/prof`) se trouvent dans [`environment.ts`](frontend/MesCours/src/environments/environment.ts) et [`environment.development.ts`](frontend/MesCours/src/environments/environment.development.ts).
- Le service des utilisateurs renvoie le JWT dans l’en-tête `Authorization`. Angular transmet le jeton au service des cours avec le préfixe `Bearer`. Pour valider ce jeton, le service des cours doit utiliser la même valeur de `APP_JWT_SECRET`.
- Les deux backends autorisent l’origine Angular `http://localhost:4200` dans leur configuration CORS.

## Compilation

Exécutez `.\mvnw.cmd -DskipTests package` dans chacun des deux dossiers backend, ou `./mvnw -DskipTests package` sur macOS et Linux. Pour Angular, exécutez `npm run build` depuis `frontend/MesCours`, après installation des dépendances.
