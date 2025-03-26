# SafetyNet Alerts 🚨

## Description
SafetyNet Alerts est une application back-end REST développée avec Spring Boot. Elle permet de gérer et d'exposer des informations de sécurité (habitants, casernes, dossiers médicaux) pour une ville, à destination des services de secours.

## Fonctionnalités
- Lecture et écriture de données depuis un fichier JSON (pas de base de données)
- Exposition d’une API REST avec plusieurs endpoints GET/POST/PUT/DELETE
- Logging des requêtes et réponses (INFO, DEBUG, ERROR)
- Respect de l’architecture MVC et des principes SOLID
- Tests unitaires (JUnit + Mockito) et tests MockMvc
- Couverture de code mesurée avec JaCoCo (>80 %)
- Rapports Surefire et JaCoCo générés automatiquement

## Stack technique
- Java 17+
- Spring Boot 3.x
- Maven
- Jackson (parser JSON)
- JUnit 5, Mockito
- JaCoCo, Surefire
- Log4j

## Démarrage
1. Cloner le dépôt :
```
git clone <url-du-repo>
```
2. Lancer l’application :
```
mvn spring-boot:run
```
3. Accéder aux endpoints via `http://localhost:8080/...`

## Tests
Lancer les tests :
```
mvn clean verify
```
- Rapport JaCoCo : `target/site/jacoco/index.html`
- Rapport Surefire : `target/surefire-reports/`

## Auteur
Michel Jazeron