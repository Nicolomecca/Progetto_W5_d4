package Nicolo_Mecca.Progetto_SpringBoot_week_5.repositories;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Prenotazione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteIdAndData(Long dipendenteId, LocalDate data);

    // Trova tutte le prenotazioni di un dipendente (paginato)
    Page<Prenotazione> findByDipendenteId(Long dipendenteId, Pageable pageable);

    // Metodi aggiuntivi utili
    List<Prenotazione> findByData(LocalDate data);

    boolean existsByViaggioIdAndData(Long viaggioId, LocalDate data);

}