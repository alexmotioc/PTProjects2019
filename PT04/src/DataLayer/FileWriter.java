package DataLayer;

import java.util.List;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWriter {
	private ArrayList<String> lines;
	private Path file;
	public FileWriter() {
		lines = new ArrayList<>();
		file = Paths.get("bill.txt");
	}
	public void addLine(String line) {
		lines.add(line);
	}
	
	public void write() {
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
