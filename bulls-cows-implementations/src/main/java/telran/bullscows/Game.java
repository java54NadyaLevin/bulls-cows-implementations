package telran.bullscows;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
	long id;
	String sequence;
	boolean isGameOver;
	LocalDateTime start;
	LocalDateTime finish;
	List<MoveResult> results;
	private static final int NUMBER_OF_DIGITS = 4;
	int cows;
	int bulls;

	public Game() {
		this.results = new ArrayList<>();
		this.start = LocalDateTime.now();
		this.id = Instant.now().toEpochMilli();
		this.sequence = new Random().ints(0, 9).distinct().limit(NUMBER_OF_DIGITS).boxed().map(String::valueOf)          // Convert each Integer to String
                .collect(Collectors.joining()); 
		this.isGameOver = false;
	}

	public long getId() {
		return id;
	}

	public String getSequence() {
		return sequence;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getFinish() {
		return finish;
	}

	public List<MoveResult> getResults() {
		return results;
	}

	public List<MoveResult> moveProcess(Move move) {
		String str = move.sequence();
		Map<Integer, Integer> map = convertStringToMap(str);
		map.entrySet().stream().forEach(e -> counter(convertStringToMap(sequence), e));
		MoveResult result = new MoveResult(str, bulls, cows);
		results.add(result);
		if (bulls == NUMBER_OF_DIGITS) {
			isGameOver = true;
			finish = LocalDateTime.now();
		}
		cleanCowsBulls();
		return results;
	}
	
	 public static Map<Integer, Integer> convertStringToMap(String str) {
	        return IntStream.range(0, NUMBER_OF_DIGITS) 
	                .boxed()
	                .collect(Collectors.toMap(
	                        index -> index, 
	                        index -> Character.getNumericValue(str.charAt(index)) 
	                ));
	 }

	private void cleanCowsBulls() {
		cows = 0;
		bulls = 0;
	}

	private void counter(Map<Integer, Integer> sequence, Entry<Integer, Integer> e) {
		if (sequence.containsValue(e.getValue())) {
			int value = sequence.get(e.getKey());
			if (value == e.getValue()) {
				bulls++;
			} else {
				cows++;
			}
		}
	}

}
