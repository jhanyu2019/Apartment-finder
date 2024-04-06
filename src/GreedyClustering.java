import java.util.*;
public class GreedyClustering {
  private List<Apartment> apartments;
  private List<ApartmentCluster> clusters;
  private UserPreferences preferences;

  public GreedyClustering(List<Apartment> apartments, UserPreferences preferences) {
    this.apartments = apartments;
    this.preferences = preferences;
    this.clusters = new ArrayList<>();
    System.out.println("Apartments in GreedyClustering: " + apartments);
  }

  public void formClusters() {
    for (Apartment apartment : apartments) {
      ApartmentCluster bestCluster = findBestClusterForApartment(apartment);
      if (bestCluster != null) {
        bestCluster.addApartment(apartment);
      }
    }
    System.out.println("Clusters after formClusters: " + clusters);
  }

  private ApartmentCluster findBestClusterForApartment(Apartment apartment) {
    ApartmentCluster bestCluster = null;
    double bestFitScore = Double.MAX_VALUE;

    for (ApartmentCluster cluster : clusters) {
      double fitScore = cluster.calculateFit(apartment, preferences);
      if (fitScore < bestFitScore) {
        bestFitScore = fitScore;
        bestCluster = cluster;
      }
    }
    if (bestCluster == null) {
      bestCluster = new ApartmentCluster();
      clusters.add(bestCluster);
    }

    return bestCluster;

  }

  public List<Apartment> getApartments() {
    return apartments;
  }

  public void setApartments(List<Apartment> apartments) {
    this.apartments = apartments;
  }

  public List<ApartmentCluster> getClusters() {
    return clusters;
  }

  public UserPreferences getPreferences() {
    return preferences;
  }

  public void setPreferences(UserPreferences preferences) {
    this.preferences = preferences;
  }
}
