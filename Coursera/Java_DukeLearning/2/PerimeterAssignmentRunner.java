import java.io.File;
import edu.duke.*;

public class PerimeterAssignmentRunner {
    
	public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start with prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int totalPoints = 0;
        for(Point pt : s.getPoints())
            totalPoints++;
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double maxLen = 0.0;
        Point prePt = s.getLastPoint();
        for(Point curPt : s.getPoints())
        {
            double curDist = prePt.distance(curPt);
            if(curDist >= maxLen)
                maxLen = curDist;
            prePt = curPt;
        }
        return maxLen;
    }

    public double getLargestX(Shape s) {
        double X = 0.0;
        //X = pt.getX();
        for(Point ptr : s.getPoints())
            if(X <= ptr.getX())
                X = ptr.getX();
        return X;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largePerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(largePerimeter <= perimeter)
                largePerimeter = perimeter;
        }
        return largePerimeter;
    }

    public String getFileWithLargestPerimeter() {
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        double largePerimeter = 0.0;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if(largePerimeter < perimeter){
                temp = f;
                largePerimeter = perimeter;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        System.out.println("Total Points in shape : " + getNumPoints(s));
        System.out.println("Largest Side : " + getLargestSide(s));
        System.out.println("Largest X : " + getLargestX(s));
        System.out.println("Average Length : " + getAverageLength(s));
        System.out.println("Perimeter = " + getPerimeter(s));
    }

    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter : " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String fileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("File with Largest Perimeter : " + fileWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
