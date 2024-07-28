package telran.bullscows;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class BullsCowsMapImplTest {
long id;
BullsCowsMapImpl bcmi = new BullsCowsMapImpl();

	@Test
	void createNewGameTest() {
		 id = bcmi.createNewGame();
		assertTrue(id>0);
		String moveStr = "1245";
		List<Integer> list = moveStr.chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
		Map<Integer, Integer> map = IntStream.range(0, 4).boxed()
				.collect(Collectors.toMap(index -> index, list::get));
		
		System.out.println(bcmi.getMoveResults(id, new Move(id, moveStr)));
	}

	

}
