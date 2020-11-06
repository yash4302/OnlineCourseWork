import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class Weather {
	// Coldest hour in day starts
	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord coldest = null;
		for(CSVRecord current : parser) {
			coldest = getSmallestOfTwo(current, coldest);
		}
		return coldest;
	}
	// Coldest hour in day ends
	
	// Lowest humidity in day starts
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowest = null;
		for(CSVRecord current : parser) {
			if(lowest == null) 
				lowest = current;
			else {
				if(current.get("Humidity").equals("N/A"))
					continue;
				else {
					int currHum = Integer.parseInt(current.get("Humidity"));
					int lowestHum = Integer.parseInt(lowest.get("Humidity"));
					if(lowestHum > currHum)
						lowest = current;
				}
			}
		}
		return lowest;
	}
	
	// testing lowest humidity starts
	public void testLowestHumidity() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord record = lowestHumidityInFile(parser);
		System.out.println(record.get("Humidity") + " at " + record.get("DateUTC"));
	}
	// testing lowest humidity ends
	// Lowest humidity in day ends
	
	// average Temperature starts
	public double averageTempInFile(CSVParser parser) {
		double averageTemp;
		double sum = 0;
		int count = 0;
		for(CSVRecord record : parser) {
			sum = sum + Double.parseDouble(record.get("TemperatureF"));
			count++;
		}
		averageTemp = sum/count;
		return averageTemp;
	}
	
	// Testing starts
	public void testAvgTemp() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double avg = averageTempInFile(parser);
		System.out.println(avg);
	}
	// Testing ends
	// average Temperature ends
	
	// average Temperature starts
	public double averageTempHighHumidityInFile(CSVParser parser, int humidity) {
		double averageTemp = 0;
		double sum = 0;
		int count = 0;
		for(CSVRecord record : parser) {
			int currHumidity = Integer.parseInt(record.get("Humidity"));
			if(currHumidity >= humidity) {
				sum += Double.parseDouble(record.get("TemperatureF"));
				count++;
			}
		}
		if(count > 0)
			averageTemp = sum/count;
		return averageTemp;
	}
		
	// Testing starts
	public void testAvgTempHighHumidity() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double avg = averageTempHighHumidityInFile(parser, 80);
		String str;
		if(avg == 0)
			str = "No such Temperature found";
		else
			str = "Desired Temperature : " + avg;
		System.out.println(str);
	}
	// Testing ends
	// average Temperature ends
	
	// Smallest of 2 starts
	public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
		//If largestSoFar is nothing
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
			//Check if currentRow’s temperature > largestSoFar’s
			if (currentTemp < smallestTemp) {
				//If so update largestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		return smallestSoFar;
	}
	// Smallest of 2 ends
	
	// File with Coldest Temperature starts
	public String fileWithColdestTemperature() {
		File coldestFile = null;
		CSVRecord coldestRecord = null;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRecord = null;
			CSVParser parser = fr.getCSVParser();
			currentRecord = coldestHourInFile(parser);
			if(coldestRecord == null)
				coldestRecord = currentRecord;
			else {
				double currTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
				double coldTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
				if(coldTemp > currTemp) {
					coldestRecord = currentRecord;
					coldestFile = f;
				}
			}
		}
		return coldestFile.getAbsolutePath();
	}
	
	// Testing
	public void testFileWithColdestTemperature() {
		String file = fileWithColdestTemperature();
		FileResource fr = new FileResource(file);
		CSVParser parser = fr.getCSVParser();
		CSVRecord coldestHour = coldestHourInFile(parser);
		System.out.println("Coldest Day : " + file);
		System.out.println("Coldest Temperature : " +coldestHour.get("TemperatureF"));
		// Resetting parser
		parser = fr.getCSVParser();
		for(CSVRecord record : parser) {
			System.out.println(record.get("DateUTC") + " : " + record.get("TemperatureF"));
		}
	}
	// Testing ends
	// File with Coldest Temperature ends
	
	// File with Lowest Humidity starts
	public CSVRecord fileWithLowestHumidity() {
		CSVRecord lowest = null;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser();
			CSVRecord current = lowestHumidityInFile(parser);
			if(lowest == null)
				lowest = current;
			else {
				int currHum = Integer.parseInt(current.get("Humidity"));
				int lowestHum = Integer.parseInt(lowest.get("Humidity"));
				if(lowestHum > currHum)
					lowest = current;
			}
		}
		return lowest;
	}
	// Testing starts
	public void testLowestHumidityInFiles() {
		CSVRecord record = fileWithLowestHumidity();
		System.out.println(record.get("Humidity") + " at " + record.get("DateUTC"));
	}
	// Testing ends
	// File with lowest Humidity ends
	
	public static void main(String[] args) {
		Weather w = new Weather();
		w.testFileWithColdestTemperature();
	}

}
