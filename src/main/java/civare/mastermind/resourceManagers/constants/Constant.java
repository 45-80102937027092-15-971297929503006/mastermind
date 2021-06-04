package civare.mastermind.resourceManagers.constants;

import java.io.File;
import java.util.EnumSet;
import java.util.LinkedHashMap;

/**
 * jtext used for gui
 */
public enum Constant {
	//    NUMBER_OF_COLUMNS(10),
//    NUMBER_OF_ROWS(10),
//    NUMBER_OF_MINES(10),
	LOCATION_X(20),
	LOCATION_Y(20),
	WIDTH(550.0),
	HEIGHT(500.0),

	IS_VICTORY_SOUND_ACTIVE(true),
	IS_DEFEAT_SOUND_ACTIVE(true),
	IS_ANY_SOUND_ACTIVE(true),
	IS_SOUND_ACTIVE(true),

	NUMBER_OF_WINS(0),
	NUMBER_OF_LOSSES(0),

	LAST_LOGGED_DAY(ConstantCounterManager.initValue),

	CAN_BUTTONS_BE_ACTIVATED_WHILE_UNDER_FLAG_OR_UNKNOWN(false);

	private static final String initValue = ConstantCounterManager.initValue;
	private final String id;
	private Object value;
	private Object defaultValue;
//	private Object initValue;

	Constant(Object value) {
		this.id = this.name();
		this.value = value;
		this.defaultValue = value;
//		this.value = initValue;

		ConstantCounterManager.setNumOfConstants(ConstantCounterManager.getNumOfConstants() + 1);
	}

	public static void restartAllToDefaultValues() {

		for (Constant constant : EnumSet.allOf(Constant.class)) {
			constant.value = constant.defaultValue;
		}

	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public static int getNumOfConstants() {
		return ConstantCounterManager.getNumOfConstants();
	}

	public static String getInitValue() {
		return initValue;
	}

	public static void main(String[] args) {
//        todo alt images

        /*
        check if assets file is present
            file is not present
                check if assets are present in /resources
                    files are not present in assets
                        create default assets in /resources

                copy from /resources to /game_assets
         */


//        create config folder if missing
		File f = new File(Config.getConstantsFolder());
		boolean isFolderCreated = f.mkdir();
		System.out.println("was assets folder present: " + !isFolderCreated);

		File configFile = new File(Config.getDefaultConstantsMemoryPath());
		System.out.println("is config file present: " + configFile.exists());

		try {
			LinkedHashMap<Integer, String> errorLog = ConstantsManager.initializeConstants();

		} catch (IllegalArgumentException e) {
//			file not found
			System.out.println(e.getMessage());

		}

		System.out.println("main");
		ConstantsManager.printAll();

        ConstantsManager.updateConstants(Config.getDefaultConstantsMemoryPath());

//		ConstantsManager.restartConstants();
//
//		ConstantsManager.printAll();

	}

	@Override
	public String toString() {
		String type;

		if (value instanceof Integer) {
			type = "Integer";
		} else if (value instanceof Double) {
			type = "Double";
		} else if (value instanceof Boolean) {
			type = "Boolean";
		} else if (value instanceof String) {
			type = "String";
		} else {
			type = "error";
			System.exit(-1);
		}

		return "Constant{ id= " + id + ", value= " + value + ", type= " + type + "}";
	}

	public String getId() {
		return id;
	}

	public Object getValue() {

		if (value instanceof Integer) {
			return Integer.parseInt(String.valueOf(value));
		} else if (value instanceof Double) {
			return Double.parseDouble(String.valueOf(value));
		} else if (value instanceof Boolean) {
			return Boolean.parseBoolean(String.valueOf(value));
		} else if (value instanceof String) {
			return value;
		} else {
			System.out.println("error; getValue");
			System.exit(-1);
		}

		return value;
	}

	public void setValue(Object value) {

		if (this.value.getClass().equals(value.getClass())) {
			this.value = value;
		} else {
			System.out.println("objects not of same type");
			System.exit(-1);
		}
	}

	private static class ConstantCounterManager {
		private static final String initValue = "init value";

		private static int numOfConstants = 0;

		static int getNumOfConstants() {
			return numOfConstants;
		}

		static void setNumOfConstants(int numOfConstants) {
			ConstantCounterManager.numOfConstants = numOfConstants;
		}
	}

}
