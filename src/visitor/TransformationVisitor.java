package visitor;

import com.github.antlrjavaparser.api.CompilationUnit;
import com.github.antlrjavaparser.api.ImportDeclaration;
import com.github.antlrjavaparser.api.PackageDeclaration;
import com.github.antlrjavaparser.api.TypeParameter;
import com.github.antlrjavaparser.api.body.*;
import com.github.antlrjavaparser.api.expr.*;
import com.github.antlrjavaparser.api.stmt.*;
import com.github.antlrjavaparser.api.type.*;
import com.github.antlrjavaparser.api.visitor.GenericVisitor;
import dsl.AnnotationDSLService;
import policy.IPolicyFactory;
import translator.ITranslator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

public class TransformationVisitor implements GenericVisitor<Type, Object> {

    static TransformationVisitor instance;

    private IPolicyFactory _policyFactory;
    private ITranslator _translator;

    Map<String, Type> variables = new TreeMap<>();
    Map<String, Type> methods = new TreeMap<>();

    String lastMethodName = "";


    protected TransformationVisitor(IPolicyFactory policyFactory, ITranslator translator){
        this._policyFactory = policyFactory;
        this._translator = translator;
    }

    public static TransformationVisitor getInstance(IPolicyFactory policyFactory, ITranslator translator){
        if(instance == null) instance = new TransformationVisitor(policyFactory, translator);

        return instance;
    }

    @Override
    public Type visit(CompilationUnit n, Object arg) {

        n.getTypes().forEach(t -> {
            t.accept(instance, arg);
        });

        return null;
    }

    @Override
    public Type visit(PackageDeclaration n, Object arg) {
        return null;
    }

    @Override
    public Type visit(ImportDeclaration n, Object arg) {
        return null;
    }

    @Override
    public Type visit(TypeParameter n, Object arg) {


        return null;
    }

    @Override
    public Type visit(ClassOrInterfaceDeclaration n, Object arg) {

        methods.clear();

        System.out.println("Entering in class declaration members for class: " + n.getName() );
        n.getMembers().forEach(m -> {
            m.accept(instance, arg);
        });

        methods.clear();

        _translator.translate(n);

        return null;
    }


    @Override
    public Type visit(EnumDeclaration n, Object arg) {
        return null;
    }

    @Override
    public Type visit(EmptyTypeDeclaration n, Object arg) {
        return null;
    }

    @Override
    public Type visit(EnumConstantDeclaration n, Object arg) {
        return null;
    }

    @Override
    public Type visit(AnnotationDeclaration n, Object arg) {
        return null;
    }

    @Override
    public Type visit(AnnotationMemberDeclaration n, Object arg) {
        return null;
    }

    @Override
    public Type visit(FieldDeclaration n, Object arg) { // The fields declaration out of method body must not be pertubated

        return null;
    }

    @Override
    public Type visit(VariableDeclarator n, Object arg) {

        if(n.getInit() != null)
            n.getInit().accept(instance, arg);

        return null;
    }

    @Override
    public Type visit(VariableDeclaratorId n, Object arg) {

        return null;
    }

    @Override
    public Type visit(ConstructorDeclaration n, Object arg) {
        return null;
    }

    @Override
    public Type visit(MethodDeclaration n, Object arg) {

        variables.clear();

        n.getParameters().forEach(p -> {
            p.accept(instance, arg);
        });

        methods.put(n.getName(), n.getType());
        lastMethodName = n.getName();

        if(_policyFactory.getPolicy(n).check(n)) {

            n.getBody().accept(instance, arg);
        }

        variables.clear();

        return null;
    }

    @Override
    public Type visit(Parameter n, Object arg) {

        variables.put(n.getId().getName(), n.getType());

        return null;
    }

    @Override
    public Type visit(CatchParameter n, Object arg) {
        return null;
    }

    @Override
    public Type visit(Resource n, Object arg) {
        return null;
    }

    @Override
    public Type visit(EmptyMemberDeclaration n, Object arg) {
        return null;
    }

    @Override
    public Type visit(InitializerDeclaration n, Object arg) {
        return null;
    }

    @Override
    public Type visit(ClassOrInterfaceType n, Object arg) {
        return null;
    }

    @Override
    public Type visit(PrimitiveType n, Object arg) {
        return null;
    }

    @Override
    public Type visit(ReferenceType n, Object arg) {
        return null;
    }

    @Override
    public Type visit(VoidType n, Object arg) {
        return null;
    }

    @Override
    public Type visit(WildcardType n, Object arg) {
        return null;
    }

    @Override
    public Type visit(ArrayAccessExpr n, Object arg) {

        n.getIndex().accept(instance, arg);

        _translator.translate(n, new PrimitiveType(PrimitiveType.Primitive.Int));

        if(variables.containsKey(n.getName().toString())){
            Type t = variables.get(n.getName().toString());

            if(t instanceof ReferenceType){
                if(((ReferenceType)t).getArrayCount() > 0)
                    return ((ReferenceType)t).getType();
            }
        }

        return null;
    }

