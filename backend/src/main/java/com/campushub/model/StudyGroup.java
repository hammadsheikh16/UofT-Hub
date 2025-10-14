package com.campushub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
public class StudyGroup {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = true
    )
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private HubUser owner;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Membership> memberships = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(owner, that.owner) && Objects.equals(memberships, that.memberships);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, owner, memberships);
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", memberships=" + memberships +
                '}';
    }
}
