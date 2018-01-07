import java.util.*;

public class detectiveVisitor extends MiniJavaBaseVisitor<Integer> {

    Map<String, String> Inheritance = new HashMap<String, String>(); // Type    -   Type
    Map<String, String> Function    = new HashMap<String, String>(); // Method  -   Class
    Map<String, String> Declaration  = new HashMap<String, String>(); // Var     -   Type
    Map<String, String> Parameters  = new HashMap<String, String>(); // Class+Method+No. - Type (to Check)

    String FinalMessage = "";
    int VisitCount = 0;
    String CurrentClass = "";
    String CurrentMethod = "";

    /** Goal */
    public Integer visitGoal(MiniJavaParser.GoalContext ctx) {
        VisitCount += 1;
        return visitChildren(ctx);
    }

    /** MainClass */
    @Override
    public Integer visitMainClass(MiniJavaParser.MainClassContext ctx) {
        this.CurrentClass = ctx.getChild(1).getText();
        return visitChildren(ctx);
    }

    /** ClassDeclaration */
    @Override
    public Integer visitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx) {
        this.CurrentClass = ctx.getChild(1).getText();
        if (ctx.getChild(2).equals("extends"))
            Inheritance.put(this.CurrentClass, ctx.getChild(3).getText());
        return visitChildren(ctx);
    }

    /** MethodDeclaration */
    @Override
    public Integer visitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx) {
        this.CurrentMethod = ctx.getChild(2).getText();
        Function.put(ctx.getChild(2).getText(), CurrentClass);
        return visitChildren(ctx);
    }

    /** Type */
    @Override
    public Integer visitType(MiniJavaParser.TypeContext ctx) {
        return visitChildren(ctx);
    }

    /** Statement */
    @Override
    public Integer visitStatement(MiniJavaParser.StatementContext ctx) {
        return visitChildren(ctx);
    }

    /** Expression */
    @Override
    public Integer visitExpression(MiniJavaParser.ExpressionContext ctx) {
        if (ctx.getChildCount() > 2 && ctx.getChild(1).getText().equals(".")) {
            String CheckVar = ctx.getChild(0).getText();
            String CheckMethod = ctx.getChild(2).getText();
            if (Declaration.containsKey(CheckVar)) {
                String CheckClass = Declaration.get(CheckVar);
                if (Function.containsKey(CheckMethod)) {
                    String DesignatedClass = Function.get(CheckMethod);
                    while (!CheckClass.equals(DesignatedClass) && Inheritance.containsKey(CheckClass)) {
                        CheckClass = Inheritance.get(CheckClass);
                    }
                    if (!DesignatedClass.equals(CheckClass)) {
                        FinalMessage += "\nError: Wrong Method Invoke @ "+ctx.getText()+" \n"+"Method:\t"+CheckMethod+"\nVar:\t"+CheckVar+"\n";
                        FinalMessage += "The method corresponds to this type: " + DesignatedClass+"\n";
                    }
                } else {
                    FinalMessage += "\nError: No method @ "+ctx.getText()+" Exists\n"+"Method:\t"+CheckMethod+"\nVar:\t"+CheckVar+"\n";
                }
            }
        }
        return visitChildren(ctx);
    }

    /** Parameter */
    @Override
    public Integer visitParameter(MiniJavaParser.ParameterContext ctx) {
        return visitChildren(ctx);
    }

    /** VarDeclaration */
    @Override
    public Integer visitVarDeclaration(MiniJavaParser.VarDeclarationContext ctx) {
        Declaration.put(ctx.getChild(1).getText(), ctx.getChild(0).getText());
        return visitChildren(ctx);
    }

    /** ParameterList */
    @Override
    public Integer visitParameterList(MiniJavaParser.ParameterListContext ctx) {
        Integer temp = 0;
        for (int i = 0; i < ctx.getChildCount(); i+= 2) {
            Parameters.put(CurrentClass + "-" + CurrentMethod + "-" + temp, ctx.getChild(i).getChild(0).getText());
            temp += 1;
        }
        return visitChildren(ctx);
    }

}
