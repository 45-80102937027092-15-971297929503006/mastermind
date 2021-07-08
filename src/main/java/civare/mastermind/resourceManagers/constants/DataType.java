
package civare.mastermind.resourceManagers.constants;

public enum DataType {
	INTEGER,
	DOUBLE,
	BOOLEAN,
	STRING,

	OTHER;

	public static DataType getType(Object value) {

		if (value instanceof Integer) {
			return INTEGER;
		} else if (value instanceof Double) {
			return DOUBLE;
		} else if (value instanceof Boolean) {
			return BOOLEAN;
		} else if (value instanceof String) {
			return STRING;
		} else {
			System.out.println("type not compatible");
			return OTHER;
		}
	}

	public static boolean isPassableType(Object value) {
		return getType(value) != null;
	}

	public static void validateType(Object value) {
		if (! isPassableType(value)) {
			System.out.println("data validation failed for: " + value);
			System.exit(-1);
		}
	}
}
