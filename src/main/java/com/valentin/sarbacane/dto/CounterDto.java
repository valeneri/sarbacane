package com.valentin.sarbacane.dto;

public class CounterDto {

	private Integer valid;
	private Integer invalid;
	
	public CounterDto() {}
	
	public CounterDto(Integer valid, Integer invalid) {
		super();
		this.valid = valid;
		this.invalid = invalid;
	}
	/**
	 * @return the valid
	 */
	public Integer getValid() {
		return valid;
	}
	/**
	 * @param valid the valid to set
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	/**
	 * @return the invalid
	 */
	public Integer getInvalid() {
		return invalid;
	}
	/**
	 * @param invalid the invalid to set
	 */
	public void setInvalid(Integer invalid) {
		this.invalid = invalid;
	}
	
	
}