    @Override
    public Type visit(ArrayCreationExpr n, Object arg) {

        n.getDimensions().forEach(d -> {
            d.accept(instance, arg);
        });

        return n.getType();
    }

    @Override
    public Type visit(ArrayInitializerExpr n, Object arg) {

        n.getValues().forEach(e -> {
            e.accept(instance, arg);
        });

        return null;
    }

    @Override
    public Type visit(AssignExpr n, Object arg) {

        Type t = n.getTarget().accept(instance, arg);
        n.getValue().accept(instance, arg);

        _translator.translate(n, t);

        return getReturnType(n.getOperator());
    }

    @Override
    public Type visit(BinaryExpr n, Object arg) {

        Type leftType = n.getLeft().accept(instance, arg);
        Type rightType = n.getRight().accept(instance, arg);

        _translator.translate(n, leftType, rightType);

        return getReturnType(n.getOperator());
    }

    @Override
    public Type visit(CastExpr n, Object arg) {

        n.getExpr().accept(instance, arg);

        return n.getType();
    }

    @Override
    public Type visit(ClassExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(ConditionalExpr n, Object arg) {

        n.getCondition().accept(instance, arg);
        n.getThenExpr().accept(instance, arg);
        n.getElseExpr().accept(instance, arg);

        return null;
    }

    @Override
    public Type visit(EnclosedExpr n, Object arg) {

        Type t = n.getInner().accept(instance, arg);

        _translator.translate(n, t);

        return t;
    }

    @Override
    public Type visit(FieldAccessExpr n, Object arg) {

        return null;

    }

    @Override
    public Type visit(InstanceOfExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(StringLiteralExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(IntegerLiteralExpr n, Object arg) {

        if(_policyFactory.getPolicy(n).check(n)){
            return new PrimitiveType(PrimitiveType.Primitive.Int);
        }

        return null;
    }

    @Override
    public Type visit(LongLiteralExpr n, Object arg) {

        return null;
    }

    @Override
    public Type visit(IntegerLiteralMinValueExpr n, Object arg) {
        return new PrimitiveType(PrimitiveType.Primitive.Int);
    }

    @Override
    public Type visit(LongLiteralMinValueExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(CharLiteralExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(DoubleLiteralExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(BooleanLiteralExpr n, Object arg) {
        return new PrimitiveType(PrimitiveType.Primitive.Boolean);
    }

    @Override
    public Type visit(NullLiteralExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(LambdaExpr n, Object arg) {

        return null;
    }

    @Override
    public Type visit(MethodCallExpr n, Object arg) {

        List<Type> argTypes = new ArrayList<>();

        if(n.getArgs() != null)
            n.getArgs().forEach(e -> {
                argTypes.add(e.accept(instance, arg));
            });

        _translator.translate(n, argTypes, null);

        return methods.get(n.getName());
    }

    @Override
    public Type visit(NameExpr n, Object arg) {

        if(variables.containsKey(n.getName()))
            return variables.get(n.getName());
        return null;
    }

    @Override
    public Type visit(ObjectCreationExpr n, Object arg) {
        n.getScope().accept(instance, arg);

        return null;
    }

    @Override
    public Type visit(QualifiedNameExpr n, Object arg) {

        return null;
    }

    @Override
    public Type visit(ThisExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(SuperExpr n, Object arg) {

        return null;
    }

    @Override
    public Type visit(UnaryExpr n, Object arg) {

        _translator.translate(n, n.getExpr().accept(instance, arg));

        return new PrimitiveType(PrimitiveType.Primitive.Int);
    }

    @Override
    public Type visit(VariableDeclarationExpr n, Object arg) {

        if(_policyFactory.getPolicy(n).check(n)) {


            n.getVars().forEach(v -> {
                v.accept(instance, arg);
                variables.put(v.getId().getName(), n.getType());

                _translator.translate(v, n.getType());
            });


        }
        return null;
    }

    @Override
    public Type visit(MarkerAnnotationExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(SingleMemberAnnotationExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(NormalAnnotationExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(MemberValuePair n, Object arg) {

        n.getValue().accept(instance, arg);

        return null;
    }

    @Override
    public Type visit(MethodReferenceExpr n, Object arg) {

        return null;
    }

    @Override
    public Type visit(ExplicitConstructorInvocationStmt n, Object arg) {

        if(n.getExpr() != null)
            n.getExpr().accept(instance, arg);

        if(n.getArgs() != null)
            n.getArgs().forEach(a -> {
                a.accept(instance, arg);

            });

        return null;
    }

    @Override
    public Type visit(TypeDeclarationStmt n, Object arg) {

        return null;
    }

    @Override
    public Type visit(AssertStmt n, Object arg) {

        n.getCheck().accept(instance, arg);
        n.getMessage().accept(instance, arg);

        return null;
    }

    @Override
    public Type visit(BlockStmt n, Object arg) {

        n.getStmts().forEach(st -> {

            st.accept(instance, arg);
        });

        return null;
    }

    @Override
    public Type visit(LabeledStmt n, Object arg) {

        return null;
    }

    @Override
    public Type visit(EmptyStmt n, Object arg) {
        return null;
    }

    @Override
    public Type visit(ExpressionStmt n, Object arg) {

        Type type = n.getExpression().accept(instance, arg);

        _translator.translate(n, type);

        return null;
    }

    @Override
    public Type visit(SwitchStmt n, Object arg) {

        Type selectorType = n.getSelector().accept(instance, arg);
        AtomicReference<Type> returnType = new AtomicReference<>(null);

        n.getEntries().forEach(s -> returnType.set(s.accept(instance, arg)));

        _translator.translate(n, selectorType);

        return null;
    }

    @Override
    public Type visit(SwitchEntryStmt n, Object arg) {

        Type labelType = n.getLabel().accept(instance, arg);

        AtomicReference<Type> returnType = new AtomicReference<>(null);

        n.getStmts().forEach(s -> {
            returnType.set(s.accept(instance, arg));
        });

        _translator.translate(n, labelType);

        return null;
    }

    @Override
    public Type visit(BreakStmt n, Object arg) {
        return null;
    }

    @Override
    public Type visit(ReturnStmt n, Object arg) {
        Type t =  n.getExpr().accept(instance, arg);

        _translator.translate(n, t);

        methods.put(lastMethodName, t);

        return t;
    }

    @Override
    public Type visit(IfStmt n, Object arg) {

        Type conditionType = n.getCondition().accept(instance, arg);

        _translator.translate(n, conditionType);

        if(n.getThenStmt() != null)
            n.getThenStmt().accept(instance, arg);


        if(n.getElseStmt() != null)
            n.getElseStmt().accept(instance, arg);

        return null;
    }

    @Override
    public Type visit(WhileStmt n, Object arg) {


        n.getCondition().accept(instance, arg);

        _translator.translate(n, new PrimitiveType(PrimitiveType.Primitive.Boolean));

        n.getBody().accept(instance, arg);

        return null;
    }

    @Override
    public Type visit(ContinueStmt n, Object arg) {

        return null;
    }

    @Override
    public Type visit(DoStmt n, Object arg) {

        n.getCondition().accept(instance, arg);
        n.getBody().accept(instance, arg);

        _translator.translate(n, new PrimitiveType(PrimitiveType.Primitive.Boolean));

        return null;
    }

    @Override
    public Type visit(ForeachStmt n, Object arg) {
        n.getIterable().accept(instance, arg);
        n.getBody().accept(instance, arg);

        return null;
    }

    @Override
    public Type visit(ForStmt n, Object arg) {

        if(n.getInit() != null)
            n.getInit().forEach(e -> {
                e.accept(instance, arg);
            });

        Type updateType = null;

        if(n.getCompare() != null) {
             updateType = n.getCompare().accept(instance, arg);
        }

        if(n.getUpdate() != null)
            n.getUpdate().forEach(e -> {
                e.accept(instance, arg);
            });


        n.getBody().accept(instance, arg);

        _translator.translate(n, updateType);

        return null;
    }

    @Override
    public Type visit(ThrowStmt n, Object arg) {

        n.getExpr().accept(instance, arg);
        return null;
    }

    @Override
    public Type visit(SynchronizedStmt n, Object arg) {

        n.getExpr().accept(instance, arg);
        n.getBlock().accept(instance, arg);

        return null;
    }

    @Override
    public Type visit(TryStmt n, Object arg) {

        n.accept(instance, arg);
        return null;
    }

    @Override
    public Type visit(CatchClause n, Object arg) {

        n.getCatchBlock().accept(instance, arg);
        return null;
    }

    private Type getReturnType(AssignExpr.Operator op){

        if(op == AssignExpr.Operator.or
                || op == AssignExpr.Operator.and
        )
            return new PrimitiveType(PrimitiveType.Primitive.Boolean);

        return new PrimitiveType(PrimitiveType.Primitive.Int);
    }

    private Type getReturnType(BinaryExpr.Operator op){

        if(op == BinaryExpr.Operator.and
                || op == BinaryExpr.Operator.equals
                || op == BinaryExpr.Operator.greaterEquals
                || op == BinaryExpr.Operator.greater
                || op == BinaryExpr.Operator.less
                || op == BinaryExpr.Operator.lessEquals
                || op == BinaryExpr.Operator.notEquals
                || op == BinaryExpr.Operator.or
        )
            return new PrimitiveType(PrimitiveType.Primitive.Boolean);

        return new PrimitiveType(PrimitiveType.Primitive.Int);
    }
}
