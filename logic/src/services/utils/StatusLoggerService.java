package services.utils;

public class StatusLoggerService implements IStatusLoggerService {

    @Override
    public void reportStatus(String preffix,double percent, String message) {

        System.out.print(String.format("\r%s %s %s %s", preffix, (int)(percent*100) + "%", StaticUtils.printPercent(percent), message));

    }

    @Override
    public void reportHeader(String header) {
        System.out.println(header);
    }
}
