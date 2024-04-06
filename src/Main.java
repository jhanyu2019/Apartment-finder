import java.nio.file.Paths;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.List;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {
  public static void main(String[] args) {
    String filePath = Paths.get("database.json").toString();
    String apartmentsJson = readJsonFile(filePath);

    Scanner scanner = new Scanner(System.in);
    UserPreferences preferences = getUserPreferences(scanner);

    List<Apartment> filteredApartments = filterApartments(apartmentsJson, preferences);

    GreedyClustering clustering = new GreedyClustering(filteredApartments, preferences);
    clustering.formClusters();

    displayClusters(clustering.getClusters());

    scanner.close();
  }

  private static UserPreferences getUserPreferences(Scanner scanner) {
    System.out.print("Enter minimum budget: ");
    int minBudget = scanner.nextInt();
    System.out.print("Enter maximum budget: ");
    int maxBudget = scanner.nextInt();
    System.out.print("Enter maximum distance (in minutes): ");
    int maxDistance = scanner.nextInt();
    System.out.print("Is a gym required? (true/false): ");
    boolean requiresGym = scanner.nextBoolean();
    System.out.print("Must be pet friendly? (true/false): ");
    boolean requiresPetFriendly = scanner.nextBoolean();
    System.out.print("Do you need parking garage? (0 for street parking, 1-3 for garage space): ");
    double garage = scanner.nextDouble();
    System.out.print("How many bedrooms needed? (0 for studio, 1-10 for the number of beds): ");
    int numberOfBeds = scanner.nextInt();

    return new UserPreferences(minBudget, maxBudget, maxDistance, requiresGym, requiresPetFriendly, garage, numberOfBeds);
  }

  private static List<Apartment> filterApartments(String apartmentsJson, UserPreferences preferences) {
    Gson gson = new Gson();
    Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
    List<Map<String, Object>> apartmentsData = gson.fromJson(apartmentsJson, listType);

    List<Apartment> filteredList = new ArrayList<>();
    for (Map<String, Object> apartmentData : apartmentsData) {
      boolean hasGym = (boolean) apartmentData.getOrDefault("gym", false);
      boolean isPetFriendly = (boolean) apartmentData.getOrDefault("pets_allowed", false);
      double garageSpace = ((Number) apartmentData.getOrDefault("parking_garage", 0)).doubleValue();
      int numberOfBeds = apartmentData.get("beds") != null ? ((Number) apartmentData.get("beds")).intValue() : 0;
      int list_price = ((Number) apartmentData.get("list_price")).intValue();
      int distance = ((Number) apartmentData.get("distance")).intValue();


      if ((!preferences.requiresGym() || hasGym) &&
              (!preferences.requiresPetFriendly() || isPetFriendly) &&
              garageSpace >= preferences.getGarage() &&
              numberOfBeds >= preferences.getNumberOfBeds() &&
              list_price >= preferences.getMinBudget() &&
              list_price <= preferences.getMaxBudget() &&
              distance <= preferences.getMaxDistance()) {


        String mlsId = (String) apartmentData.get("mls_id");
        String propertyUrl = (String) apartmentData.get("property_url");
        String city = (String) apartmentData.get("city");
        String street = (String) apartmentData.get("street");

        Apartment apartment = new Apartment(mlsId, propertyUrl, city, street, list_price, distance, hasGym, isPetFriendly, garageSpace, numberOfBeds);
        filteredList.add(apartment);
      }
    }
    return filteredList;
  }

  private static String readJsonFile(String filename) {
    StringBuilder sb = new StringBuilder();
    try (FileReader fileReader = new FileReader(filename);
         BufferedReader bufferedReader = new BufferedReader(fileReader)) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
    return sb.toString();
  }




  private static void displayClusters(List<ApartmentCluster> clusters) {
    if (clusters.isEmpty()) {
      System.out.println("No apartments found.");
      return;
    }

    System.out.println("Clustered Apartment Results:");
    int clusterNumber = 1;
    for (ApartmentCluster cluster : clusters) {
      List<Apartment> apartments = cluster.getApartments();
      if (apartments.isEmpty()) {
        System.out.println("No apartments in Cluster " + clusterNumber);
        clusterNumber++;
        continue;
      }

      System.out.println("Cluster " + clusterNumber + ":");
      for (Apartment apartment : apartments) {
        System.out.println("  ID: " + apartment.getId());
        System.out.println("  Property_url: " + apartment.getName());
        System.out.println("  City: " + apartment.getCity());
        System.out.println("  Street: " + apartment.getStreet());
        System.out.println("  Price: " + apartment.getPrice());
        System.out.println("  Distance: " + apartment.getDistance() + " minutes");
        System.out.println("  Number of beds: " + apartment.getNumberOfBeds());
        System.out.println("  Number of Garage: " + apartment.getGarage());
        System.out.println("  Gym: " + apartment.hasGym());
        System.out.println("  Pet friendly: " + apartment.isPetFriendly());
        System.out.println("  --------------------");
      }
      clusterNumber++;
    }
  }


}