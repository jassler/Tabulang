package de.hskempten.tabulang.utils;


/**
 * Use this class to concatenate two Objects.
 * A concated Object can be used to construct more
 * complex data types such as lists and trees.
 * This class contains some convenience methods
 * for standard implementations of such data types.
 * <p>
 * In the documentation of this data type, we describe
 * a Pair Object as follows (informal definition):
 * ( A , B )
 * So, a parenthesis stands for a Concatenated, A and B
 * stand for the Objects contained in the Concatenated.
 */
public class Pair<F, L> {

// -------------------- Fields ----------------------------

    private F first;
    private L last;


// -------------------- Constructors ----------------------

    /**
     * Constructs a concated object from the given ones.
     */
    public Pair(F first, L last) {
        this.first = first;
        this.last = last;
    }


// -------------------- Accessors -------------------------

    /**
     * Returns the first object of the concated one.
     */
    public F getFirst() {
        return first;
    }

    /**
     * Sets the first object to the given one.
     */
    public void setFirst(F o) {
        first = o;
    }

    /**
     * Returns the last object of the concated one.
     */
    public L getLast() {
        return last;
    }

    /**
     * Sets the last object to the given one.
     */
    public void setLast(L o) {
        last = o;
    }

    /**
     * This method assumes, that this Pair is the head
     * of a list which is constructed as the following
     * expression hints:
     * ( Object[0], ( Object[1], ( Object[2], ... ) ) )
     * Here, the numbers of the objects are given in
     * square brackets. The method returns the object
     * with the given number.
     * The last element of the list can be recognized
     * by a null Object in the last component.
     * If there is occurs a Pair in the list, such that
     * its last component is not a Pair, an exception
     * is thrown.
     */
    public Object getListElementNr(int nr)
            throws Exception {
        if (nr == 0) return first;
        else {
            if (last instanceof Pair) {
                if (last != null) {
                    return ((Pair) last).getListElementNr(nr - 1);
                } else throw new Exception("List too short for index.");
            } else throw new Exception("Last element is not of class Pair.");
        }
    }

    /**
     * This method assumes, that this Pair is the head
     * of a tree which is constructed as the following
     * expression hints:
     * ( Object , ( leftSubtree, rightSubtree ) )
     * Where both subtrees are constructed analogously.
     * If a node has no right (left) subtree, the respective
     * element is empty.
     * <p>
     * The path has the following form:
     * a1/a2/.../an
     * where n is the length of the path, ai is from {0,1}
     * for 1 <= i <= n.
     * Semantics: The method walks through the tree and chooses
     * the first element for a 0 and the second element for
     * a 1.
     * <p>
     * If an Object is encountered which does not conform to
     * the above tree description, an Exception is thrown.
     */
    public Object getTreeElement(String path)
            throws Exception {
        String turn = StringUtils.getNextField(path, "/");
        if (turn.equals("")) return first;
        else {
            if (last instanceof Pair) {
                if (last != null) {
                    return ((Pair) last).getTreeElement(StringUtils.eatNextField(path, "/"));
                } else throw new Exception("Tree depth is to small for given path");
            } else throw new Exception("Wrong class in last component.");
        }
    }

    @Override
    public int hashCode() {
        // overflows are ok
        return first.hashCode() + last.hashCode();
    }

// ------------------ Mutators ------------------------------

    /**
     * Returns true if and only if the given other object is a Pair and
     * the first element of the other pair is equal to the first object
     * of this pair and the second element of the other pair is equal to the second object
     * of this pair.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other instanceof Pair) {
            Pair op = (Pair) other;
            return ((op.first.equals(first)) && (op.last.equals(last)));
        } else return false;
    }

    public String toString() {
        return "(" + first + ", " + last + ")";
    }


}
