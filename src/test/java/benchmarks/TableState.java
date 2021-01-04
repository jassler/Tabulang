package benchmarks;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Tuple;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class TableState {

    private Tuple<InternalString>[] fewTupleElements;
    private Tuple<InternalString>[] manyTupleElements;

    @Setup(Level.Invocation)
    public void setup() {
        fewTupleElements = new Tuple[10_000];
        for(int i = 0; i < fewTupleElements.length; i++)
            fewTupleElements[i] = new Tuple<>(InternalString.objToArray("This", "Tuple", "Has", "A", "Few", "Elements", "in", "it"));
    }
}
