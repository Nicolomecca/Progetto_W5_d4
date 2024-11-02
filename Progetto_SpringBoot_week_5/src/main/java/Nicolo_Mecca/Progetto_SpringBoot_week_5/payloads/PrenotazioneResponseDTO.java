package Nicolo_Mecca.Progetto_SpringBoot_week_5.payloads;

import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Prenotazione;
import Nicolo_Mecca.Progetto_SpringBoot_week_5.entities.Viaggio;

import java.time.LocalDate;

public record PrenotazioneResponseDTO(
        Long id,
        LocalDate data,
        String note,
        Viaggio viaggio,
        Long dipendenteId
) {
    public PrenotazioneResponseDTO(Prenotazione prenotazione) {
        this(
                prenotazione.getId(),
                prenotazione.getData(),
                prenotazione.getNote(),
                prenotazione.getViaggio(),
                prenotazione.getDipendente().getId()
        );
    }
}