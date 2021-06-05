package civare.mastermind.resourceManagers.images;


import civare.mastermind.Main;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;

public interface Common {

	String name();

	Class<? extends Enum<?>> getDeclaringClass();

//	default String getPath() {
//		return
//				"/resources" +
//				Config.getBaseFolder() +
//						getDeclaringClass().getSimpleName().toLowerCase() +
//						"/" +
//						name().toLowerCase() +
//				Config.getDOT() +
//				Config.getImagesFormatName();
//
//	}

	default ImageIcon getImageIcon() {
		return new ImageIcon(getLocation());

	}

	default URL getLocation() {

		String location =
//                          images folder
				Config.getBasePath() +

//                                type folder
						Config.getBackslash() +
						getDeclaringClass().getSimpleName().toLowerCase() +

						Config.getBackslash() +
//                                image
						name().toLowerCase() +
						Config.getDOT() +
						Config.getImagesFormatName();

		System.out.println("location: " + location);

		return Objects.requireNonNull(Main.class.getResource(location));
	}

	default void flushToDefaultImage() {
//		todo
//		imageIcon = defaultImageIcon;
	}

//	constructor
//	try {
//			imageIcon = new ImageIcon(getLocation(path));
//			defaultImageIcon = new ImageIcon(getLocation(path));
//
//
//			//            Path p = Paths.get( "/resources", "images", "time", "0.png");
//
//		} catch (Exception e) {
//
//			System.out.println("error in Image.java while loading image " + path);
//
//			System.exit(-1);
//		}

}
