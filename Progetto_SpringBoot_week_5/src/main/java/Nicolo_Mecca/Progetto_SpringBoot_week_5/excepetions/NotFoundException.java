package Nicolo_Mecca.Progetto_SpringBoot_week_5.excepetions;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}