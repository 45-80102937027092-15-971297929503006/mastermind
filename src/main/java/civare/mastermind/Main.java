package civare.mastermind;

import civare.mastermind.resourceManagers.constants.Config;
import civare.mastermind.windows.index.MainFrame;

import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
//        todo alt images

        /*
        check if assets file is present
            file is present
                continue
            file is not present
                check if assets are present in /resources
                    files are not present in assets
                        create default assets in /resources

                copy from /resources to /game_assets
         */


//        create config folder if missing
        File f = new File(Config.getConstantsFolder());
        System.out.println("folder created ? " + f.mkdir());

        SwingUtilities.invokeLater(MainFrame::new);
    }

//    TODO extract image loading to separate thread
//    TODO gui style
//    TODO change logs to json instead of ad-hoc solution

    //    FIXME
//          start/ stop button not working
//          when pressed "restart all settings" images in settings window are not restarted automatically
//          fix position on screen of mainframe and settingswindow
//          when index window is moved and settings window is opened; make sure that new window opens at same location
//          fix algorithm for game
//              first cell that is clicked must not be mine
//              check that game is solvable without brute fore
//              first cell opened must be zero
//                  dont generate any mine around that tile
//          error while changing dimensions
//          statistics graph


}
