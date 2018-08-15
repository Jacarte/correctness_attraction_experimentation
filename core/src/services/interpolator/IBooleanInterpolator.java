package services.interpolator;

public interface IBooleanInterpolator extends Interpolator {

    boolean getValue(boolean value);

    void incrementInSpace();

    String getPerturbationOperation();
}
