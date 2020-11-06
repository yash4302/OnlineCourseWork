import java.io.*;
import org.apache.commons.csv.*;
import edu.duke.*;

public class Baby {
	// Print name starts
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num Born " + rec.get(2));
			}
		}
	}
	// Print names ends

	// total Births starts
	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
			}
			else {
				totalGirls += numBorn;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
	}
	
	// testing starts
	public void testTotalBirths () {
		FileResource fr = new FileResource("Baby/yob1905.csv");
		totalBirths(fr);
	}
	// testing ends
	// total Birth ends
	
	// get Rank starts
	public int getRank(int year, String name, String gender) {
		int rank = -1, count = 0;
		FileResource fr = new FileResource("Baby/yob"+year+".csv");
		// FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			if(record.get(1).equals(gender))
				count++;
			if(record.get(0).equals(name) && record.get(1).equals(gender)) {
				rank = count;
				break;
			}
		}
		return rank;
	}
	// get Rank ends
	
	// get name starts
	public String getName(int year, int rank, String gender) {
		String name = "NO NAME";
		int count = 0;
		FileResource fr = new FileResource("Baby/yob"+year+".csv");
		CSVParser parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			if(record.get(1).equals(gender))
				count++;
			if(count==rank && record.get(1).equals(gender)) {
				name = record.get(0);
				break;
			}
			else if(count > rank)
				break;
		}
		return name;
	}
	// get name ends
	
	// name in year starts
	public void nameInYear(String name, int birthYear, int newYear, String gender) {
		int rank = getRank(birthYear, name, gender);
		String pronoun;
		String newName = getName(newYear, rank, gender);
		if(gender.equals("M"))
			pronoun = "he";
		else
			pronoun = "she";
		System.out.println(name + " born in " + birthYear + " would be " + newName + " if " + pronoun + " was born in " + newYear +".");
	}
	// name in year ends
	
	// year of highest rank starts
	public int yearOfHighestRank(String name, String gender) {
		int highestRank = -1, highYear = 0;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			String fileName = f.getAbsolutePath();
			fileName = fileName.substring(fileName.indexOf("yob")+3, fileName.indexOf("yob")+7);
			
			int year = Integer.parseInt(fileName);
			int currRank = getRank(year, name, gender);
			if(highestRank == -1)
				highestRank = currRank;
			else if(currRank != -1 && currRank < highestRank) {
				highestRank = currRank;
				highYear = year;
			}
		}
		System.out.println(highYear);
		return highestRank;
	}
	// year of highest rank ends
	
	// year of highest rank starts
	public double avgRank(String name, String gender) {
		int sum=0, count=0;
		double avgRank = -1;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			String fileName = f.getAbsolutePath();
			fileName = fileName.substring(fileName.indexOf("yob")+3, fileName.indexOf("yob")+7);
			
			int year = Integer.parseInt(fileName);
			int currRank = getRank(year, name, gender);
			if(currRank == -1)
				break;
			else {
				sum += currRank;
				count++;
			}
		}
		avgRank = (double)sum/count;
		return avgRank;
	}
	// year of highest rank ends
	
	// total births ranked higher starts
	public int totalBirthRankedHigher(int year, String name, String gender) {
		int count = 0;
		// FileResource fr = new FileResource();
		FileResource fr = new FileResource("Baby/yob"+year+".csv");
		CSVParser parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			if(record.get(0).equals(name) && record.get(1).equals(gender))
				break;
			else if(record.get(1).equals(gender))
				count += Integer.parseInt(record.get(2));
		}
		return count;
	}
	// total births ranked higher ends
	
	public static void main(String[] args) {
		Baby b = new Baby();
		// b.testTotalBirths();
		// System.out.println(b.getRank(1960, "Emily", "F"));
		// System.out.println(b.getRank(1971, "Frank", "M"));
		// System.out.println(b.getName(1980, 350, "F"));
		// System.out.println(b.getName(1982, 450, "M"));
		// b.nameInYear("Susan", 1972, 2014, "F");
		// b.nameInYear("Owen", 1974, 2014, "M");
		// System.out.println(b.yearOfHighestRank("Genevieve", "F"));
		// System.out.println(b.yearOfHighestRank("Mich", "M"));
		// System.out.println(b.avgRank("Susan", "F"));
		// System.out.println(b.avgRank("Robert", "M"));
		// System.out.println(b.totalBirthRankedHigher(1990, "Emily", "F"));
		// System.out.println(b.totalBirthRankedHigher(1990, "Drew", "M"));
	}
}
