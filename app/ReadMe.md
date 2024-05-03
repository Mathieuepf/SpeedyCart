# SpeedyCart APP
Ce projet est une application Android développée avec Android Studio.
L'application a pour but de mettre en relation des particuliers et des commerces de proximité pour livrer des produits.

## Context

Cette API est dévellopé dans le cadre d'un projet de semestre de 4e année à l'[EPF](https://www.epf.fr/).

## Pré-requis
Avant d'utiliser cette application, assurez-vous d'avoir les éléments suivants :

* Android Studio installé sur votre ordinateur
* Un téléphone Android ou un émulateur Android pour exécuter l'application
* Connexion Internet (pour accéder à l'API si elle est sur un server distant)

## Guide d'Installation
1. Cloner le dépôt Git : `git clone https://github.com/Mathieuepf/SpeedyCart.git`
2. Ouvrir Android Studio
3. Importer le projet en sélectionnant le répertoire du projet cloné
4. Attendre que les dépendances soient téléchargées et que le projet soit synchronisé
5. Connecter votre téléphone Android à votre ordinateur via USB (ou utiliser un émulateur Android)
6. Sélectionner votre appareil ou l'émulateur dans Android Studio
7. Appuyer sur le bouton "Run" pour installer et exécuter l'application

## Guide d'Utilisation
Ouvrez l'application et explorez là.

## Structure
Le projet suit une structure MVVM :
- `model/` : contains class representing data entities
- `view/` : Contains the main activity class responsible for the UI layout and interaction
- `viewModel` : contains class that act as a intermediate between the view and the model
- `repository/` : contains class responsible for managing data operations