package services.interpolator;

public interface Interpolator<Tr, Tvalue> {

    Tr interpolate(Tvalue value);


}
