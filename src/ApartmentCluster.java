import java.util.ArrayList;
import java.util.List;

public class ApartmentCluster {
  private List<Apartment> apartments;

  public ApartmentCluster() {
    this.apartments = new ArrayList<>();
  }

  public void addApartment(Apartment apartment) {
    apartments.add(apartment);
  }

  public double calculateFit(Apartment apartment, UserPreferences preferences) {
    double fitScore = 0.0;

    // Boolean preferences
    if (preferences.requiresGym() && !apartment.hasGym()) {
      fitScore += 1;
    }
    if (preferences.requiresPetFriendly() && !apartment.isPetFriendly()) {
      fitScore += 1;
    }

    // Numerical preferences - garage and beds
    fitScore += Math.abs(apartment.getGarage() - preferences.getGarage());
    fitScore += Math.abs(apartment.getNumberOfBeds() - preferences.getNumberOfBeds());

    // Budget
    if (apartment.getPrice() < preferences.getMinBudget() || apartment.getPrice() > preferences.getMaxBudget()) {
      fitScore += 1; // Or calculate based on how far it is from the range
    }

    // Distance
    if (apartment.getDistance() > preferences.getMaxDistance()) {
      fitScore += (apartment.getDistance() - preferences.getMaxDistance());
    }

    return fitScore;
  }


  public List<Apartment> getApartments() {
    return apartments;
  }

  public int getClusterSize() {
    return apartments.size();
  }
}
