package de.hsb.shop.model;

public enum Creditcardtype {

	MASTER ("Mastercard"), VISA ("Visa"), AMEX ("American Express");
	
	private final String label;
	private Creditcardtype(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}

}
