import java.util.*;

public class GreedyClustering {
  private List<Apartment> apartments;
  private ApartmentCluster optimalCluster;
  private UserPreferences preferences;

  public GreedyClustering(List<Apartment> apartments, UserPreferences preferences) {
    this.apartments = apartments;
    this.preferences = preferences;
    this.optimalCluster = new ApartmentCluster();

  }

  public void formClusters() {
    final double FIT_SCORE_THRESHOLD = 83;

    for (Apartment apartment : apartments) {
      double fitScore = optimalCluster.calculateFit(apartment, preferences);

      if (fitScore >= FIT_SCORE_THRESHOLD) {
        optimalCluster.addApartment(apartment);
      }
    }

    System.out.println("Optimal cluster after formClusters:");
    for (Apartment apt : optimalCluster.getApartments()) {
      System.out.println("Apartment ID: " + apt.getId() + " Fit Score: " + optimalCluster.calculateFit(apt, preferences));
    }
  }


  public ApartmentCluster getOptimalCluster() {
    return optimalCluster;
  }

}
