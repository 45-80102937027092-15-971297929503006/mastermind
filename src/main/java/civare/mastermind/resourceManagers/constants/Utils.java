package civare.mastermind.resourceManagers.constants;

// Java program to copy content from
// one file to another

import civare.mastermind.Main;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Utils {
	// get a file from the resources folder
	// works everywhere, IDEA, unit test and JAR file.
	private static InputStream getFileFromResourceAsStream(String fileName) {

		// The class loader that loaded the class
		ClassLoader classLoader = Main.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);

		// the stream holding the file content
		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			return inputStream;
		}

	}

	public static class CopyFileVisitor extends SimpleFileVisitor<Path> {
		private final Path targetPath;
		private Path sourcePath = null;
		public CopyFileVisitor(Path targetPath) {
			this.targetPath = targetPath;
		}

		@Override
		public FileVisitResult preVisitDirectory(final Path dir,
												 final BasicFileAttributes attrs) throws IOException {
			if (sourcePath == null) {
				sourcePath = dir;
			} else {
				Files.createDirectories(targetPath.resolve(sourcePath
						.relativize(dir)));
			}
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(final Path file,
										 final BasicFileAttributes attrs) throws IOException {
			Files.copy(file,
					targetPath.resolve(sourcePath.relativize(file)));
			return FileVisitResult.CONTINUE;
		}
	}

	/**
	 * creates file if not present
	 * checks if source file is not present and returns false
	 *
	 * @param sourcePath
	 * @param targetPath
	 */
	public static boolean copyContentOfFolder(Path sourcePath, Path targetPath) {
		if (! sourcePath.toFile().exists()) {
			System.out.println("file "+ sourcePath+" does not exist");
			return false;
		}


		try {
			Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(final Path dir,
														 final BasicFileAttributes attrs) throws IOException {
					Files.createDirectories(targetPath.resolve(sourcePath
							.relativize(dir)));
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(final Path file,
												 final BasicFileAttributes attrs) throws IOException {
					Files.copy(file,
							targetPath.resolve(sourcePath.relativize(file)));
					return FileVisitResult.CONTINUE;
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		final Path sourcePath = Paths.get("C:/a");
		final Path targetPath = Paths.get("C:/b");
		copyContentOfFolder(sourcePath, targetPath);

	}
}

