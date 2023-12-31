package webtech.filmfriend;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Roll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Stock stock;
    private LocalDate expectedPickupDate;
    private int usedIso;
    private String labName;
    private String notes;

    public Roll() {}

    public Roll(Long id, Stock stock) {
        this.id = id;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public LocalDate getExpectedPickupDate() {
        return expectedPickupDate;
    }

    public void setExpectedPickupDate(LocalDate expectedPickupDate) {
        this.expectedPickupDate = expectedPickupDate;
    }

    public int getUsedIso() {
        return usedIso;
    }

    public void setUsedIso(int usedIso) {
        this.usedIso = usedIso;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
