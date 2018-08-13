package services.policies;

public interface IPolicy<T> extends IGenericPolicy {

    boolean check(T node);
}
