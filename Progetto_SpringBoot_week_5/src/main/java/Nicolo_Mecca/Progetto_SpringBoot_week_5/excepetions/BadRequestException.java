package Nicolo_Mecca.Progetto_SpringBoot_week_5.excepetions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) {
        super(msg);
    }
}
