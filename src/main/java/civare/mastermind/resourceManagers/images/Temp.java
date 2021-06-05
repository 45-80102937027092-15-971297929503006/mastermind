package civare.mastermind.resourceManagers.images;

import civare.mastermind.Main;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Temp {

	public interface Common {
		String name();
		Class<? extends Enum<?>> getDeclaringClass();

		default String getPath() {
			return
				Config.getBaseFolder() +
				getDeclaringClass().getSimpleName().toLowerCase() +
				"/" +
				name().toLowerCase() + ".png";

		}

	}

	public enum Button implements Common {
		 DEFEAT,
		 PLAY_AGAIN,
		 VICTORY,

		;

		public static void main(String[] args) {
			System.out.println(DEFEAT.getPath());
		}
	}

	public enum TileImage implements Common {
		 T_1,
		 T_2,
		 T_3,
		 T_4;

		public static void main(String[] args) {

			System.out.println(T_1.getPath());

		}
	}
}
