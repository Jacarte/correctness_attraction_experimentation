package services.visitor;

import com.github.antlrjavaparser.api.*;
import com.github.antlrjavaparser.api.body.*;
import com.github.antlrjavaparser.api.expr.*;
import com.github.antlrjavaparser.api.stmt.*;
import com.github.antlrjavaparser.api.type.*;
import com.github.antlrjavaparser.api.visitor.GenericVisitor;
import services.policies.IPolicyFactory;
import services.api.ITranslator;
import services.utils.IServiceProvider;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class TransformationVisitor implements GenericVisitor<Type, Object> {

    Map<String, Type> variables = new TreeMap<>();
    Map<String, Type> methods = new TreeMap<>();

    String lastMethodName = "";

    IServiceProvider _serviceProvider;

    public TransformationVisitor(IServiceProvider provider){
        _serviceProvider = provider;
    }

    @Override
    public Type visit(CompilationUnit n, Object arg) {

        n.getTypes().forEach(t -> {
            t.accept(_serviceProvider.getVisitor(), arg);
        });

        _serviceProvider.getTranslator().translate(n);

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
            m.accept(_serviceProvider.getVisitor(), arg);
        });

        methods.clear();

        _serviceProvider.getTranslator().translate(n);

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
            n.getInit().accept(_serviceProvider.getVisitor(), arg);

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
            p.accept(_serviceProvider.getVisitor(), arg);
        });

        methods.put(n.getName(), n.getType());
        lastMethodName = n.getName();

        if(_serviceProvider.getPolicyFactory().getPolicy(n).check(n)) {

            n.getBody().accept(_serviceProvider.getVisitor(), arg);
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

        n.getIndex().accept(_serviceProvider.getVisitor(), arg);

        _serviceProvider.getTranslator().translate(n, new PrimitiveType(PrimitiveType.Primitive.Int));

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
            d.accept(_serviceProvider.getVisitor(), arg);
        });

        return n.getType();
    }

    @Override
    public Type visit(ArrayInitializerExpr n, Object arg) {

        n.getValues().forEach(e -> {
            e.accept(_serviceProvider.getVisitor(), arg);
        });

        return null;
    }

    @Override
    public Type visit(AssignExpr n, Object arg) {

        Type t = n.getTarget().accept(_serviceProvider.getVisitor(), arg);
        n.getValue().accept(_serviceProvider.getVisitor(), arg);

        _serviceProvider.getTranslator().translate(n, t);

        return getReturnType(n.getOperator());
    }

    @Override
    public Type visit(BinaryExpr n, Object arg) {

        Type leftType = n.getLeft().accept(_serviceProvider.getVisitor(), arg);
        Type rightType = n.getRight().accept(_serviceProvider.getVisitor(), arg);

        _serviceProvider.getTranslator().translate(n, leftType, rightType);

        return getReturnType(n.getOperator());
    }

    @Override
    public Type visit(CastExpr n, Object arg) {

        n.getExpr().accept(_serviceProvider.getVisitor(), arg);

        return n.getType();
    }

    @Override
    public Type visit(ClassExpr n, Object arg) {
        return null;
    }

    @Override
    public Type visit(ConditionalExpr n, Object arg) {

        n.getCondition().accept(_serviceProvider.getVisitor(), arg);
        n.getThenExpr().accept(_serviceProvider.getVisitor(), arg);
        n.getElseExpr().accept(_serviceProvider.getVisitor(), arg);

        return null;
    }

    @Override
    public Type visit(EnclosedExpr n, Object arg) {

        Type t = n.getInner().accept(_serviceProvider.getVisitor(), arg);

        _serviceProvider.getTranslator().translate(n, t);

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

        if(_serviceProvider.getPolicyFactory().getPolicy(n).check(n)){
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
                argTypes.add(e.accept(_serviceProvider.getVisitor(), arg));
            });

        _serviceProvider.getTranslator().translate(n, argTypes, null);

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
        n.getScope().accept(_serviceProvider.getVisitor(), arg);

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

        _serviceProvider.getTranslator().translate(n, n.getExpr().accept(_serviceProvider.getVisitor(), arg));

        return new PrimitiveType(PrimitiveType.Primitive.Int);
    }

    @Override
    public Type visit(VariableDeclarationExpr n, Object arg) {

        if(_serviceProvider.getPolicyFactory().getPolicy(n).check(n)) {


            n.getVars().forEach(v -> {
                v.accept(_serviceProvider.getVisitor(), arg);
                variables.put(v.getId().getName(), n.getType());

                _serviceProvider.getTranslator().translate(v, n.getType());
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

        n.getValue().accept(_serviceProvider.getVisitor(), arg);

        return null;
    }

    @Override
    public Type visit(MethodReferenceExpr n, Object arg) {

        return null;
    }

    @Override
    public Type visit(ExplicitConstructorInvocationStmt n, Object arg) {

        if(n.getExpr() != null)
            n.getExpr().accept(_serviceProvider.getVisitor(), arg);

        if(n.getArgs() != null)
            n.getArgs().forEach(a -> {
                a.accept(_serviceProvider.getVisitor(), arg);

            });

        return null;
    }

    @Override
    public Type visit(TypeDeclarationStmt n, Object arg) {

        return null;
    }

    @Override
    public Type visit(AssertStmt n, Object arg) {

        n.getCheck().accept(_serviceProvider.getVisitor(), arg);
        n.getMessage().accept(_serviceProvider.getVisitor(), arg);

        return null;
    }

    @Override
    public Type visit(BlockStmt n, Object arg) {

        n.getStmts().forEach(st -> {

            st.accept(_serviceProvider.getVisitor(), arg);
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

        Type type = n.getExpression().accept(_serviceProvider.getVisitor(), arg);

        _serviceProvider.getTranslator().translate(n, type);

        return null;
    }

    @Override
    public Type visit(SwitchStmt n, Object arg) {

        Type selectorType = n.getSelector().accept(_serviceProvider.getVisitor(), arg);
        AtomicReference<Type> returnType = new AtomicReference<>(null);

        n.getEntries().forEach(s -> returnType.set(s.accept(_serviceProvider.getVisitor(), arg)));

        _serviceProvider.getTranslator().translate(n, selectorType);

        return null;
    }

    @Override
    public Type visit(SwitchEntryStmt n, Object arg) {

        Type labelType = n.getLabel().accept(_serviceProvider.getVisitor(), arg);

        AtomicReference<Type> returnType = new AtomicReference<>(null);

        n.getStmts().forEach(s -> {
            returnType.set(s.accept(_serviceProvider.getVisitor(), arg));
        });

        _serviceProvider.getTranslator().translate(n, labelType);

        return null;
    }

    @Override
    public Type visit(BreakStmt n, Object arg) {
        return null;
    }

    @Override
    public Type visit(ReturnStmt n, Object arg) {
        Type t =  n.getExpr().accept(_serviceProvider.getVisitor(), arg);

        _serviceProvider.getTranslator().translate(n, t);

        methods.put(lastMethodName, t);

        return t;
    }

    @Override
    public Type visit(IfStmt n, Object arg) {

        Type conditionType = n.getCondition().accept(_serviceProvider.getVisitor(), arg);

        _serviceProvider.getTranslator().translate(n, conditionType);

        if(n.getThenStmt() != null)
            n.getThenStmt().accept(_serviceProvider.getVisitor(), arg);


        if(n.getElseStmt() != null)
            n.getElseStmt().accept(_serviceProvider.getVisitor(), arg);

        return null;
    }

    @Override
    public Type visit(WhileStmt n, Object arg) {


        n.getCondition().accept(_serviceProvider.getVisitor(), arg);

        _serviceProvider.getTranslator().translate(n, new PrimitiveType(PrimitiveType.Primitive.Boolean));

        n.getBody().accept(_serviceProvider.getVisitor(), arg);

        return null;
    }

    @Override
    public Type visit(ContinueStmt n, Object arg) {

        return null;
    }

    @Override
    public Type visit(DoStmt n, Object arg) {

        n.getCondition().accept(_serviceProvider.getVisitor(), arg);
        n.getBody().accept(_serviceProvider.getVisitor(), arg);

        _serviceProvider.getTranslator().translate(n, new PrimitiveType(PrimitiveType.Primitive.Boolean));

        return null;
    }

    @Override
    public Type visit(ForeachStmt n, Object arg) {
        n.getIterable().accept(_serviceProvider.getVisitor(), arg);
        n.getBody().accept(_serviceProvider.getVisitor(), arg);

        return null;
    }

    @Override
    public Type visit(ForStmt n, Object arg) {

        if(n.getInit() != null)
            n.getInit().forEach(e -> {
                e.accept(_serviceProvider.getVisitor(), arg);
            });

        Type updateType = null;

        if(n.getCompare() != null) {

             updateType = n.getCompare().accept(_serviceProvider.getVisitor(), arg);
        }

        List<Type> updateTypes = new ArrayList<>();

        if(n.getUpdate() != null)
            n.getUpdate().forEach(e -> {
                Type upType = e.accept(_serviceProvider.getVisitor(), arg);
                updateTypes.add(upType);

            });


        n.getBody().accept(_serviceProvider.getVisitor(), arg);

        _serviceProvider.getTranslator().translate(n, updateTypes);

        return null;
    }

    @Override
    public Type visit(ThrowStmt n, Object arg) {

        n.getExpr().accept(_serviceProvider.getVisitor(), arg);
        return null;
    }

    @Override
    public Type visit(SynchronizedStmt n, Object arg) {

        n.getExpr().accept(_serviceProvider.getVisitor(), arg);
        n.getBlock().accept(_serviceProvider.getVisitor(), arg);

        return null;
    }

    @Override
    public Type visit(TryStmt n, Object arg) {

        n.accept(_serviceProvider.getVisitor(), arg);
        return null;
    }

    @Override
    public Type visit(CatchClause n, Object arg) {

        n.getCatchBlock().accept(_serviceProvider.getVisitor(), arg);
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
