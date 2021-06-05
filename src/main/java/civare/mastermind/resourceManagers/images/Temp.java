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

	@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME)
	public @interface DbId {
		int value();
	}


	static final class Helper extends ClassValue<Map<Object,Object>> {
		static final Helper INSTANCE = new Helper();

		@Override protected Map<Object, Object> computeValue(Class<?> type) {
			Map<Object,Object> m = new HashMap<>();
			for(Field f: type.getDeclaredFields()) {
				if(f.isEnumConstant()) try {
					Object constant = f.get(null);
					Integer id = f.getAnnotation(DbId.class).value();
					m.put(id, constant);
					m.put(constant, id);
				}
				catch(IllegalAccessException ex) {
					throw new IllegalStateException(ex);
				}
			}
			return Collections.unmodifiableMap(m);
		}
	}
	public interface Common {
		String name();
		Class<? extends Enum<?>> getDeclaringClass();
		default int toDB() {
			return (Integer)Helper.INSTANCE.get(getDeclaringClass()).get(this);
		}

		default String getPath() {
//			System.out.println(getClass());
//			System.out.println(getDeclaringClass());
//			System.out.println(getClass().getSimpleName());
//			System.out.println(getDeclaringClass().getEnclosingClass());
			System.out.println(getDeclaringClass().getSimpleName());
			return
					"/images/" +


					getDeclaringClass().getSimpleName().toLowerCase() +
							"/" +


							name().toLowerCase() + ".png";

		}

		static <T extends Enum<T>&Common> T fromDB(Class<T> type, int id) {
			return type.cast(Helper.INSTANCE.get(type).get(id));
		}
	}

	public enum Button implements Common {
		@DbId(121) DEFEAT,
		@DbId(207) PLAY_AGAIN,
		@DbId(314) VICTORY,

		;

//		URL getURI() {
//			return Objects.requireNonNull(Main.class.getResource(
//					Common.fromDB(Button.class, this.toDB()).getPath()));
//		}

		public static void main(String[] args) {
//			int id = Button.PLAY_AGAIN.toDB();
//			System.out.println("id = " + id);
			System.out.println(DEFEAT.getPath());
//			System.out.println(DEFEAT.getURI());

//			Button d = Common.fromDB(Button.class, id);
//			System.out.println("text = " + d.getPath());
		}
	}

	public enum TileImage implements Common {
		@DbId(121) T_1,
		@DbId(207) T_2,
		@DbId(314) T_3,
		@DbId(315) T_4;

		public static void main(String[] args) {
			int id = TileImage.T_1.toDB();
			System.out.println("id = " + id);
			TileImage d = Common.fromDB(TileImage.class, id);
			System.out.println("text = " + d.getPath());
		}
	}
}
