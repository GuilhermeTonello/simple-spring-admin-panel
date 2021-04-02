package com.gui.adminpanel.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity
@Table(name = "users", 
	uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email", "rg", "cpf"}, name = "user_unique_fields_check")
)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "seq_user_id", sequenceName = "seq_user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_id")
	private Long id;
	
	@NotBlank(message = "Username is empty")
	@NotNull
	@Size(max = 255, message = "Username name must be between 1 and 255 characters")
	private String username;
	
	@NotBlank(message = "Password is empty")
	@NotNull
	@Size(max = 255, message = "Password must be between 1 and 255 characters")
	private String password;
	
	@NotBlank(message = "First name is empty")
	@NotNull
	@Size(max = 255, message = "First name must be between 1 and 255 characters")
	private String firstName;
	
	@NotBlank(message = "Last name is empty")
	@NotNull
	@Size(max = 255, message = "Last name must be between 1 and 255 characters")
	private String lastName;
	
	@Email(message = "Email is invalid")
	@NotBlank(message = "Email is empty")
	@Size(max = 255, message = "Email must be between 1 and 255 characters")
	@NotNull
	private String email;
	
	@NotBlank(message = "RG is empty")
	@Size(max = 30, message = "Email must be between 1 and 30 characters")
	@NotNull
	private String rg;
	
	@CPF(message = "CPF is invalid")
	@NotBlank(message = "CPF is empty")
	@NotNull
	private String cpf;
	
	@NotBlank(message = "Phone number is empty")
	@Size(max = 30, message = "Phone number must be between 1 and 30 characters")
	@NotNull
	private String phone;
	
	@NotBlank(message = "Gender field is empty")
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Past(message = "Date of birth is invalid")
	@NotNull(message = "Date of birth is empty")
	private Date dateOfBirth;
	
	@NotNull(message = "Salary is empty")
	@Max(value = 999999999, message = "Salary too high")
	@Min(value = 0, message = "Salary too low")
	private BigDecimal salary;
	
	@Valid
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	@NotNull
	private Address address;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getCompleteName() {
		return String.format("%s %s", getFirstName(), getLastName());
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public BigDecimal getSalary() {
		return salary;
	}
	
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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

}
