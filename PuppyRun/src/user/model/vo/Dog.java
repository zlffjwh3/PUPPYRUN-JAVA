package user.model.vo;

import java.sql.Date;

public class Dog {
	private int dogCode;
	private int userId;
	private String dogName;
	private String dogBreed;
	private char dogGender;
	private int dogAge;
	private int dogWeight;
	
	public Dog() {}
	
	// getter/setter
	public int getDogCode() {
		return dogCode;
	}

	public void setDogCode(int dogCode) {
		this.dogCode = dogCode;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDogName() {
		return dogName;
	}

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}

	public String getDogBreed() {
		return dogBreed;
	}

	public void setDogBreed(String dogBreed) {
		this.dogBreed = dogBreed;
	}

	public char getDogGender() {
		return dogGender;
	}

	public void setDogGender(char dogGender) {
		this.dogGender = dogGender;
	}

	public int getDogAge() {
		return dogAge;
	}

	public void setDogAge(int dogAge) {
		this.dogAge = dogAge;
	}

	public int getDogWeight() {
		return dogWeight;
	}

	public void setDogWeight(int dogWeight) {
		this.dogWeight = dogWeight;
	}

	@Override
	public String toString() {
		return "Dog [dogCode=" + dogCode + ", userId=" + userId + ", dogName=" + dogName + ", dogBreed=" + dogBreed
				+ ", dogGender=" + dogGender + ", dogAge=" + dogAge + ", dogWeight=" + dogWeight + "]";
	}
}
