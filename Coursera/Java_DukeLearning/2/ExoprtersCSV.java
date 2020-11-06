import edu.duke.*;
import org.apache.commons.csv.*;

public class ExoprtersCSV {
	// CountryInfo starts
	public String countryInfo(CSVParser parser, String country) {
		String str = "NOT FOUND";
		for(CSVRecord record : parser) {
			if(country.equals(record.get("Country"))) {
				str = record.get(0) + " : " + record.get(1) + " : " + record.get(2); 
			}
		}
		return str;
	}
	// CountryInfo ends
	
	// Two Export starts
	public void listTwoExports(CSVParser parser, String i1, String i2) {
		for(CSVRecord record : parser) {
			String str = record.get("Exports");
			if(str.contains(i1) && str.contains(i2))
				System.out.println(record.get("Country"));
		}
	}
	// Two Export ends
	
	// Total Exporters starts
	public int totalExporters(CSVParser parser, String item) {
		int count = 0;
		for(CSVRecord record : parser ) {
			String str = record.get(1);
			if(str.contains(item))
				count++;
		}
		return count;
	}
	// Total Exporters ends
	
	// bigExporters starts
	public void bigExporters(CSVParser parser, String amount) {
		String str = "NOT FOUND";
		for(CSVRecord record : parser) {
			String curAmount = record.get(2);
			if(curAmount.length() > amount.length()) {
				str = record.get(0) + " : " + record.get(2);
				System.out.println(str);
			}
		}
	}
	// bigExporters ends
	
	// Testing method starts
	public void testing() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		listTwoExports(parser, "gold", "diamonds");
		// Resetting parser so that it start searching from beginning
		parser = fr.getCSVParser();
		System.out.println(countryInfo(parser, "Nauru"));
		// Reset parser
		parser = fr.getCSVParser();
		System.out.println("Suger Exporters : " + totalExporters(parser, "sugar"));
		// Resetting parser so that it start searching from beginning
		parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999,999");
	}
	// Testing method ends
	
	public static void main(String[] args) {
		ExoprtersCSV obj = new ExoprtersCSV();
		obj.testing();
	}

}
