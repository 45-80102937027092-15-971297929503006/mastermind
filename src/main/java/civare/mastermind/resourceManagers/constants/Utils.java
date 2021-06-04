package civare.mastermind.resourceManagers.constants;

// Java program to copy content from
// one file to another

import civare.mastermind.Main;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

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

	public static void copyContent(File a, File b)
			throws Exception {

		try {
			FileInputStream in = new FileInputStream(a);
		} catch (Exception e ) {
			e.printStackTrace();
		}

		try (FileInputStream in = new FileInputStream(a); FileOutputStream out = new FileOutputStream(b)) {

			int n;

			// read() function to read the
			// byte of data
			while ((n = in.read()) != -1) {
				// write() function to write
				// the byte of data
				out.write(n);
			}
		}
		// close() function to close the
		// stream
		// close() function to close
		// the stream
		System.out.println("File Copied");
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

	public  class Temp {
		public Temp() {

		}
	}

	public static void main(String[] args) throws Exception {
		final Path targetPath = Paths.get("C:/b");
		final Path sourcePath = Paths.get("C:/a");
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

//		Files.walkFileTree("C:/a", new CopyFileVisitor(new Path(String.valueOf(getFileFromResourceAsStream("C:/b")))));


		//		String source = "C:/your/source";
//		File srcDir = new File(source);
//
//		String destination = "C:/your/destination";
//		File destDir = new File(destination);
//
//		try {
//			FileUtils.copyDirectory(srcDir, destDir);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

//
//		Scanner sc = new Scanner(System.in);
//
//		// get the source file name
//		System.out.println(
//				"Enter the source filename from where you have to read/copy :");
////		String a = sc.nextLine();

//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//		int result = fileChooser.showOpenDialog(this);
//		if (result == JFileChooser.APPROVE_OPTION) {
//			File selectedFile = fileChooser.getSelectedFile();
//			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//		}
//		// source file
//	File x = new File("C:/a/");
//		System.out.println(x.exists());
//		// get the destination file name
//		System.out.println(
//				"Enter the destination filename where you have to write/paste :");
////		String b = sc.nextLine();
//
//		// destination file
//		File y = new File("C:\\b");
//		System.out.println(y.exists());
//
//		// method called to copy the
		// contents from x to y
//		copyContent(x, y);
	}
}

