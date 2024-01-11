package com.brainwired.task.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "First name should not be empty")
    @Length(min = 3, max = 20, message = "First name should be between 2 and 21")
    @Pattern(regexp = "^[^0-9]+$", message = "Invalid value. Must not contain numbers.")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Column(name = "last_name")
    @Pattern(regexp = "^[^0-9]+$", message = "Invalid value. Must not contain numbers.")
    @Length(min = 3, max = 20, message = "Last name should be between 2 and 21")
    private String lastName;

    @NotNull(message = "please fill your date of birth")
    @Column(name = "date_of_birth")
    @Past(message = "Date must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotEmpty(message = "please check your address")
    @Length(min = 8, max = 100, message = "Address should be between 7 and 101")
    private String address;

    private boolean enable = true;
}
