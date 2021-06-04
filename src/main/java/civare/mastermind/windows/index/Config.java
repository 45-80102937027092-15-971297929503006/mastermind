package civare.mastermind.windows.index;


import java.io.File;

/**
 * Used for code{ResultLogger}
 */
public class Config {

//    private static final String CONSTANTS_FOLDER = "/minesweeperConstants";

    private static final String RESULT_LOG_PATH =
            String.valueOf(new File(civare.mastermind.resourceManagers.constants.Config.getConstantsFolder(),
                    "/statistics.txt"));

    public static String getResultLogPath() {
        return RESULT_LOG_PATH;
    }
}
