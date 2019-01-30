package entity;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rezervation_room")
public class EntityCheckIn {
    //String SQL = "Select 	door_nr , name , surrname ,identity_number, start_date , end_date , total_price  FROM rezervation_room";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "door_nr")
    private  int door;
    @Column(name = "name")
   private String name;
    @Column(name = "surrname")
   private String surrname;
    @Column(name = "identity_number")
    private long identityNumber;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "total_price")
    private double totalPrice;

    public int getId() {
        return id;
    }

    public int getDoor() {
        return door;
    }

    public String getName() {
        return name;
    }

    public String getSurrname() {
        return surrname;
    }

    public long getIdentityNumber() {
        return identityNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurrname(String surrname) {
        this.surrname = surrname;
    }

    public void setIdentityNumber(long identityNumber) {
        this.identityNumber = identityNumber;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityCheckIn)) return false;
        EntityCheckIn that = (EntityCheckIn) o;
        return id == that.id &&
                door == that.door &&
                identityNumber == that.identityNumber &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                name.equals(that.name) &&
                surrname.equals(that.surrname) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, door, name, surrname, identityNumber, startDate, endDate, totalPrice);
    }

    @Override
    public String toString() {
        return "EntityCheckIn{" +
                "id=" + id +
                ", door=" + door +
                ", name='" + name + '\'' +
                ", surrname='" + surrname + '\'' +
                ", identityNumber=" + identityNumber +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
