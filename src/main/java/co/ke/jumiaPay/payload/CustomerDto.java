package co.ke.jumiaPay.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	public String name;
	public String phone;
	public String country;
	public String state;
	
}
