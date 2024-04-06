public class Apartment {
  private String mls_id;
  private String property_url;
  private int list_price;
  private int distance;
  private boolean hasGym;
  private boolean isPetFriendly;
  private double garage;
  private int numberOfBeds;
  private String city;
  private String street;



  public Apartment(String mls_id, String property_url, String city, String street,  int list_price,
                   int distance, boolean hasGym, boolean isPetFriendly, double garage, int numberOfBeds) {
    this.mls_id = mls_id;
    this.property_url = property_url;
    this.list_price = list_price;
    this.distance = distance;
    this.city = city;
    this.street = street;

    this.hasGym = hasGym;
    this.isPetFriendly = isPetFriendly;
    this.garage = garage;
    this.numberOfBeds = numberOfBeds;
  }

  public String getId() {
    return mls_id;
  }

  public String getName() {
    return property_url;
  }

  public String getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }


  public int getPrice() {
    return list_price;
  }

  public int getDistance() {
    return distance;
  }

  public boolean hasGym() {
    return hasGym;
  }

  public boolean isPetFriendly() {
    return isPetFriendly;
  }

  public double getGarage() {
    return garage;
  }

  public int getNumberOfBeds(){
    return numberOfBeds;
  }




  @Override
  public String toString() {
    return "Apartment{" +
            "id=" + mls_id +
            ", property_url='" + property_url + '\'' +
            ", city=" + city +
            ", street=" + street +
            ", price=" + list_price +
            ", distance=" + distance +
            ", number of bedrooms=" + numberOfBeds +
            ", hasGym=" + hasGym +
            ", isPetFriendly=" + isPetFriendly +
            ", garage=" + garage +
            '}';
  }

}
