package Nicolo_Mecca.Progetto_SpringBoot_week_5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private LocalDate data;
    private String note;

    @OneToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    public Prenotazione(LocalDate data, String note, Viaggio viaggio) {
        this.data = data;
        this.note = note;
        this.viaggio = viaggio;
    }
}