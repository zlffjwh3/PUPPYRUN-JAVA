package user.model.vo;

import java.sql.Date;

public class Dog {
	private int dogCode;
	private String dogId;
	private String dogName;
	private String dogBreed;
	private char dogGender;
	private int dogAge;
	private float dogWeight;
	
	public Dog() {}
	
	// getter/setter
	public int getDogCode() {
		return dogCode;
	}

	public void setDogCode(int dogCode) {
		this.dogCode = dogCode;
	}
	
	public String getDogId() {
		return dogId;
	}

	public void setDogId(String dogId) {
		this.dogId = dogId;
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

	public float getDogWeight() {
		return dogWeight;
	}

	public void setDogWeight(float dogWeight) {
		this.dogWeight = dogWeight;
	}

	@Override
	public String toString() {
		return "Dog [dogCode=" + dogCode + ", dogId=" + dogId + ", dogName=" + dogName + ", dogBreed=" + dogBreed
				+ ", dogGender=" + dogGender + ", dogAge=" + dogAge + ", dogWeight=" + dogWeight + "]";
	}
}
