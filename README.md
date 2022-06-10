# Génerer l'exécutable

A la racine du projet, lancer la commande suivante :
```shell script
mvn clean package
```
Le fichier JAR sera généré.

# Exécuter le fichier JAR

Lancer la commande :
```shell script
java -jar target/LuccaDevises-0.1.0-SNAPSHOT.jar CHEMIN_DU_FICHIER
```

Où CHEMIN_DU_FICHIER est le fichier d'entrée du programme.
