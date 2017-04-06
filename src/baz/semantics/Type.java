
package baz.semantics;

import baz.syntax.analysis.*;
import baz.syntax.node.*;

public enum Type {
    BOOL,
    INT,
    STRING,
    ANON,
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
            public void caseAAnonType(
                    AAnonType node) {

                result.type = ANON;
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
        case ANON:
            return "anon";
        case VOID:
            return "void";
        default:
            throw new RuntimeException("unhandled case");
        }
    }
}
