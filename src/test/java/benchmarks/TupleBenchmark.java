package benchmarks;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Tuple;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class TupleBenchmark {

    @Benchmark
    public void constructWithArray(Blackhole b, DataPresets data) {
        b.consume(new Tuple<>(data.strArray));
    }

    @Benchmark
    public void constructWithArrayAndHeader(Blackhole b, DataPresets data) {
        b.consume(new Tuple<>(data.strArray, data.strArrayHeader, true));
    }

    @Benchmark
    public void constructWithList(Blackhole b, DataPresets data) {
        b.consume(new Tuple<>(data.strList));
    }

    @Benchmark
    public void constructWithListAndHeader(Blackhole b, DataPresets data) {
        b.consume(new Tuple<>(data.strList, data.strListHeader, true, null));
    }
}
