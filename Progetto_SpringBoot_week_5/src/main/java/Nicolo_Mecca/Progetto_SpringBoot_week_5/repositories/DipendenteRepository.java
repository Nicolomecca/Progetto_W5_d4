package Nicolo_Mecca.Progetto_SpringBoot_week_5.repositories;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
}
