package Nicolo_Mecca.Progetto_SpringBoot_week_5.repositories;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}
