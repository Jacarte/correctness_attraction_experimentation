package services;

import com.github.antlrjavaparser.api.CompilationUnit;
import com.github.antlrjavaparser.api.ImportDeclaration;
import com.github.antlrjavaparser.api.body.*;
import com.github.antlrjavaparser.api.expr.*;
import com.github.antlrjavaparser.api.stmt.*;
import com.github.antlrjavaparser.api.type.ClassOrInterfaceType;
import com.github.antlrjavaparser.api.type.PrimitiveType;
import com.github.antlrjavaparser.api.type.Type;
import com.github.antlrjavaparser.api.type.VoidType;
import services.api.INamingService;
import services.api.ITranslator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Translator implements ITranslator {


    static Type intType = new PrimitiveType(PrimitiveType.Primitive.Int);
    static Type boolType = new PrimitiveType(PrimitiveType.Primitive.Boolean);

    INamingService _namingService;
    String fileName;

    int rowCumul = 0;
    int colCumul = 0;

    public void setFileName(String name){
        this.fileName = name;
    }

    List<InterestingPoint> pBis = new ArrayList<>();

    public Translator(INamingService namingService){
        this._namingService = namingService;
    }


    public List<InterestingPoint> getPbis(){
        return pBis;
    }

    @Override
    public void translate(VariableDeclarator node, Type target) {

        node.setInit(translateExpression(node.getInit(), target));

    }

    @Override
    public void translate(AssignExpr expr, Type target) {
        expr.setValue(translateExpression(expr.getValue(), target));
    }

    @Override
    public void translate(UnaryExpr expr, Type target) {

        // expr.setExpr(translateExpression(expr.getExpr(), target));
    }

    @Override
    public void translate(MethodCallExpr expr, List<Type> callArgs, Type storedType) {

        List<Expression> args = new ArrayList<>();

        if(expr.getArgs() != null)
            for(int i = 0; i < expr.getArgs().size(); i++){
                args.add(translateExpression(expr.getArgs().get(i), callArgs.get(i)));
            }

        expr.setArgs(args);
    }

    private Expression translateExpression(Expression base, Type t){

        if(t == null)
            return base;


        InterestingPoint pbi = new InterestingPoint();

        String perturbationName = _namingService.getNextPerturbationName();

        pbi.pbiIndex = _namingService.getPbiIndex();

        pbi.colNumber = base.getBeginColumn() + colCumul;
        pbi.rowNumber = base.getEndLine() + colCumul;

        pbi.fileName = this.fileName;

        pbi.variableId = perturbationName;
        pbi.original = base.toString();

        if(t.equals(intType)) {

            pbi.perturbationType = "NUMERICAL";

            pBis.add(pbi);

            return new MethodCallExpr(getScope(), _namingService.getPIntMethodName(), Arrays.asList(
                    new NameExpr(perturbationName), base));
        }
        if(t.equals(boolType)) {

            pbi.perturbationType = "BOOLEAN";

            pBis.add(pbi);

            return new MethodCallExpr(getScope(), _namingService.getPBoolMethodName(), Arrays.asList(
                    new NameExpr(perturbationName), base));
        }

        return base;
    }

    @Override
    public void translate(BinaryExpr expr, Type leftType, Type rightType) {

        Type t = leftType;

        if(leftType == null)
            t = rightType;

        expr.setLeft(translateExpression(expr.getLeft(), t));
        expr.setRight(translateExpression(expr.getRight(), t));

    }

    @Override
    public void translate(ForStmt expr, List<Type> updateTypes) {

        expr.setCompare(translateExpression(expr.getCompare(), new PrimitiveType(PrimitiveType.Primitive.Boolean)));

        List<Expression> exp = new ArrayList<>();

        for(int i = 0; i < updateTypes.size(); i++){
            exp.add(translateExpression(expr.getUpdate().get(i), updateTypes.get(i)));
        }

        expr.setUpdate(exp);
    }

    @Override
    public void translate(ReturnStmt expr, Type returnType) {
        expr.setExpr(translateExpression(expr.getExpr(), returnType));
    }

    @Override
    public void translate(SwitchStmt expr, Type selectorType) {
        expr.setSelector(translateExpression(expr.getSelector(), selectorType));
    }

    @Override
    public void translate(SwitchEntryStmt expr, Type labelType) {

        if(expr.getLabel() != null)
            expr.setLabel(translateExpression(expr.getLabel(), labelType));

    }

    @Override
    public void translate(IfStmt expr, Type conditionType) {
        expr.setCondition(translateExpression(expr.getCondition(), conditionType));
    }

    @Override
    public void translate(WhileStmt expr, Type conditionType) {
        expr.setCondition(translateExpression(expr.getCondition(), conditionType));
    }

    @Override
    public void translate(DoStmt expr, Type conditionType) {
        expr.setCondition(translateExpression(expr.getCondition(), conditionType));
    }



    @Override
    public void translate(ExpressionStmt expr, Type t) {
        if(expr.getExpression() instanceof UnaryExpr){
            expr.setExpression(translateExpression(expr.getExpression(), t));
        }
    }


    @Override
    public void translate(ArrayAccessExpr expr, Type t) {
        expr.setIndex(translateExpression(expr.getIndex(), t));
    }

    @Override
    public void translate(EnclosedExpr expr, Type t) {
        expr.setInner(translateExpression(expr.getInner(), t));
    }

    @Override
    public void translate(ClassOrInterfaceDeclaration expr) {

        expr.setName(expr.getName() + _namingService.getInstrumentationSuffix());

        expr.getMembers().add(new FieldDeclaration(8, new ClassOrInterfaceType("IServiceProvider"), new VariableDeclarator(
            new VariableDeclaratorId("_serviceProvider"),
            new ObjectCreationExpr(getScope(), new ClassOrInterfaceType("ServiceProvider"), new ArrayList<>())
        )));

        MethodDeclaration setup = new MethodDeclaration(9,
                new VoidType(), "setupPerturbation" );

        ClassOrInterfaceType perturbationInterfaceClass = new ClassOrInterfaceType("IPerturbationEngine");


        ClassOrInterfaceType concretePerturbationClass = new ClassOrInterfaceType("PerturbationEngine");

        expr.getMembers().add(new FieldDeclaration(9, perturbationInterfaceClass, new VariableDeclarator(new VariableDeclaratorId(_namingService.engineName()))));

        setup.setBody(new BlockStmt(new ArrayList<>()));

        setup.getBody().getStmts().add(new ExpressionStmt(
                new AssignExpr(new NameExpr(_namingService.engineName()),
                        new MethodCallExpr(getScope(),"_serviceProvider.getPerturbationEngine"), AssignExpr.Operator.assign)
        ));


        getPbis().forEach(c -> {
            addPbi(expr, c, setup);
        });
        expr.getMembers().add(setup);

        rowCumul = expr.getBeginLine();
        colCumul = expr.getBeginColumn();
    }

    @Override
    public void translate(CompilationUnit unit) {

        _namingService.getPerturbationNamespace().forEach(name -> {
            unit.getImports().add(new ImportDeclaration(new NameExpr(name), false, true));
        });

    }

    private void addPbi(ClassOrInterfaceDeclaration dec, InterestingPoint pbi, MethodDeclaration setupExpression){


        ClassOrInterfaceType perturbationType = new ClassOrInterfaceType(
                pbi.perturbationType.equals("NUMERICAL")?
                        _namingService.getIntegerPerturbationClassName() :
                        _namingService.getBooleanPerturbationClassName()
        );



        VariableDeclarator variable = new VariableDeclarator(
                new VariableDeclaratorId(pbi.variableId)
        );



        setupExpression.getBody().getStmts().add(new ExpressionStmt(
                new AssignExpr(new NameExpr(pbi.variableId),
                        new ObjectCreationExpr(getScope(), perturbationType,
                                Arrays.asList(
                                        new StringLiteralExpr(String.format("%s (%s:%s)", pbi.fileName, pbi.rowNumber, pbi.colNumber)),
                                        new IntegerLiteralExpr(String.valueOf(pbi.pbiIndex - 1)),
                                        new StringLiteralExpr(pbi.original),
                                        new NameExpr("_serviceProvider")
                                )), AssignExpr.Operator.assign)
        ));

        /*
                */

        FieldDeclaration f = new FieldDeclaration(_namingService.getPerturbationInstanceModifiers(), perturbationType, variable);

        dec.getMembers().add(f);

    }


    Expression scope;

    @Override
    public Expression getScope() {
        return scope;
    }

    @Override
    public void setScope(Expression scope) {
        this.scope = scope;
    }

}

