import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ApartmentSearcher {
  private List<ApartmentCluster> clusters;

  public ApartmentSearcher() {
    clusters = createClusters();
  }

  private List<ApartmentCluster> createClusters() {
    List<ApartmentCluster> clusters = new ArrayList<>();

    int[] budgetRanges = {500, 1000, 1500, 2000, 2500, 3500};
    int[] distanceRanges = {15, 30, 45, 60};

    for (int i = 0; i < budgetRanges.length - 1; i++) {
      for (int j = 0; j < distanceRanges.length; j++) {
        int priceMin = budgetRanges[i];
        int priceMax = budgetRanges[i + 1];
        int distanceMax = distanceRanges[j];
        clusters.add(new ApartmentCluster(priceMin, priceMax, distanceMax));
      }
    }

    return clusters;
  }


  public void addApartments(String jsonData) {
    Gson gson = new Gson();
    Type listType = new TypeToken<List<Apartment>>() {}.getType();
    List<Apartment> apartments = gson.fromJson(jsonData, listType);

    for (Apartment apartment : apartments) {

      for (ApartmentCluster cluster : clusters) {
        if (apartment.getPrice() >= cluster.getPriceMin()
                && apartment.getPrice() <= cluster.getPriceMax()
                && apartment.getDistance() <= cluster.getDistanceMax()) {
          cluster.addApartment(apartment);
          break;
        }
      }
    }
  }

  public List<Apartment> searchApartments(int minBudget, int maxBudget, int maxDistance) {
    List<Apartment> results = new ArrayList<>();
    for (ApartmentCluster cluster : clusters) {
      if (cluster.getPriceMax() >= minBudget && cluster.getPriceMin() <= maxBudget
              && cluster.getDistanceMax() <= maxDistance) {
        results.addAll(cluster.getApartments());
      }
    }
    return results;
  }





 public List<Apartment> filterApartments(List<Apartment> results,Boolean requiresGym, Boolean requiresPetFriendly) {
    List<Apartment> filteredList = new ArrayList<>();
    for (Apartment apt : results) {


      boolean gymMatch = (requiresGym == null) || (requiresGym ? apt.isHasGym() : true);
      boolean petFriendlyMatch = (requiresPetFriendly == null) || (requiresPetFriendly ? apt.isPetFriendly() : true);

      if (gymMatch && petFriendlyMatch) {
        filteredList.add(apt);
      }
    }
    return filteredList;
  }



/*
public void updateApartmentsWithAttributes(String attributesJson) {
Gson gson = new Gson();
Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
List<Map<String, Object>> attributeList = gson.fromJson(attributesJson, listType);

Map<Integer, Map<String, Object>> attributesMap = new HashMap<>();
for (Map<String, Object> attributes : attributeList) {
  Integer id = ((Double) attributes.get("mls_id")).intValue();
  attributesMap.put(id, attributes);
}

for (ApartmentCluster cluster : clusters) {
  for (Apartment apartment : cluster.getApartments()) {
    Map<String, Object> additionalAttrs = attributesMap.get(apartment.getId());
    if (additionalAttrs != null) {
      Boolean hasGym = (Boolean) additionalAttrs.get("gym");
      Boolean isPetFriendly = (Boolean) additionalAttrs.get("petFriendly");

      if (hasGym != null) {
        apartment.setHasGym(hasGym);
      }
      if (isPetFriendly != null) {
        apartment.setPetFriendly(isPetFriendly);
      }
    }
    }
  }
}
*/







}
