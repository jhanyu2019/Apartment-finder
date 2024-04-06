public class UserPreferences {
  private int minBudget;
  private int maxBudget;
  private int maxDistance;
  private boolean requiresGym;
  private boolean requiresPetFriendly;
  private double garage;
  private int numberOfBeds;

  public UserPreferences(int minBudget, int maxBudget, int maxDistance,
                         boolean requiresGym, boolean requiresPetFriendly,
                         double garage, int numberOfBeds) {
    this.minBudget = minBudget;
    this.maxBudget = maxBudget;
    this.maxDistance = maxDistance;
    this.requiresGym = requiresGym;
    this.requiresPetFriendly = requiresPetFriendly;
    this.garage = garage;
    this.numberOfBeds = numberOfBeds;
  }

  public int getMinBudget() {
    return minBudget;
  }

  public int getMaxBudget() {
    return maxBudget;
  }

  public int getMaxDistance() {
    return maxDistance;
  }

  public boolean requiresGym() {
    return requiresGym;
  }

  public boolean requiresPetFriendly() {
    return requiresPetFriendly;
  }

  public double getGarage() {
    return garage;
  }

  public int getNumberOfBeds() {
    return numberOfBeds;
  }
}