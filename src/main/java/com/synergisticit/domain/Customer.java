package com.synergisticit.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long customerId;
	
	@NotEmpty
	private String customerName;
	
	@Enumerated(EnumType.STRING)
	private Gender customerGender;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate customerDOB;
	
	@NotEmpty
	private String customerMobileNo;
	
	@Embedded
	private Address customerAddress;
	
	private String customerSSN;
	
	@OneToMany(mappedBy="accountCustomer")
	private List<Account> customerAccounts = new ArrayList<>();
	
	@OneToOne
	private User user;

}
