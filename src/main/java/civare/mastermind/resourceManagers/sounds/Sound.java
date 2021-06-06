package civare.mastermind.resourceManagers.sounds;

//    TODO ability to add new sounds

import civare.mastermind.Main;
import civare.mastermind.resourceManagers.images.Config;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public enum Sound {

	SOUND_PATH_0("j2"),
	SOUND_PATH_1("j3"),
	SOUND_PATH_2("i");


	private static final List<Sound> VALUES = Arrays.asList(values());
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	private static final String separator = "/";
	private static final String REDUCED_DEFAULT_PATH =
			separator + "sounds" + separator + "defeat_sounds" + separator;
	//                    "sounds" +
//                    separator +
//                    "defeat_sounds" +
//                    separator;
	private static final String EXTENSION = ".wav";

	private static Sound currentSound = getRandomSound();

	private final String path;

	Sound(String path) {
		this.path = path;
	}

	public URL getLocation() {

		String location =
//                          images folder
//				Config.getBasePath() +

				separator + "sounds"+
//                                type folder
						Config.getBackslash() +
						getDeclaringClass().getSimpleName().toLowerCase() +

						Config.getBackslash() +
//                                image
						name().toLowerCase() +
						Config.getDOT() +
						Config.getImagesFormatName();

//		System.out.println("location: " + location);

		return Objects.requireNonNull(Main.class.getResource(location));
	}

	static Sound getRandomSoundDifferentFromCurrent() {
		Sound next = getRandomSound();

		while (next == currentSound) {
			next = getRandomSound();
		}

		currentSound = next;
		return next;
	}

	private static Sound getRandomSound() {
		return VALUES.get(new Random().nextInt(SIZE));
	}

	String getReducedDefaultPath() {
		return REDUCED_DEFAULT_PATH + SOUND_PATH_0.path + EXTENSION;
	}

}
