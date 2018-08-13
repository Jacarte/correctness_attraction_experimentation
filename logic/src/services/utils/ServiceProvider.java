package services.utils;

import com.github.antlrjavaparser.api.type.Type;
import com.github.antlrjavaparser.api.visitor.GenericVisitor;
import services.PerturbationEngine;
import services.Translator;
import services.api.INamingService;
import services.api.ITranslator;
import services.engine.IPerturbationEngine;
import services.engine.NamingService;
import services.engine.PolicyFactory;
import services.interpolator.IntegerInterpolator;
import services.interpolator.Interpolator;
import services.interpolator.SequencialInterpolator;
import services.policies.IPolicyFactory;
import services.visitor.TransformationVisitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

public class ServiceProvider implements IServiceProvider {

    static IServiceProvider _serviceProvider;

    public static IServiceProvider getProvider(){

        if(_serviceProvider == null)
            _serviceProvider = new ServiceProvider();

        return _serviceProvider;
    }

    static INamingService _namingService;

    @Override
    public INamingService getNamingService() {

        if(_namingService == null)
            _namingService = new NamingService();

        return _namingService;
    }

    ITranslator _translator;

    @Override
    public ITranslator getTranslator() {

        if(_translator == null)
            _translator = new Translator(getNamingService());

        return _translator;
    }

    static GenericVisitor<Type, Object> visitor;

    @Override
    public GenericVisitor<Type, Object> getVisitor() {


        if(visitor == null) visitor = new TransformationVisitor(this);

        return visitor;

    }

    IntegerInterpolator iInterpolator;

    @Override
    public IntegerInterpolator getIntegerInterpolator() {
        return new SequencialInterpolator();
    }

    @Override
    public IPolicyFactory getPolicyFactory() {
        return new PolicyFactory();
    }

    static PerturbationEngine engine;

    @Override
    public IPerturbationEngine getPerturbationEngine() {

        if(engine == null)
            engine = new PerturbationEngine();

        return engine;
    }
}
