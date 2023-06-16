package lesson5;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BackUp {
	
	public static void main(String[] args) {
		copyFiles(Path.of("C://1"), Path.of("C://2"));
	}
	
	/**
	 * копирование файлов с вложениями рекурсивным способом
	 *
	 * @param start от куда копировать
	 * @param out   куда копировать
	 */
	static void copyFiles(Path start, Path out) {
		try (DirectoryStream<Path> files = Files.newDirectoryStream(start)) {
			if (!Files.exists(out)) Files.createDirectory(out);
			for (Path path : files) {
				if (Files.isDirectory(path)) {
					copyFiles(path, Path.of(out + "\\" + path.getFileName()));
				} else {
					Files.copy(path, Path.of(out + "\\" + path.getFileName()));
				}
			}
		} catch (IOException e) {
		}
	}
}
