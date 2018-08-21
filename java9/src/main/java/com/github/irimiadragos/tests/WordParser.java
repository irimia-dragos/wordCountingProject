package com.github.irimiadragos.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordParser {
	public WordParser() {

	}

	public void printResult(String file) {
		try (InputStream input = new FileInputStream(file)) {
			System.out.println(sort(loadWords(input)));
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Long> loadWords(InputStream stream) {
		try (Scanner input = new Scanner(stream)) {
			return input.findAll(Pattern.compile("[a-zA-Z\\'\\-]+")).
					map(MatchResult::group).map(String::toLowerCase).
					collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		}
	}


	public Map<String, Long> sort(Map<String, Long> map) {
		return map.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(10)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
