package ScriptKiddie.ScriptKidderExclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "status", "data" })
public class Employee {
	@JsonProperty("status")
	private String status;
	@JsonProperty("data")
	private Data data;

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("data")
	public Data getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(Data data) {
		this.data = data;
	}

}
