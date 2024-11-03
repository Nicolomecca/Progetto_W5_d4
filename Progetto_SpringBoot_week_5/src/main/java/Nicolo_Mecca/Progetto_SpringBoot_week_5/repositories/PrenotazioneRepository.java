package Nicolo_Mecca.Progetto_SpringBoot_week_5.repositories;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Prenotazione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteIdAndDataViaggio(Long dipendenteId, LocalDate dataViaggio);

    Page<Prenotazione> findByDipendenteId(Long dipendenteId, Pageable pageable);

    List<Prenotazione> findByDataViaggio(LocalDate dataViaggio);

    boolean existsByViaggioIdAndDataViaggio(Long viaggioId, LocalDate dataViaggio);


}