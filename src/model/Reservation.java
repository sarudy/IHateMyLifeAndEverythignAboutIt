package model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation implements Comparable<Reservation> {
    public final Customer customer;
    private final IRoom room;
    // I know I am deviating from the script but I had to find another source to learn about dates because the
    // course chapter was not helping and I found LocalDate along the way and it's much better
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    // don't need to validate the Date format because the java class does it for me
    public Reservation(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return this.room;
    }

    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }

    @Override
    public int compareTo(Reservation o) {
        if (this.getCheckInDate().isAfter(o.getCheckInDate()) || this.getCheckInDate().isEqual(o.getCheckInDate())) {
            return 1;
        } else if (this.getCheckInDate().isBefore(o.getCheckInDate())) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getRoom(), getCheckInDate(), getCheckOutDate());
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
