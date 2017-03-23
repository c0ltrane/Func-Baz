
package baz.interpreter;

import baz.semantics.*;
import baz.syntax.node.*;

public class InterpreterException
        extends RuntimeException {

    private final String message;

    private final Token token;

    private Frame frame;

    public InterpreterException(
            String message,
            Token token,
            Frame frame) {

        this.message = message;
        this.token = token;
        this.frame = frame;
    }

    @Override
    public String getMessage() {

        return this.message + " at line " + this.token.getLine() + " position "
                + this.token.getPos() + getTrace();
    }

    private final static String EOL = System.getProperty("line.separator");

    public String getTrace() {

        if (this.frame == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Frame currentFrame = this.frame.getParent();

        while (currentFrame != null) {
            sb.append(EOL);
            sb.append("  called from ");

            FunctionInfo functionInfo = currentFrame.getFunctionInfo();
            String name = functionInfo == null ? "main program"
                    : "'" + functionInfo.getName() + "'";
            sb.append(name);

            TId callLocation = currentFrame.getCallLocation();
            sb.append(" at line ");
            sb.append(callLocation.getLine());
            sb.append(" position ");
            sb.append(callLocation.getPos());

            currentFrame = currentFrame.getParent();
        }

        return sb.toString();
    }
}
