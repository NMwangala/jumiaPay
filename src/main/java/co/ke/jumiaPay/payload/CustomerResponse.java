package co.ke.jumiaPay.payload;

import java.util.List;

import lombok.Data;

@Data
public class CustomerResponse {
	public List<CustomerDto> customerData;
	public int pageNo;
	public int pageSize;
	public Long totalElements;
	public int totalPages;
	public boolean last;
}
