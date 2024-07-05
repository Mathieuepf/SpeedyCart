# Guide de Contribution

Merci de suivre ces étapes pour assurer un processus de développement organisé et harmonieux.


Workflow GitHub: Nous suivons un workflow basé sur les branches et les Pull Requests (PR). Voici les étapes à suivre :

## 1. Forker le dépôt

1. Forkez le dépôt principal sur GitHub.
2. Clonez votre fork sur votre machine locale :

    ```bash
    git clone https://github.com/votre-nom-utilisateur/SpeedyCart.git
    ```

3. Ajoutez le dépôt principal comme dépôt distant pour pouvoir récupérer les mises à jour :

    ```bash
    git remote add upstream https://github.com/Mathieuepf/SpeedyCart.git
    ```

## 2. Créer une branche

Créez une nouvelle branche pour chaque fonctionnalité ou correctif :

```bash
git switch -c nom-de-la-branche
```

## 3. Développement
Travaillez sur votre branche en faisant des commits réguliers en suivant la convention de nommage :

```bash
<type>(<portée>): <sujet>
```

Avec comme types de commits :
* build : changements affectant le système de build ou des dépendances externes
* ci : changements concernant les fichiers et scripts d’intégration ou de configuration
* feat : ajout d’une nouvelle fonctionnalité
* fix : correction d’un bug
* perf : amélioration des performances
* refactor : modification sans ajout de fonctionnalité ni amélioration des performances
* style : changements de formatage, sans impact fonctionnel
* docs : rédaction ou mise à jour de documentation
* test : ajout ou modification de tests

Pour plus d'information : [buzut.net](https://buzut.net/cours/versioning-avec-git/bien-nommer-ses-commits)

## 4. Pousser les modifications
Poussez vos modifications vers votre fork sur GitHub :

```bash
git push origin nom-de-la-branche
```
## 5. Créer une Pull Request

Allez sur le dépôt principal et créez une Pull Request depuis votre branche. Le titre de la PR doit suivre le même format que les commits.

## 6. Revue de code

Deux membres de l'équipe doivent approuver votre Pull Request avant de pouvoir la fusionner. Pendant ce temps, des commentaires peuvent être laissés et des modifications peuvent être demandées.

## 7. Fusion
Une fois approuvée, la PR doit être fusionnée en utilisant la méthode "Rebase and merge" :

1. Rebase sur la branche principale

2. Merge la branche et supprimez-la après fusion
