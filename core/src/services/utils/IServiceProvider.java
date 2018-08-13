package services.utils;

import com.github.antlrjavaparser.api.visitor.GenericVisitor;
import services.api.INamingService;
import services.api.ITranslator;
import services.engine.IPerturbationEngine;
import services.interpolator.IntegerInterpolator;
import services.interpolator.Interpolator;
import com.github.antlrjavaparser.api.type.*;
import services.policies.IPolicyFactory;

import java.lang.reflect.InvocationTargetException;

public interface IServiceProvider {

    INamingService getNamingService();

    ITranslator getTranslator();

    GenericVisitor<Type, Object> getVisitor();

    IntegerInterpolator getIntegerInterpolator();

    IPolicyFactory getPolicyFactory();

    IPerturbationEngine getPerturbationEngine();

}
