package kg.mega.kindergarten.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "children_group_histories")
public class ChildrenGroupHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Child getChildren() {
        return children;
    }

    public void setChildren(Child children) {
        this.children = children;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
