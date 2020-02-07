package hybrid.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

	public void writeToFile(String fileName, String writeContent) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(writeContent);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception occured :" + e + e.getMessage());
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Exception occured :" + e + e.getMessage());
			}
		}
	}

	public String readFromFile(String fileName) {
		BufferedReader reader = null;
		String readContent = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			if (reader.readLine() != null) {
				readContent = readContent + "\n" + reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception occured :" + e + e.getMessage());
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Exception occured :" + e + e.getMessage());
			}
		}
		return readContent;
	}

}
