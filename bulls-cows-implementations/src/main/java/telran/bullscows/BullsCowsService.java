package telran.bullscows;

import java.util.List;

public interface BullsCowsService {
public long createNewGame();
List<MoveResult> getMoveResults(long gameId, Move move);
boolean isGameOver(long gameId);
}
