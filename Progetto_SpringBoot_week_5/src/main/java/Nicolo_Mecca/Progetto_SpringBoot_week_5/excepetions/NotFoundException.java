package Nicolo_Mecca.Progetto_SpringBoot_week_5.excepetions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Il record con id " + id + " non è stato trovato!");
    }
}
