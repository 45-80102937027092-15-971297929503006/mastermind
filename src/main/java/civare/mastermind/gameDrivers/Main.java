package civare.mastermind.gameDrivers;

import java.util.List;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {

	}

	/**
	 * @return true if user_1 won else false
	 * TODO move finalState to somewhere else
	 */
	public boolean checkGameState(List<Object> pegs, List<Object> finalState) {
		/*
		 *  2 - in the right place
		 *  1 - right but in the wrong place
		 *  0 - wrong
		 * */
		TreeSet<Integer> pegInfo = new TreeSet<>(); // Set beacuse it should be sorted when displayed
		for (int i = 0; i < pegs.size(); i++) {
			if (pegs.get(i) == finalState.get(i)) {
				// its in the right spot
				pegInfo.add(2);
			} else if (finalState.contains(pegs.get(i))) { // TODO complexity! Set.contains() = O(1)
				// its right but in the wrong spot
				pegInfo.add(1);
			} else {
				// its wrong
				pegInfo.add(0);
			}
		}
		// TODO pegInfo has to be displayed

		return !(pegInfo.contains(0) || pegInfo.contains(2));
	}

//    todo return num of correct positions and colors

}
