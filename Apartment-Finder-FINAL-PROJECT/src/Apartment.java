import java.util.List;

public class Apartment {
  private int mls_id;
  private String property_url;
  private int list_price;
  private int distance;

  private boolean hasGym;
  private boolean isPetFriendly;

  public Apartment(int mls_id, String property_url, int list_price, int distance, boolean hasGym, boolean isPetFriendly) {
    this.mls_id = mls_id;
    this.property_url = property_url;
    this.list_price = list_price;
    this.distance = distance;

    this.hasGym = hasGym;
    this.isPetFriendly = isPetFriendly;
  }

  public int getId() {
    return mls_id;
  }

  public String getName() {
    return property_url;
  }

  public int getPrice() {
    return list_price;
  }

  public int getDistance() {
    return distance;
  }



  public boolean isHasGym() {
    return hasGym;
  }

  public boolean isPetFriendly() {
    return isPetFriendly;
  }


  public void setHasGym(boolean hasGym) {
    this.hasGym = hasGym;
  }


  public void setPetFriendly(boolean isPetFriendly) {
    this.isPetFriendly = isPetFriendly;
  }



  @Override
  public String toString() {
    return "Apartment{" +
            "id=" + mls_id +
            ", property_url='" + property_url + '\'' +
            ", price=" + list_price +
            ", distance=" + distance +
            '}';
  }

}
