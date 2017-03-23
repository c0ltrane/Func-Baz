
package baz;

import java.util.*;

import baz.syntax.analysis.*;
import baz.syntax.node.*;

public class TreeVisualizer
        extends DepthFirstAdapter {

    private HashMap<Node, Integer> ids = new HashMap<Node, Integer>();

    int nextID = 1;

    @Override
    public void inStart(
            Start node) {

        System.out.println("graph G {");
        defaultIn(node);
    }

    @Override
    public void outStart(
            Start node) {

        defaultOut(node);
        System.out.println("}");
    }

    @Override
    public void defaultIn(
            Node node) {

        int id = this.nextID++;
        this.ids.put(node, id);
        System.out.println(" " + id + " [label=\""
                + node.getClass().getSimpleName() + "\"];");

        Node parent = node.parent();
        if (parent != null) {
            System.out.println(" " + this.ids.get(parent) + " -- " + id + ";");
        }
    }

    @Override
    public void defaultCase(
            Node node) {

        if (node instanceof Token) {
            Token token = (Token) node;

            int id = this.nextID++;
            this.ids.put(token, id);
            System.out.println(" " + id + " [label=\""
                    + token.getClass().getSimpleName() + ": "
                    + token.getText().replaceAll("\"", "\\\\\"") + "\"];");

            Node parent = node.parent();
            System.out.println(" " + this.ids.get(parent) + " -- " + id + ";");
        }
    }

}
