package civare.mastermind.resourceManagers.images;

import civare.mastermind.Main;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;

public interface Pathable {

	String name = null;

	public default String getName(String name) {
		return name;
	}

	public static String getPath(String path) {
		return path;
	}

	public default String gethalfpa() {
		String location = Config.getBaseFolder() +
				getClass().getSimpleName().toLowerCase() +
				Config.getBackslash();

//		+
//				this.name +
//				Config.getDOT() +
//				Config.getImagesFormatName();

		return location;
//		return Objects.requireNonNull(Main.class.getResource(location));
	}


}
