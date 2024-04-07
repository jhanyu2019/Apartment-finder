import java.nio.file.Paths;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.List;


public class Main {
  public static void main(String[] args) {
    String filePath = Paths.get("database.json").toString();
    String apartmentsJson = readJsonFile(filePath);

    Scanner scanner = new Scanner(System.in);
    UserPreferences preferences = getUserPreferences(scanner);

    List<Apartment> filteredApartments = ApartmentFilter.filterApartments(apartmentsJson, preferences);

    GreedyClustering clustering = new GreedyClustering(filteredApartments, preferences);
    clustering.formClusters();

    displayCluster(clustering.getOptimalCluster());

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

  private static void displayCluster(ApartmentCluster cluster) {

    System.out.println("Optimal Apartments:");
    for (Apartment apartment : cluster.getApartments()) {
      System.out.println("Apartment ID: " + apartment.getId() );
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
  }


}