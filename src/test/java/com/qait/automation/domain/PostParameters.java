package com.qait.automation.domain;

public class PostParameters {

	private String id;
	private String signature;
	private int allow_access;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getAllow_access() {
		return allow_access;
	}

	public void setAllow_access(int allow_access) {
		this.allow_access = allow_access;
	}

}
