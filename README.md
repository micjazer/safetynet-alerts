SafetyNet Alerts Mission n°5

@ SafetyNet Alerts - Projet Spring Boot

Cette mission a été réalisé dans le cadre de ma formation de Concepteur Développeur d’Applications.  
Je m'appelle Michel Jazeron, et j’ai suivi cette mission avec l’objectif de mettre en pratique mes compétences en Java et en architecture web avec Spring Boot.

@@ Contexte

L’application SafetyNet Alerts simule un système d'information pour les services de secours.  
Elle lit un fichier JSON contenant des données sur les personnes, leurs adresses, leurs antécédents médicaux et les casernes de pompiers, afin de fournir rapidement les bonnes informations aux bons interlocuteurs.

@@ Ce que fait l'application

L’application expose une série d’endpoints REST permettant de répondre à différents besoins :

- Récupérer les personnes desservies par une caserne (/firestation)
- Identifier les enfants vivant à une adresse (/childAlert)
- Renvoyer les numéros de téléphone liés à une caserne (/phoneAlert)
- Obtenir les personnes vivant à une adresse avec leur caserne (/fire)
- Voir toutes les adresses desservies par des casernes avec les foyers concernés (/flood/stations)
- Afficher les informations d’une personne par son nom (/personInfo)
- Lister les adresses mail d’une ville (/communityEmail)

@@ Technologies utilisées

J’ai choisi d’utiliser les outils suivants :

- Java 17
- Spring Boot 3
- Gradle comme système de build
- Jackson pour la manipulation du JSON
- JUnit 5 et MockMvc pour les tests
- JaCoCo et Surefire pour mesurer la couverture et l'exécution des tests
- SLF4J / Logback pour les logs

@@ Comment lancer l’application

Pour faire fonctionner le projet : 

1. Cloner le dépôt :

git clone https://github.com/micjazer/safetynet-alerts.git
cd safetynet-alerts


