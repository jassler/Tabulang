package benchmarks;

import de.hskempten.tabulang.datatypes.Tuple;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Arrays;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class TableState {

    private Tuple<String>[] fewTupleElements;
    private Tuple<String>[] manyTupleElements;

    @Setup(Level.Invocation)
    public void setup() {
        fewTupleElements = new Tuple[10_000];
        for(int i = 0; i < fewTupleElements.length; i++)
            fewTupleElements[i] = new Tuple<>(Arrays.asList("This", "Tuple", "Has", "A", "Few", "Elements", "in", "it"));

        manyTupleElements = new Tuple[10_000];
        for(int i = 0; i < manyTupleElements.length; i++)
            manyTupleElements[i] = new Tuple<>((String[]) IntStream.of(12,25,36,85,28,96,47).mapToObj(Integer::toString).toArray());
    }
}
