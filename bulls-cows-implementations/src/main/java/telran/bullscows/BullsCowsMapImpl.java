package telran.bullscows;

import java.util.HashMap;

import java.util.List;


public class BullsCowsMapImpl implements BullsCowsService {
	static HashMap<Long, Game> games = new HashMap<>();

	@Override
	public long createNewGame() {
		Game game = new Game();
		games.put(game.id, game);
		return game.id;
		
	}

	@Override
	public List<MoveResult> getMoveResults(long gameId, Move move) {
		Game game = games.get(gameId);
		game.moveProcess(move);
		return game.getResults();
	}

	@Override
	public boolean isGameOver(long gameId) {
		return games.get(gameId).isGameOver();
	}

}
