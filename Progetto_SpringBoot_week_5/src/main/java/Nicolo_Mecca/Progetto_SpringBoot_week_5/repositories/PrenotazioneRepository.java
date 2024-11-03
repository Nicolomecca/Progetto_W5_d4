package Nicolo_Mecca.Progetto_SpringBoot_week_5.repositories;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Prenotazione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    // Verifica se esiste una prenotazione per un dipendente in una data specifica del viaggio
    boolean existsByDipendenteIdAndDataViaggio(Long dipendenteId, LocalDate dataViaggio);

    // Trova tutte le prenotazioni di un dipendente (paginato)
    Page<Prenotazione> findByDipendenteId(Long dipendenteId, Pageable pageable);

    // Trova le prenotazioni per una data specifica del viaggio
    List<Prenotazione> findByDataViaggio(LocalDate dataViaggio);

    // Verifica se esiste una prenotazione per un viaggio in una data specifica del viaggio
    boolean existsByViaggioIdAndDataViaggio(Long viaggioId, LocalDate dataViaggio);

    Optional<Prenotazione> findByViaggioId(Long viaggioId);

}