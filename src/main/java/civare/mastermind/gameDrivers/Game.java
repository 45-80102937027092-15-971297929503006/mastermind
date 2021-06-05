package civare.mastermind.gameDrivers;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private final int codeLength = 4; // 4, 6 or 8
	private final int numOfRows = 10; // 6, 8, 10 or 12
	private final int numOfColors = 6;
	private final List<Object> finalState;

	public Game() {
//        init game

		// generate random final state of the board
		this.finalState = new ArrayList<>();
		for (int i = 0; i < codeLength; i++) {
			this.finalState.add((Math.random() * 100) % numOfColors);
		}

		//
	}

	public static void main(String[] args) {

	}


}
