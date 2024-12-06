package sg.edu.nus.iss.vttp5a_ssf_day17l.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {
    
    private Integer id;

    @NotEmpty(message = "Full name is mandatory")
    @Size(min = 5, max = 15, message = "Full name must be between 5 to 15 characters")
    private String fullName;

    @Email(message="Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Phone number must starts with 8 or 9 follow by 7 digits")
    private String phone;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    public Person() {
    }

    public Person(Integer id, String fullName, String email, String phone, Date dob) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone + ", dob=" + dob
                + "]";
    }
    
}
