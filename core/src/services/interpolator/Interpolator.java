package services.interpolator;

public interface Interpolator{

    Object getNext();

    boolean canNext();

    void reset();
}
