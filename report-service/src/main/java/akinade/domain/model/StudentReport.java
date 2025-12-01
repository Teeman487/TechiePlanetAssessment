package akinade.domain.model;
import java.util.List;

public class StudentReport {
    private  String studentName;
    private List<Integer> scores;
    private double mean;
    private double median;
    private int mode;


    public StudentReport(String studentName, List<Integer> scores, double mean, double median, int mode) {
        this.studentName = studentName;
        this.scores = scores;
        this.mean = mean;
        this.median = median;
        this.mode = mode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}




