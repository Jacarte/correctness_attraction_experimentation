package services.utils;

public interface IStatusLoggerService {

    void reportStatus(String preffix, double percent, String message);

    void reportHeader(String header);

}
