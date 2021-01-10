package benchmarks;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@State(Scope.Benchmark)
public class DataPresets {

    public InternalNumber[] strArray = IntStream.range(0, 1000).mapToObj(InternalNumber::new).toArray(InternalNumber[]::new);
    public InternalString[] strArrayHeader = Stream.of(strArray).map(i -> new InternalString("V" + i.getValue())).toArray(InternalString[]::new);

    public List<InternalNumber> strList = Arrays.asList(strArray);
    public List<InternalString> strListHeader = Arrays.asList(strArrayHeader);


}
