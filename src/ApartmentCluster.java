import java.util.ArrayList;
import java.util.List;

public class ApartmentCluster {
  private List<Apartment> apartments;
  private static final double WEIGHT_GYM = 20.0;
  private static final double WEIGHT_PET_FRIENDLY = 20.0;
  private static final double WEIGHT_GARAGE = 15.0;
  private static final double WEIGHT_BEDS = 15.0;
  private static final double WEIGHT_BUDGET = 30.0;
  private static final double WEIGHT_DISTANCE = 30.0;

  public ApartmentCluster() {
    this.apartments = new ArrayList<>();
  }

  public void addApartment(Apartment apartment) {
    apartments.add(apartment);
  }

  public double calculateFit(Apartment apartment, UserPreferences preferences) {
    double fitScore = 100.0;

    if (!apartment.isPetFriendly() && preferences.requiresPetFriendly()) {
      fitScore -= WEIGHT_PET_FRIENDLY;
    }

    if (!apartment.hasGym() && preferences.requiresGym()) {
      fitScore -= WEIGHT_GYM;
    }

    if (apartment.getNumberOfBeds() != preferences.getNumberOfBeds()) {
      fitScore -= Math.abs(apartment.getNumberOfBeds() - preferences.getNumberOfBeds()) * WEIGHT_BEDS;
    }

    if (apartment.getGarage() != preferences.getGarage()) {
      fitScore -= Math.abs(apartment.getGarage() - preferences.getGarage()) * WEIGHT_GARAGE;
    }

    if (apartment.getPrice() >= preferences.getMaxBudget()) {
      fitScore -= (preferences.getMaxBudget() - apartment.getPrice()) / WEIGHT_BUDGET;
    }

    if (apartment.getDistance() > preferences.getMaxDistance()) {
      fitScore -= (apartment.getDistance() - preferences.getMaxDistance()) * WEIGHT_DISTANCE;
    }

    return Math.max(0, fitScore);
  }





  public List<Apartment> getApartments() {
    return apartments;
  }

  public int getClusterSize() {
    return apartments.size();
  }
}
