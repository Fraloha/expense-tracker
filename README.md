# 💰 Expense Tracker API

REST API per il tracciamento delle spese personali, costruita con **Spring Boot 3**, **Spring Security** e **JWT**.

---

## 🚀 Come avviare il progetto

### Prerequisiti
- Java 17+
- Maven

### Avvio
```bash
./mvnw spring-boot:run
```

Il server parte su `http://localhost:8080`

### H2 Console (database in-browser)
Disponibile su `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:expensedb`
- **Username**: `sa`
- **Password**: *(vuota)*

---

## 🔐 Autenticazione

Tutti gli endpoint (tranne `/api/auth/**`) richiedono un **Bearer Token JWT** nell'header:

```
Authorization: Bearer <token>
```

---

## 📋 Endpoint

### Auth

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| POST | `/api/auth/register` | Registra un nuovo utente |
| POST | `/api/auth/login` | Login e ottieni il token JWT |

#### Registrazione
```json
POST /api/auth/register
{
  "username": "mario",
  "email": "mario@example.com",
  "password": "password123"
}
```

#### Login
```json
POST /api/auth/login
{
  "username": "mario",
  "password": "password123"
}
```

#### Risposta (entrambi)
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "username": "mario",
  "email": "mario@example.com"
}
```

---

### Categorie

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| GET | `/api/categories` | Lista tutte le categorie |
| POST | `/api/categories` | Crea una nuova categoria |
| DELETE | `/api/categories/{id}` | Elimina una categoria |

> Al primo avvio vengono create automaticamente 7 categorie di default (Cibo, Trasporti, Svago, Casa, Salute, Abbigliamento, Altro).

---

### Spese

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| GET | `/api/expenses` | Lista tutte le spese dell'utente |
| GET | `/api/expenses?category=Cibo` | Filtra per categoria |
| GET | `/api/expenses?month=2024-03` | Filtra per mese (formato `yyyy-MM`) |
| GET | `/api/expenses/{id}` | Dettaglio singola spesa |
| POST | `/api/expenses` | Crea nuova spesa |
| PUT | `/api/expenses/{id}` | Aggiorna spesa esistente |
| DELETE | `/api/expenses/{id}` | Elimina spesa |

#### Corpo richiesta (POST / PUT)
```json
{
  "description": "Pranzo al ristorante",
  "amount": 18.50,
  "date": "2024-03-15",
  "categoryId": 1
}
```

#### Risposta
```json
{
  "id": 1,
  "description": "Pranzo al ristorante",
  "amount": 18.50,
  "date": "2024-03-15",
  "categoryName": "Cibo"
}
```

---

### Statistiche

| Metodo | Endpoint | Descrizione |
|--------|----------|-------------|
| GET | `/api/stats/monthly` | Statistiche mese corrente |
| GET | `/api/stats/monthly?month=2024-03` | Statistiche mese specifico |

#### Risposta
```json
{
  "month": "2024-03",
  "total": 342.50,
  "byCategory": {
    "Cibo": 120.00,
    "Trasporti": 45.00,
    "Svago": 177.50
  }
}
```

---

## 🏗️ Struttura del progetto

```
src/main/java/com/expensetracker/
├── ExpenseTrackerApplication.java   # Entry point
├── config/
│   ├── SecurityConfig.java          # Spring Security + JWT
│   └── DataInitializer.java         # Categorie di default
├── controller/
│   ├── AuthController.java          # Register / Login
│   ├── CategoryController.java      # CRUD categorie
│   └── ExpenseController.java       # CRUD spese + stats
├── service/
│   └── ExpenseService.java          # Business logic
├── repository/
│   ├── UserRepository.java
│   ├── CategoryRepository.java
│   └── ExpenseRepository.java
├── model/
│   ├── User.java
│   ├── Category.java
│   └── Expense.java
├── dto/
│   ├── AuthDTOs.java
│   ├── ExpenseDTOs.java
│   ├── CategoryDTOs.java
│   └── StatsDTO.java
├── security/
│   ├── JwtUtils.java                # Generazione/validazione token
│   └── JwtAuthFilter.java           # Filtro HTTP
└── exception/
    └── GlobalExceptionHandler.java  # Gestione errori globale
```

---

## 🔒 Note di sicurezza

- Le password sono hashate con **BCrypt**
- Il token JWT scade dopo **24 ore** (configurabile in `application.properties`)
- Ogni utente vede e modifica **solo le proprie spese**
