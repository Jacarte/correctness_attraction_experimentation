package services.utils;

import com.github.antlrjavaparser.api.type.Type;
import com.github.antlrjavaparser.api.visitor.GenericVisitor;
import experiment.PBoolModel;
import experiment.POneModel;
import services.PerturbationEngine;
import services.Translator;
import services.api.INamingService;
import services.api.ITranslator;
import services.engine.*;
import services.interpolator.*;
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

        StaticUtils.serviceProvider = _serviceProvider;

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

    static ILogger logger;

    @Override
    public ILogger getLogger() {
        if(logger == null) logger = new ConsoleLoggerService();
        return logger;
    }

    @Override
    public IStatusLoggerService getLoggerService() {
        return new StatusLoggerService();
    }

    @Override
    public IPerturbationModel getModel() {

        String type = CommandLineParser.CONFIG.get("type");

        switch (type){
            case "pbool":
                return new PBoolModel();
            case "pone":
                return new POneModel();
        }

        return null;
    }

    static ExectionPolicy policy;

    @Override
    public ExectionPolicy getExecutionPolicy() {
        return policy;
    }

    @Override
    public void setExecutionPolicy(ExectionPolicy policy) {
        ServiceProvider.policy = policy;
    }

    @Override
    public void resetVisitor() {
        visitor = new TransformationVisitor(this);
    }
}
