package com.example.onBoarding_syst.controller;

public class Risposta {
	private String token;
	
	public Risposta(String token) {
		this.token = token;
	}
	
	public String getToken() {
        return token;
    }
	
	public void setToken(String token) {
        this.token = token;
    }
}