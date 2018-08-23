package com.github.irimiadragos.tests.jmh;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.github.irimiadragos.tests.WordParser;
import com.github.irimiadragos.tests.WordParserException;
import com.github.irimiadragos.tests.WordParserLoader;

public class WordsParserBenchmark {

    @Benchmark
    public void testLoadMethod(Blackhole bh)  throws WordParserException{
        WordParser parser = WordParserLoader.instance().getParser();
        bh.consume(parser.parseWords(new ByteArrayInputStream("a aa aaa aaaa aa-aa aa\na aa aaa aaaa aa-aa aa".getBytes())));
    }

    @Benchmark
    public void testSortdMethod(Blackhole bh)  throws WordParserException{
        WordParser parser = WordParserLoader.instance().getParser();
        Map<String, Long> words = new HashMap<String, Long>();
        words.put("1a", 10L);
        words.put("2b", 20L);
        words.put("3c", 15L);
        words.put("4c", 13L);
        words.put("5d", 17L);
        words.put("6e", 11L);
        words.put("7f", 19L);
        words.put("8g", 22L);
        words.put("9h", 14L);
        words.put("10i", 12L);
        words.put("11j", 16L);
        bh.consume(parser.sort(words, 5));
    }
    
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(WordsParserBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
