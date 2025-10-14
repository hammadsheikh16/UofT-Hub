package com.campushub.model;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Entity

public class HubUser implements UserDetails{

    @Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "user_id_seq"
    )
    private Integer id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false
    )
    private String email;

    @Column(
            nullable = true
    )
    private String profileImageId;

    @Column(
            nullable = false
    )
    private Integer age;

    @Column(
            nullable = false
    )
    private String password;

    public HubUser(){

    }

    public HubUser(
            Integer id,
            String name,
            String email,
            Integer age,
            String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public HubUser(
            Integer id,
            String name,
            String email,
            Integer age,
            String password,
            String profileImageId
    ){
        this(id, name, email, age, password);
        this.profileImageId = profileImageId;
    }

    public HubUser(String name,
                   String email,
                   String password,
                   Integer age){
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageId() {
        return profileImageId;
    }

    public void setProfileImageId(String profileImageId) {
        this.profileImageId = profileImageId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HubUser hubUser = (HubUser) o;
        return Objects.equals(id, hubUser.id) && Objects.equals(name, hubUser.name) && Objects.equals(email, hubUser.email) && Objects.equals(profileImageId, hubUser.profileImageId) && Objects.equals(age, hubUser.age) && Objects.equals(password, hubUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, profileImageId, age, password);
    }

    @Override
    public String toString() {
        return "HubUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", profileImageId='" + profileImageId + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
