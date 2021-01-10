package benchmarks;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(TupleBenchmark.class.getSimpleName())
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MILLISECONDS)
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(options).run();
    }

}
