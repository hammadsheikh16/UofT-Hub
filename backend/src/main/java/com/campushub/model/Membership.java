package com.campushub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@Entity
public class Membership {

    @EmbeddedId
    private MembershipId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private HubUser hubUser;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private StudyGroup group;

    @Column(nullable = false)
    public LocalDateTime joinedAt = LocalDateTime.now();

    @Column(nullable = false)
    public String role;

    public Membership() {}

    public Membership(HubUser hubUser, StudyGroup group, String role) {
        this.id = new MembershipId(hubUser.getId(), group.getId());
        this.hubUser = hubUser;
        this.group = group;
        this.role = role;
        this.joinedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return Objects.equals(id, that.id) && Objects.equals(hubUser, that.hubUser) && Objects.equals(group, that.group) && Objects.equals(joinedAt, that.joinedAt) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hubUser, group, joinedAt, role);
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", hubUser=" + hubUser +
                ", group=" + group +
                ", joinedAt=" + joinedAt +
                ", role='" + role + '\'' +
                '}';
    }
}
