package com.synergisticit.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
	
	@NotEmpty private String addressLine1;	
	@NotEmpty private String addressLine2;
	@NotEmpty private String city;
	@NotEmpty private String state;
	@NotEmpty private String country;
	@NotEmpty private String zip;
}
