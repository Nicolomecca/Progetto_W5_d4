package Nicolo_Mecca.Progetto_SpringBoot_week_5.payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timestamp) {
}
