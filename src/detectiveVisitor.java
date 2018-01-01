import java.util.*;

public class detectiveVisitor extends MiniJavaBaseVisitor<Integer> {
    String FinalMessage = "";
    int VisitCount = 0;
    /** Goal */
    @Override
    public Integer visitGoal(MiniJavaParser.GoalContext ctx) {
        VisitCount += 1;
        System.out.println("This is the "+this.VisitCount+" visit to the AST.");
        return visitChildren(ctx);
    }
}
