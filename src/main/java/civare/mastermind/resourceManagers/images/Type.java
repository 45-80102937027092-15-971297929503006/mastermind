package civare.mastermind.resourceManagers.images;

import civare.mastermind.resourceManagers.constants.Constant;

import java.util.EnumSet;
import java.util.Locale;

public enum Type {

	BUTTON,
	CLOSED_TILES,
	OPENED_TILES,
	PEGS,
	SETTINGS_CHECKER_FLAGS,
	STOP,
	TEMP,
	TIME,

	;

	private final String name;


	Type() {
		this.name = this.name().toLowerCase();
	}

	public String getFullPath() {
		return Config.getBackslash() + "images" + Config.getBackslash() + name + Config.getBackslash();
	}

	@Override
	public String toString() {
		return "Type{" +
				"name='" + name + '\'' +
				"full path= "+ getFullPath() +


				'}';
	}

	public static void main(String[] args) {

		for (Type  type : EnumSet.allOf(Type.class)) {
//			constant.value = constant.defaultValue;
//			System.out.println(type.name);
			System.out.println(type);
		}

	}

}
