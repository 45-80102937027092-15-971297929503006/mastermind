package civare.mastermind.resourceManagers.constants;

import java.util.EnumSet;

/**
 * jtext used for gui
 */
public enum Constant {
	NUMBER_OF_COLUMNS(10),
    NUMBER_OF_ROWS(10),
    NUMBER_OF_MINES(10),
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
	private final Object defaultValue;
	private Object value;

	Constant(Object value) {
		this.id = this.name();
		this.value = value;
		this.defaultValue = value;

		ConstantCounterManager.setNumOfConstants(ConstantCounterManager.getNumOfConstants() + 1);
	}

	public static void restartAllToDefaultValues() {

		for (Constant constant : EnumSet.allOf(Constant.class)) {
			constant.value = constant.defaultValue;
		}

	}

	public static int getNumOfConstants() {
		return ConstantCounterManager.getNumOfConstants();
	}

	public static String getInitValue() {
		return initValue;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}



	@Override
	public String toString() {
		DataType.validateType(this.value);

		String type = DataType.getType(this.value).toString().toLowerCase();

		return "Constant{ id= " + id + ", value= " + value + ", type= " + type + "}";
	}

	public String getId() {
		return id;
	}

	public Object getValue() {

		DataType.validateType(this.value);

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
		private static final String initValue = "init_value";

		private static int numOfConstants = 0;

		static int getNumOfConstants() {
			return numOfConstants;
		}

		static void setNumOfConstants(int numOfConstants) {
			ConstantCounterManager.numOfConstants = numOfConstants;
		}
	}

}
