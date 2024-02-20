import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentScoreTracker {
  private List<Double> scores; 

  // Constructor to initialize a new StudentScoreTracker object
  public StudentScoreTracker() {
    scores = new ArrayList<>();
  }

  // Method to add a new assignment score to the data stream
  public void addScore(double score) {
    scores.add(score); 
    Collections.sort(scores); 
  }

  // Method to calculate and return the median of all assignment scores in the list
  public double getMedianScore() {
    int size = scores.size();
    if (size == 0) {
      throw new IllegalStateException("No scores available.");
    }

    int middleIndex = size / 2; // Index of the middle score

    if (size % 2 == 0) {
      // If even, calculate the average of the two middle scores
      double lowerMedian = scores.get(middleIndex - 1);
      double upperMedian = scores.get(middleIndex);
      return (lowerMedian + upperMedian) / 2.0;
    } else {
      // If odd, return the middle score
      return scores.get(middleIndex);
    }
  }

  public static void main(String[] args) {
    // Example usage
    StudentScoreTracker scoreTracker = new StudentScoreTracker();
    scoreTracker.addScore(85.5);
    scoreTracker.addScore(92.3);
    scoreTracker.addScore(77.8);
    scoreTracker.addScore(90.1);
    double median1 = scoreTracker.getMedianScore();
    System.out.println("Median score 1: " + median1);

    scoreTracker.addScore(81.2);
    scoreTracker.addScore(88.7);
    double median2 = scoreTracker.getMedianScore();
    System.out.println("Median score 2: " + median2);
  }
}
