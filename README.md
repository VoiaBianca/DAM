# Piese de schimb și Lucrări de service

Proiect pentru cursul *Dezvoltarea Aplicațiilor Multistrat* (Sprint 1–3), aplicație „Închirieri și Service Auto".
Implementează modulul de piese de schimb și lucrări de service: modelul de domeniu, serviciile de business, persistența JPA și auditul.

Documentația completă (cerințe, diagrame, reguli de business, cod explicat) se află în
`Sprint1-3_DAM11_PiesaDeSchimb.docx`.

## Tehnologii
- Java 21, Maven
- Spring Boot 3.3.5 (Spring Data JPA, Bean Validation)
- Hibernate, PostgreSQL (aplicația), H2 în memorie (teste)
- JUnit 5

## Model de domeniu
- **LucrareService** (Aggregate Root) cu componentele **LinieLucrare** (piesa folosită și cantitatea)
- **PiesaDeSchimb** (catalog) și **Furnizor**, în afara agregatului
- Reguli de validare VR-01…VR-06 și reguli de calcul CR-01, CR-02

## Structura pachetelor (`com.example.rental`)
| Pachet | Conținut |
|---|---|
| `domain.model` | entități JPA, enum `StatusLucrare`, view `LucrareCostView` |
| `domain.service` | factory-uri, servicii de validare, calcul și audit |
| `domain.service.audit` | listener JPA (`@PrePersist`, `@PreUpdate`, `@PreRemove`) |
| `application.service` | servicii de workflow (cazurile de utilizare) |
| `infrastructure.persistence` | repository-uri Spring Data JPA și varianta JPA cu `EntityManager` |
| `infrastructure.config` | clasa de configurare Spring |

## Rulare
1. Deschide `pom.xml` în IntelliJ IDEA (Open as Project), cu JDK 21.
2. Rulează testele: clic dreapta pe `Test_LucrarePieseDomainServices`, apoi *Run*, sau `mvn test`.
   Testele folosesc H2 în memorie și nu au nevoie de o bază de date instalată.
3. `RentalApplication` pornește cu PostgreSQL (`localhost:5432`, baza `rental`),
   configurat în `src/main/resources/application.properties`.

## Teste
Un singur test, `Test_LucrarePieseDomainServices`, verifică serviciile declarate:
validarea (VR-03, VR-06), calculul (CR-01, CR-02) și auditul.