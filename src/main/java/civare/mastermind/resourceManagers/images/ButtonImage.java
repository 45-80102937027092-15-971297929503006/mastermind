package civare.mastermind.resourceManagers.images;

import civare.mastermind.Main;

import java.net.URL;
import java.util.Objects;

public enum ButtonImage implements Pathable {
	VICTORY,
	DEFEAT,
	PLAY_AGAIN,
	;


	public static void main(String[] args) {
		System.out.println(VICTORY);
		System.out.println(VICTORY.name);
		System.out.println(VICTORY.getLocation());
//		System.out.println(VICTORY.getLocation());

	}

	private static final String path = Config.getButtonPath();
	private final String name;
//	private final String location;

	ButtonImage() {
		this.name = this.name().toLowerCase();
//		this.location =
//				Config.getBaseFolder() +
//						getClass().getSimpleName().toLowerCase() +
//						Config.getBackslash() +
//						name +
//						Config.getDOT() +
//						Config.getImagesFormatName();

//		System.out.println(name + " - " + this.getLocation());
	}

//	@Override
	public URL getLocation() {
		String location =  Pathable.super.gethalfpa()
				+  this.name +
				Config.getDOT() +
				Config.getImagesFormatName();

		System.out.println(location);
		return Objects.requireNonNull(Main.class.getResource(location));

	}
}
