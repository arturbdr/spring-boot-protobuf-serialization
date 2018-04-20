package com.benchmark.jackson;

import com.benchmark.All;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class DeserializeJackson {

    private ObjectMapper objectMapper;
    private TypeReference<TestObject> typeReference;
    private byte[] testJSON;

    @Setup(Level.Trial)
    public void benchSetup(BenchmarkParams params) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new AfterburnerModule());
        typeReference = new TypeReference<TestObject>() {
        };
        testJSON = TestObject.createTestJSON();
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void deser(Blackhole bh) throws IOException {
        for (int i = 0; i < 1000; i++) {
            bh.consume(objectMapper.readValue(testJSON, typeReference));
        }
    }

    public static void main(String[] args) throws IOException, RunnerException {
        All.loadJMH();
        Main.main(new String[]{
                "com.benchmark.jackson.DeserializeJackson",
                "-i", "5",
                "-wi", "5",
                "-f", "1",
        });
    }
}
