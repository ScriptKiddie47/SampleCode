package ScriptKiddie.ScriptKidderExclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "salary", "age", "id" })
public class Data {
	@JsonProperty("name")
	private String name;
	@JsonProperty("salary")
	private String salary;
	@JsonProperty("age")
	private String age;
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("salary")
	public String getSalary() {
		return salary;
	}

	@JsonProperty("salary")
	public void setSalary(String salary) {
		this.salary = salary;
	}

	@JsonProperty("age")
	public String getAge() {
		return age;
	}

	@JsonProperty("age")
	public void setAge(String age) {
		this.age = age;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}
}
