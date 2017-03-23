
package baz.semantics;

import baz.syntax.analysis.*;
import baz.syntax.node.*;

public enum Type {
    BOOL,
    INT,
    STRING,
    CLOSURE,
    VOID;

    public static Type get(
            PType type) {

        class Result {

            Type type;
        }
        final Result result = new Result();

        type.apply(new DepthFirstAdapter() {

            @Override
            public void caseABoolType(
                    ABoolType node) {

                result.type = BOOL;
            }

            @Override
            public void caseAIntType(
                    AIntType node) {

                result.type = INT;
            }

            @Override
            public void caseAStringType(
                    AStringType node) {

                result.type = STRING;
            }
            
            @Override
            public void caseAClosureType(
                    AClosureType node) {

                result.type = CLOSURE;
            }
        });

        return result.type;
    }

    @Override
    public String toString() {

        switch (this) {
        case BOOL:
            return "bool";
        case INT:
            return "int";
        case STRING:
            return "string";
        case CLOSURE:
            return "void";
        case VOID:
            return "void";
        default:
            throw new RuntimeException("unhandled case");
        }
    }
}
