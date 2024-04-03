import java.util.List;

public class Apartment {
  private int mls_id;
  private String mls;
  private int list_price;
  private int distance;

  private boolean hasGym;
  private boolean isPetFriendly;

  public Apartment(int mls_id, String mls, int list_price, int distance, boolean hasGym, boolean isPetFriendly) {
    this.mls_id = mls_id;
    this.mls = mls;
    this.list_price = list_price;
    this.distance = distance;

    this.hasGym = hasGym;
    this.isPetFriendly = isPetFriendly;
  }

  public int getId() {
    return mls_id;
  }

  public String getName() {
    return mls;
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
            ", name='" + mls + '\'' +
            ", price=" + list_price +
            ", distance=" + distance +
            '}';
  }

}
