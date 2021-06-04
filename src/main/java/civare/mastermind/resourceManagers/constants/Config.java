package civare.mastermind.resourceManagers.constants;

import java.io.File;

public class Config {

    private static final String CONSTANTS_FOLDER = "/mastermind_assets";

//    private static final String CONSTANTS_MEMORY_PATH =
//            String.valueOf(new File(CONSTANTS_FOLDER, "/custom_constants.txt"));

    private static final String DEFAULT_CONSTANTS_MEMORY_PATH =
            String.valueOf(new File(CONSTANTS_FOLDER, "/default_constants.txt"));

//    public static String getConstantsMemoryPath() {
//        return CONSTANTS_MEMORY_PATH;
//    }

    public static String getConstantsFolder() {
        return CONSTANTS_FOLDER;
    }

    public static String getDefaultConstantsMemoryPath() {
        return DEFAULT_CONSTANTS_MEMORY_PATH;
    }
}
