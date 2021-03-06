package civare.mastermind.resourceManagers.sounds;

import civare.mastermind.Main;
import civare.mastermind.resourceManagers.constants.Constant;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class SoundsManager {

	public static void main(String[] args) {
		new Thread(() -> playClip(Sound.getRandomSoundDifferentFromCurrent())).start();
	}

	public static void playGameOverSound() {

		System.out.println("trying to play game over sound");

		if ((boolean) Constant.IS_SOUND_ACTIVE.getValue()) {
			new Thread(() -> playClip(Sound.getRandomSoundDifferentFromCurrent())).start();

			System.out.println("sound played");
		} else {
			System.out.println("sound not active");
		}

	}

	public static void playVictorySound() {
		System.out.println("TODO victory sound");
	}

	private static void playClip(Sound sound) {

		AudioListener listener = new AudioListener();
		System.out.println(sound.getReducedDefaultPath());

		System.out.println(Files.exists(Paths.get(sound.getReducedDefaultPath())));
//		System.out.println(Files.exists(Paths.get("/resources/sounds/")));

		try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
				Objects.requireNonNull(Main.class.getResource(sound.getReducedDefaultPath())))) {

			Clip clip = AudioSystem.getClip();
			clip.addLineListener(listener);
			clip.open(audioInputStream);
			clip.start();
			listener.waitUntilDone();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getMessage());
			System.out.println("error while playing sound");
		}


	}

	private static class AudioListener implements LineListener {
		private boolean done = false;

		@Override
		public synchronized void update(LineEvent event) {
			LineEvent.Type eventType = event.getType();
			if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
				done = true;
				notifyAll();
			}
		}

		public synchronized void waitUntilDone() {
			try {
//              TODO check if this is busy waiting
				while (!done) {
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
