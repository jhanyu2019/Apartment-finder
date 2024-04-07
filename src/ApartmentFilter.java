import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApartmentFilter {
  public static List<Apartment> filterApartments(String apartmentsJson, UserPreferences preferences) {
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


      if (
              garageSpace >= preferences.getGarage() &&
              numberOfBeds >= preferences.getNumberOfBeds() &&
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
}
