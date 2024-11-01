package Nicolo_Mecca.Progetto_SpringBoot_week_5.repositories;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
}
