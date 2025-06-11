# GestionProduits+ – Application de gestion de produits

![Dashboard]([https://github.com/IMADKHKHALIFI/Products-Management-System/raw/main/src/main/resources/images/dashboard.png](https://github.com/IMADKHKHALIFI/Products-Management-System/blob/main/src/main/resources/images/dashbord.png))
![Liste des produits]([https://github.com/IMADKHKHALIFI/Products-Management-System/raw/main/src/main/resources/images/index.png](https://github.com/IMADKHKHALIFI/Products-Management-System/blob/main/src/main/resources/images/index.png))


## 🚀 Présentation

**GestionProduits+** est une application web moderne de gestion de produits développée avec **Spring Boot**, **Thymeleaf**, **Bootstrap 5** et **Spring Security**.  
Elle propose une expérience utilisateur professionnelle, responsive, avec un mode sombre/clair, des statistiques dynamiques, des exports, et une gestion sécurisée des utilisateurs.

---

## 🏗️ Architecture du projet

- **Backend** : Spring Boot, Spring MVC, Spring Security, JPA/Hibernate
- **Frontend** : Thymeleaf, Bootstrap 5, Bootstrap Icons, Chart.js
- **Base de données** : H2 (par défaut, modifiable)
- **Sécurité** : Authentification, rôles (ADMIN, USER)
- **Structure** :
  ```
  src/
    main/
      java/
        .../controller/      # Contrôleurs Spring MVC
        .../model/           # Entités JPA (Product, User, etc.)
        .../repository/      # Repositories Spring Data JPA
        .../service/         # Services métier
      resources/
        templates/           # Vues Thymeleaf (layout, dashboard, products, ...)
        static/              # CSS/JS personnalisés
        images/              # Captures d’écran pour la doc
  ```

---

## ✨ Fonctionnalités principales

### 🔑 Authentification & Sécurité
- Login sécurisé (Spring Security)
- Gestion des rôles (ADMIN, USER)
- Accès restreint à certaines pages (ex : création/suppression réservée à l’ADMIN)

### 📦 Gestion des produits
- **CRUD** complet (Créer, Lire, Modifier, Supprimer)
- Recherche dynamique par nom (barre de recherche dans la navbar)
- Filtres et tri dynamiques (prix, quantité, etc.)
- Export de la liste en PDF/Excel

### 📊 Dashboard & Statistiques
- Cartes de statistiques globales (nombre de produits, valeur totale, produit le plus cher, etc.)
- Graphiques dynamiques (Chart.js) : prix, quantités, évolution du stock
- Résumé statistique sous la table (total, quantité, valeur)

### 🖥️ UI/UX moderne
- Design responsive (Bootstrap 5)
- Mode sombre/clair avec bouton de bascule dans la navbar (🌙/☀️)
- Icônes dynamiques devant chaque produit (💻, 🎧, etc.)
- Badges pour quantités faibles et produits premium
- Boutons animés (hover, transitions)
- Toasts/alertes Bootstrap pour les actions réussies
- Loader animé si la table met du temps à s’afficher

### 👤 Expérience utilisateur
- Affichage du nom ou avatar de l’utilisateur connecté dans la navbar
- Messages de confirmation après modification/suppression
- Navigation fluide entre dashboard, liste, édition, création

---

## 🖼️ Captures d’écran

> Place tes images dans `src/main/resources/images/` puis référence-les ainsi :

- ![Dashboard](src/main/resources/images/dashboard.png)
- ![Liste des produits](src/main/resources/images/products-list.png)
- ![Mode sombre](src/main/resources/images/dark-mode.png)
- ![Edition produit](src/main/resources/images/edit-product.png)

---

## ⚙️ Installation & Lancement

1. **Cloner le projet**  
   `git clone ...`

2. **Configurer la base de données**  
   (Par défaut H2, voir `application.properties`)

3. **Lancer l’application**  
   ```bash
   ./mvnw spring-boot:run
   # ou
   mvn spring-boot:run
   ```

4. **Accéder à l’application**  
   [http://localhost:8084/](http://localhost:8084/)

---

## 📝 Personnalisation & Bonnes pratiques

- **Layouts Thymeleaf** : tous les écrans héritent de `layout1.html` (navbar, dark mode, recherche…)
- **Séparation des rôles** : les actions critiques sont protégées par `sec:authorize`
- **Injection des données** : le contrôleur injecte toujours `products` ou `productList` dans le modèle
- **Gestion des erreurs** : messages d’erreur et de succès affichés en haut de page, disparition automatique
- **Responsive** : toutes les pages sont testées sur mobile et desktop

---

## 📂 Dossier images

Place ici tes captures d’écran pour la documentation :  
`src/main/resources/images/`

---

## 🙏 Remerciements

- [Bootstrap](https://getbootstrap.com/)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Chart.js](https://www.chartjs.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)

---

## 🧑‍💻 Auteur

- IMAD EL KHELYFY
- Année 2025

---
