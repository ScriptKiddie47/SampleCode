package ScriptKiddie.ScriptKidderExclude;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
"status",
"data"
})
public class EmployeePOJO {
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("data")
	private List<EmployeeDataPOJO> data = null;
	
	@JsonProperty("status")
	public String getStatus() {
	return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
	this.status = status;
	}

	@JsonProperty("data")
	public List<EmployeeDataPOJO> getData() {
	return data;
	}

	@JsonProperty("data")
	public void setData(List<EmployeeDataPOJO> data) {
	this.data = data;
	}
}
