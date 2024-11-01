package Nicolo_Mecca.Progetto_SpringBoot_week_5.payloads;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UploadFotoProfiloDTO(
        @NotNull(message = "Il file dell'immagine è obbligatorio!")
        MultipartFile file,

        @NotNull(message = "L'ID del dipendente è obbligatorio!")
        Long dipendenteId
) {
}