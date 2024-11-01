package Nicolo_Mecca.Progetto_SpringBoot_week_5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String imgProfilo;

    @OneToMany
    @JoinColumn(name = "dipendente_id")
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    public Dipendente(String username, String nome, String cognome, String email, String imgProfilo, List<Prenotazione> prenotazioni) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.imgProfilo = imgProfilo;
        this.prenotazioni = prenotazioni;
    }
}