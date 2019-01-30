package entity;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Objects;
@Entity
@Transactional
@Table(name = "add_new_room")
public class EntityBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_new_room")
    private int id;
    @Column(name = "door_nr")
    private  int door;
    @Column(name = "beds")
    private int beds;
    @Column(name = "baths")
    private int baths;
    @Column(name = "separated_room")
    private boolean separatedRoom;
    @Column(name = "max_adults")
    private int maxAdults;
    @Column(name = "max_childrens")
    private int maxChildrens;
    @Column(name = "price_per_night")
    private double pricePerNight;
    @Column(name = "available")
    private boolean available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBaths() {
        return baths;
    }

    public void setBaths(int baths) {
        this.baths = baths;
    }

    public boolean isSeparatedRoom() {
        return separatedRoom;
    }

    public void setSeparatedRoom(boolean separatedRoom) {
        this.separatedRoom = separatedRoom;
    }

    public int getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(int maxAdults) {
        this.maxAdults = maxAdults;
    }

    public int getMaxChildrens() {
        return maxChildrens;
    }

    public void setMaxChildrens(int maxChildrens) {
        this.maxChildrens = maxChildrens;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityBooking)) return false;
        EntityBooking that = (EntityBooking) o;
        return id == that.id &&
                door == that.door &&
                beds == that.beds &&
                baths == that.baths &&
                separatedRoom == that.separatedRoom &&
                maxAdults == that.maxAdults &&
                maxChildrens == that.maxChildrens &&
                Double.compare(that.pricePerNight, pricePerNight) == 0 &&
                available == that.available;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, door, beds, baths, separatedRoom, maxAdults, maxChildrens, pricePerNight, available);
    }

    @Override
    public String toString() {
        return "EntityBooking{" +
                "id=" + id +
                ", door=" + door +
                ", beds=" + beds +
                ", baths=" + baths +
                ", separatedRoom=" + separatedRoom +
                ", maxAdults=" + maxAdults +
                ", maxChildrens=" + maxChildrens +
                ", pricePerNight=" + pricePerNight +
                ", available=" + available +
                '}';
    }
}
