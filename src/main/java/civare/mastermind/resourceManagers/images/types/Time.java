package civare.mastermind.resourceManagers.images.types;

import civare.mastermind.resourceManagers.images.Common;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public enum Time implements Common {
	T_0,
	T_1,
	T_2,
	T_3,
	T_4,
	T_5,
	T_6,
	T_7,
	T_8,
	T_9,
	;

	static Map<Integer, ImageIcon> timeMap = new HashMap<>();

	 public static ImageIcon getLocationByIndex(int index) {
		return timeMap.get(index);
	}

	static {
		int num = 0;
		for (Time t : Time.values()) {
			timeMap.put(num++, t.getImageIcon());
		}
	}

	public static void main(String[] args) {
		System.out.println(timeMap.get(0));
	}

}
