package de.hsb.shop.model;

public enum Kreditkartentyp {

	MASTER ("Mastercard"), VISA ("Visa"), AMEX ("American Express");
	
	private final String label;
	private Kreditkartentyp(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}

}
