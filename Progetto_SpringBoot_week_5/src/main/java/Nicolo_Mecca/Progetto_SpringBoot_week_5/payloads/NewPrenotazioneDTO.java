package Nicolo_Mecca.Progetto_SpringBoot_week_5.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewPrenotazioneDTO(
        @NotNull(message = "La data è obbligatoria!")
        @Future(message = "La data deve essere futura")
        LocalDate data,

        @NotNull(message = "L'ID del viaggio è obbligatorio!")
        Long viaggioId,

        @NotNull(message = "L'ID del dipendente è obbligatorio!")
        Long dipendenteId
) {
}

