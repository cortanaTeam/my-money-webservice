package com.vn.hcmute.team.cortana.mymoney.bean;

public class Wallet {
	private String id;
	private String userid;
	private String walletName;
	private String money;
	private String currencyUnit;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Wallet() {
		this.id="";
		this.userid="";
		this.walletName="";
		this.money="";
		this.currencyUnit="";
		this.token="";
	}
	public Wallet(String id, String userid,String walletName,String money,String currencyUnit,String token) {
		this.id=id;
		this.userid=userid;
		this.walletName=walletName;
		this.money=money;
		this.currencyUnit=currencyUnit;
		this.token=token;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWalletName() {
		return walletName;
	}
	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCurrencyUnit() {
		return currencyUnit;
	}
	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}
	
	@Override
	public String toString() {
		return "Wallet[id= "+id+",userid= "+userid+",walletname="+
				walletName+",money="+money+",curencyUnit="+currencyUnit+"]";
	}
	
	
}
