package com.example.rental.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity @Table(name = "furnizori")
public class Furnizor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message = "VR-02: Denumirea furnizorului este obligatorie")
    private String denumire;
    @Column(length = 50) private String telefon;
    @Column(length = 100) private String email;

    public Furnizor() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
