import edu.duke.*;

public class DNA_Assignment {
	// DNA Finder starts
	public String findGene(String dna, int startIndex) {
		/* 
		int start = dna.indexOf("ATG");
		if (start == -1) {
			return "";
		}
		*/
		int stopTAA = findStopCodon(dna, startIndex, "TAA");
		int stopTGA = findStopCodon(dna, startIndex, "TGA");
		int stopTAG = findStopCodon(dna, startIndex, "TAG");
		
		if(stopTAA == -1)
			stopTGA = dna.length();
		if(stopTAA == -1)
			stopTAA = dna.length();
		if(stopTAG == -1)
			stopTAA = dna.length();
		
		int stop = Math.min(stopTAA, Math.min(stopTGA, stopTAG));
		
		if (stop < dna.length()) {
			return dna.substring(startIndex, stop+3);
		}
		else {
			return "";
		}
	}
	// DNA Finder ends
	
	// All Genes starts
	public void printAllGenes(String dna) {
		dna = dna.toUpperCase();
		int startIndex = dna.indexOf("ATG");
		
		while(startIndex != -1) {
			String gene = findGene(dna, startIndex);
			if(gene == "")
				break;
			System.out.println(gene);
			startIndex = dna.indexOf("ATG", startIndex+gene.length());
		}
	}
	// All Genes ends
	
	// Count Genes starts
	public int countAllGenes(String dna) {
		dna = dna.toUpperCase();
		int startIndex = dna.indexOf("ATG");
		int count = 0;
		
		while(startIndex != -1) {
			String gene = findGene(dna, startIndex);
			if(gene == "")
				break;
			count++;
			startIndex = dna.indexOf("ATG", startIndex+gene.length());
		}
		return count;
	}
	// Count Genes ends
	
	// Get Genes starts
	public StorageResource getAllGenes(String dna) {
		dna = dna.toUpperCase();
		int startIndex = dna.indexOf("ATG");
		StorageResource sr = new StorageResource();
			
		while(startIndex != -1) {
			String gene = findGene(dna, startIndex);
			if(gene == "")
				break;
			sr.add(gene);
			startIndex = dna.indexOf("ATG", startIndex+gene.length());
		}
		return sr;
	}
	// Get Genes ends
	
	// CGRatio Starts
	public double cgRatio(String dna) {
		dna = dna.toUpperCase();
		
		int cg = howMany("G", dna);
		cg = cg + howMany("C", dna);
		
		return (double)cg/dna.length();
	}
	// CGRatio ends
	
	// countCTG starts
	public int countCTG(String dna) {
		dna = dna.toUpperCase();
		int count = 0;
		int index = dna.indexOf("CTG");
		while(index != -1) {
			count++;
			index = dna.indexOf("CTG", index+3);
		}
		return count;
	}
	// countCTG ends
	
	// findStopCodon starts
	public int findStopCodon(String dna, int startIndex, String stopCodon) {
		int stopIndex = dna.indexOf(stopCodon, startIndex);
		while((stopIndex-startIndex)%3 != 0 && stopIndex!=-1)
			stopIndex = dna.indexOf(stopCodon, stopIndex+3);
		if(stopIndex == -1)
			return dna.length();
		return stopIndex;
	}
	// findStopCodon ends
	
	// howMany Finder starts
	public int howMany(String a, String b) {
		int count = 0;
		int index = b.indexOf(a, 0);
		while(index != -1) {
			count++;
			index = b.indexOf(a, index+a.length());
		}
		return count;
	}
	// howMany Finder ends
	
	// Main content
	public void processGene() {
		FileResource fr = new FileResource();
		String dna = fr.asString();
		int count = 0, max=0;
		int ctg = countCTG(dna);
		StorageResource sr = getAllGenes(dna);
		for(String str : sr.data()) {
			count++;
			if(str.length() > 60) {
				System.out.println(str);
				//count++;
			}
			else if(cgRatio(str) > 0.35) {
				System.out.println(str);
				//count++;
			}
			if(str.length() > max)
				max = str.length();
		}
		System.out.println(count);
		System.out.println(max);
		System.out.println(ctg);
	}
	
	// main Method
	public static void main(String[] args) {
		DNA_Assignment d = new DNA_Assignment();
		d.processGene();
	}
}
