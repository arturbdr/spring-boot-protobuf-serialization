package com.benchmark.protobuf;

import com.benchmark.All;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class DeserializeProtobuf {

    private List<byte[]> testData;
    private Random random;

    @Setup(Level.Trial)
    public void benchSetup(BenchmarkParams params) {
        random = new Random();
        testData = Arrays.asList(
                createData(),
                createData(),
                createData(),
                createData(),
                createData(),
                createData(),
                createData(),
                createData(),
                createData(),
                createData());
    }

    private byte[] createData() {
        return Pb.PbTestObject.newBuilder()
                .setField1(random.nextInt(61415923))
                .setField2(random.nextInt(61415923))
                .setField3(random.nextInt(61415923))
                .setField4(random.nextInt(61415923))
                .setField5(random.nextInt(61415923))
                .setField6(random.nextInt(61415923))
                .setField7(random.nextInt(61415923))
                .setField8(random.nextInt(61415923))
                .setField9(random.nextInt(61415923))
                .setField10(random.nextInt(61415923))
                .build().toByteArray();
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void deser(Blackhole bh) throws IOException {
        for (int i = 0; i < 1000; i++) {
            bh.consume(Pb.PbTestObject.parseFrom(testData.get(i % 10)));
        }
    }

    public static void main(String[] args) throws IOException, RunnerException {
        All.loadJMH();
        Main.main(new String[]{
                "com.benchmark.protobuf.DeserializeProtobuf",
                "-i", "5",
                "-wi", "5",
                "-f", "1",
        });
    }
}
