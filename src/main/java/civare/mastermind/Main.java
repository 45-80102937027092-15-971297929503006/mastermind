package civare.mastermind;

import civare.mastermind.windows.index.MainFrame;

import javax.swing.*;

public class Main {
//    Path path = Paths.get("foo", "bar", "baz.txt");

	public static void main(String[] args) {
//
//
//
////        todo alt images
//
//        /*
//        check if assets file is present
//            file is present
//                continue
//            file is not present
//                check if assets are present in /resources
//                    files are not present in assets
//                        create default assets in /resources
//
//                copy from /resources to /game_assets
//         */
//
//
////        create config folder if missing
//        File f = new File(Config.getConstantsFolder());
//        System.out.println("folder created ? " + f.mkdir());
//
		SwingUtilities.invokeLater(MainFrame::new);

//        JFrame f = new JFrame();
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Since I'm not setting a layout manager to contentPane, it's default (BorderLayout) is used
////            String p = "/images/resized_images/opened_tiles/-1.png";
//        //This sets the image in JFrame's content area
////            f.getContentPane().add(new JLabel(new ImageIcon(p)));
//
//        JButton button = new JButton("faakflfkl");
//        try {
//
//            button.setIcon(new ImageIcon(Main.class.getResource("/errors.png")));
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//            System.out.println(ex.getMessage());
//        }
//
//        button.addActionListener(e -> {
//            System.out.println("pressed");
//        });
//        f.add(button);
////        URL urlConfig = MyClass.class.getResource("/settings/config.ini"); //by default "src/main/resources/" is in classpath and no config needs to be changed.
////        InputStream inputAvatar = MyClass.class.getResourceAsStream("/myAvatar.gif"); //with changes in pom.xml now "src/main/images" is counted as resource folder, and added to classpath. So we use it directly.
//        //
////        BufferedImage img = ImageIO.read(Image.class.getResource("com/minesweeper/resources/images/resized_images/opened_tiles/-1.png"));
////            f.add(new JButton(new ImageIcon(String.valueOf(img))));
//
//
//        //This sets JFrame's icon (shown in top left corner of JFrame)
////            f.setIconImage(new ImageIcon("com/minesweeper/resources/images/resized_images/opened_tiles/-1.png").getImage());
//
//        f.setBounds(300, 200, 400, 300);
//        f.setVisible(true);
//    }

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
}
