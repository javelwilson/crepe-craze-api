package com.javelwilson.crepecrazeapi.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Crepe_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date placedAt;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Zip code is required")
    private String zip;

    @CreditCardNumber(message = "Invalid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    @Column(name = "cc_cvv")
    private String ccCVV;

    @ManyToOne
    private User user;

    @ManyToMany(targetEntity = Crepe.class)
    private List<Crepe> crepes = new ArrayList<>();

    public void addDesign(Crepe design) { this.crepes.add(design);
    }
    @PrePersist
    public void placedAt() {
        this.placedAt = new Date();
    }
}
