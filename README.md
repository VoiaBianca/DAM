# DAM11 – Sprint 1–3 – PiesaDeSchimb
Proiect demonstrativ Java 21 / Spring Boot 3 / PostgreSQL, aliniat cu documentatia PiesaDeSchimb. Include entitatea JPA, agregatul LucrareService, validare, calcul, workflow, Spring Data repository, repository bonus EntityManager, audit si teste.

## Rulare
1. Instaleaza Java 21, Maven si PostgreSQL.
2. Creeaza baza de date `rental` in PostgreSQL.
3. Configureaza DB_URL, DB_USER, DB_PASSWORD (implicit localhost:5432/rental, postgres/postgres).
4. `mvn test`
5. `mvn spring-boot:run`

## Limite
- Codul este o implementare demonstrativa pentru modulul PiesaDeSchimb, nu aplicatia completa de inchirieri auto.
- `ddl-auto=update` este pentru dezvoltare; pentru productie se recomanda migrari Flyway.
- Auditul scrie in log, nu intr-un tabel de audit.
- Nu include frontend, autentificare sau endpoint-uri REST pentru celelalte module.
- Testele nu au fost executate in acest mediu daca Maven nu este disponibil.
