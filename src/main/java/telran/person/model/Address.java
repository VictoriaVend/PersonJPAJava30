package telran.person.model;


import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Builder
@Embeddable//для мэпинга класса в базу данных
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String city;
	String street;
	int building;

}
