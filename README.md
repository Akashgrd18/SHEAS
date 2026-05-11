# 🏥 SHEAS — Smart Health Monitoring & Emergency Alert System

A microservices backend that monitors patient vitals in real-time, triggers emergency alerts when critical thresholds are breached, and notifies assigned doctors.

## 🛠️ Tech Stack

Java 17 | Spring Boot 3 | Spring Cloud Gateway | Spring Security + JWT | PostgreSQL | Netflix Eureka | OpenFeign | Swagger

## 📦 Services

| Service | Port | Role |
|---------|------|------|
| Eureka Server | 8070 | Service discovery |
| API Gateway | 8072 | JWT auth, routing |
| Security | 9292 | Login, registration, token generation |
| Patients | 8080 | Patient CRUD, health data, alert triggering |
| Doctors | 8090 | Doctor CRUD, patient linking |
| Alert | 9000 | Alert creation & history |

## 🔐 Auth Flow

1. `POST /sheas/security/auth/login` → Returns JWT
2. Send `Authorization: Bearer <token>` on all other requests
3. Gateway validates token locally → forwards to service

## 🚀 Quick Start

```bash
# Prerequisites: Java 17+, PostgreSQL, Maven
# Create database
psql -c "CREATE DATABASE sheas_db;"

# Start services in order:
# 1. Eureka → 2. Security → 3. Patients → 4. Doctors → 5. Alert → 6. Gateway
cd eurekaServer/eurekaServer && mvn spring-boot:run
```

**Eureka Dashboard:** http://localhost:8070

## 📡 Key Endpoints

| Method | Endpoint | Auth |
|--------|----------|------|
| POST | `/sheas/security/auth/register` | Public |
| POST | `/sheas/security/auth/login` | Public |
| POST | `/sheas/patients/patient/create` | JWT |
| GET | `/sheas/patients/patient/fetch?mobileNumber=` | JWT |
| POST | `/sheas/patients/health/data` | JWT |
| POST | `/sheas/doctors/doctor/create` | JWT |
| GET | `/sheas/doctors/doctor/fetchDoctorDetails?mobileNumber=` | JWT |
| GET | `/sheas/alerts/alert/getAlertHistory?patientId=` | JWT |

## 📮 Postman

Import `SHEAS_Postman_Collection.json` — all endpoints pre-configured with auto JWT token management.

## 👤 Author

**Akash Gupta**
