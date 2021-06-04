package civare.mastermind.resourceManagers.constants;

//  TODO auto save setting for window size and location
//  TODO mute sound option


import civare.mastermind.Main;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;

// todo what if default settings file is deleted

// todo handle restart settings with thread wait differently
public class ConstantsManager {

	public static void main(String[] args) {
		//        todo alt images

        /*
        check if assets file is present
            file is not present
				create file

			check if assets are present in /resources
				files are not present in assets
					create default assets in /resources

         */


		try {
			LinkedHashMap<Integer, String> errorLog = ConstantsManager.initializeConstants();

		} catch (IllegalArgumentException e) {
//			file not found
			System.out.println(e.getMessage());

		}

		System.out.println("main");
		ConstantsManager.printAll();

//        ConstantsManager.updateConstants(Config.getDefaultConstantsMemoryPath());

//		ConstantsManager.restartConstants();
//
//		ConstantsManager.printAll();

	}

	private static String getFileFromResourceAsString(String fileName) {

		// The class loader that loaded the class
		ClassLoader classLoader = Main.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);

		// the stream holding the file content
		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			return String.valueOf(inputStream);
		}

	}

	//	todo add before everything
	private static void checkFolder() {
		//        create config folder if missing
		File f = new File(Config.getConstantsFolder());
		boolean isFolderCreated = f.mkdir();
		System.out.println("was assets folder present: " + !isFolderCreated);
	}

	/**
	 * checks if assets folder is present, creates folder if not present
	 * checks if constants assets file is present
	 * if file is not present initializes constants to init value
	 * <p>
	 * <p>
	 * <p>
	 * reads from {@code Config.getDefaultConstantsMemoryPath()}
	 * sets constants values to values from constants instead of defined in this
	 * <p>
	 * use this at the start of program to get latest constants
	 * <p>
	 * handling errors:
	 * if default_constants.txt is modified there is rollback mechanism
	 * returns list of constants that could not be initialized from default_constants.txt
	 * <p>
	 * if file is empty:
	 * use constants defined in this file
	 * <p>
	 * if file contains modified line (two words, first word is not constant):
	 * this constant uses preassigned value from this file
	 * <p>
	 * if file contains modified line (two words, second word wrong type):
	 * this constant uses preassigned value from this file
	 * <p>
	 * if file contains modified line (two words, first word is not constant, second word wrong type):
	 * this constant uses preassigned value from this file
	 * <p>
	 * if file contains modified line (no words (empty line), one word, multiple words):
	 * this constant uses preassigned value from this file
	 */
	public static LinkedHashMap<Integer, String> initializeConstants() {
		System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

		checkFolder();

//		check file
		File configFile = new File(Config.getDefaultConstantsMemoryPath());
		System.out.println("is config file present: " + configFile.exists());


//		default values, hardcoded in source
//		used if default constants file is not present
		ArrayList<Constant> bu_constants = new ArrayList<>(EnumSet.allOf(Constant.class));

//		check if file is present
//		use default constants if file is not present
//		create file and write to it
		if (!Paths.get(Config.getDefaultConstantsMemoryPath()).toFile().exists()) {
			System.out.println("file not found: " + Config.getDefaultConstantsMemoryPath());

			for (Constant constant : EnumSet.allOf(Constant.class)) {
				constant = bu_constants.remove(0);
			}

			updateConstants(Config.getDefaultConstantsMemoryPath());
			return new LinkedHashMap<>();
		}


		LinkedHashMap<Integer, String> error_log = new LinkedHashMap<>();

		try (FileReader fr = new FileReader(Config.getDefaultConstantsMemoryPath());
			 BufferedReader bw = new BufferedReader(fr)) {

			String line;

//            which token is being processed
			int index = 0;
			for (Constant constant : EnumSet.allOf(Constant.class)) {

				/*

                  default_constants.txt contains less lines than sum of constants is

                  breaks assignment
                  constants use predefined values

                 */

				if ((line = bw.readLine()) == null) {
					error_log.put(index, "not enough lines from this line");
					break;
				}

				index++;

				if ((line.split(" ")).length != 2) {

					if (line.equals("")) {
						error_log.put(index, "empty line");
					} else {
						if ((line.split(" "))[0].equals(constant.getId())) {

							if (line.split(" ").length > 2) {

								handleValue(error_log, line, index, constant, "too much tokens");

							} else {
								error_log.put(index, "not enough tokens");
							}

						} else {
							error_log.put(index, "id mismatch");
						}
					}

				} else {

					handleValue(error_log, line, index, constant, "");

				}

			}


		} catch (IOException e) {
//			System.out.println("IOException");
			System.out.println(e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
//			System.out.println("Exception");
			System.out.println(e);
			System.out.println(e.getMessage());
			System.out.println();
			printAll();

			for (Constant constant : EnumSet.allOf(Constant.class)) {
				constant = bu_constants.remove(0);
			}

			printAll();

		}

		System.out.println("error log");
		error_log.forEach((key, value) -> System.out.println(key + ":" + value));
		System.out.println();

		return error_log;
	}


	/**
	 * Assigns new value to constants if that can be done.
	 * If that can not be done; appends new error to log and does not change value (it uses predefined value).
	 *
	 * @param error_log          (k, v) -> (index, error message)
	 * @param line               line in .txt
	 * @param index              index of constant
	 * @param constant           current constant that we try to assign new value
	 * @param errorMessagePrefix if line in .txt contains more than 2 tokens append message, else ""
	 */
	private static void handleValue(LinkedHashMap<Integer, String> error_log, String line,
									int index, Constant constant, String errorMessagePrefix) {

		Object value = (line.split(" "))[1];

		if (constant.getValue() instanceof Integer) {
			if (value.toString().matches("[0-9][0-9]*")) {
				constant.setValue(Integer.parseInt(String.valueOf(value)));

				if (!errorMessagePrefix.equals("")) {
					error_log.put(index, errorMessagePrefix);
				}

			} else {
				error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
						"error with integer " + value);
			}
		} else if (constant.getValue() instanceof Double) {
			if (value.toString().matches("[0-9]+\\.[0-9]+")) {
				constant.setValue(Double.parseDouble(String.valueOf(value)));
				if (!errorMessagePrefix.equals("")) {
					error_log.put(index, errorMessagePrefix);
				}

			} else {
				error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
						"error with double " + value);
			}
		} else if (constant.getValue() instanceof Boolean) {

			if (value.toString().equals("true") || value.toString().equals("false")) {
				constant.setValue(Boolean.parseBoolean(String.valueOf(value)));

				if (!errorMessagePrefix.equals("")) {
					error_log.put(index, errorMessagePrefix);
				}

			} else {
				error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
						"error with boolean " + value);
			}

		} else if (constant.getValue() instanceof String) {
			constant.setValue(value);

			if (!errorMessagePrefix.equals("")) {
				error_log.put(index, errorMessagePrefix);
			}

		} else {
			error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
					"error while paring value, constant.value error, " + value);
		}
	}

	/**
	 * prints all constants
	 */
	public static void printAll() {
		System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

		for (Constant constant : EnumSet.allOf(Constant.class)) {
			System.out.println(constant);
		}

		System.out.println();
	}

	/**
	 * writes to path
	 * writes constant values to default_constants.txt
	 * <p>
	 * use this at the end of program to save new settings
	 */
	public static void updateConstants(String path) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {

			for (Constant constant : EnumSet.allOf(Constant.class)) {
				out.println(constant.getId() + " " + constant.getValue());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * sets to default constants
	 */
	public static void restartConstants() {
		Constant.restartAllToDefaultValues();

		updateConstants(Config.getDefaultConstantsMemoryPath());

	}

}
