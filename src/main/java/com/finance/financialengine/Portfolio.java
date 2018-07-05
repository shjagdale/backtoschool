package com.finance.financialengine;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

class Portfolio {
	
	int risk = 5; // Default risk
	int allocations [] = new int [5]; // These are dollar amount allocation across different categories - NOT % allocation
	int netAmountInvested = 20000; // Default investment
	String userName = "Shashikant Jagdale";
	// gather the current risk factor and amount investment from backend - maybe database or some other entity
	// for simplification, above part is left out an default values are used
	
	// Here the default portfolio should be read from master source i.e. database
	// Read from backend and create corresponding java object for review, and any transactions
	// For now, update it with static values

	public Portfolio () {
		
	}
	public Portfolio (int risk, int amountInvested){ 	 
		if(risk > 0 || risk < 10)
			this.risk = risk;
		if(amountInvested > 0)
			this.netAmountInvested = amountInvested;
		allocations = RiskAnalyzer.getInstance().returnAllocation(--risk, netAmountInvested);	 
	}
	public Portfolio (int risk){ 	 
		if(risk > 0 || risk < 10)
			this.risk = risk;
		allocations = RiskAnalyzer.getInstance().returnAllocation(--risk, netAmountInvested);	 
	}

	public int getRisk() {
		return risk;
	}

	public void setRisk(int risk) {
		this.risk = risk;
	}

	public int[] getAllocations() {
		return allocations;
	}

	public void setAllocations(int[] allocations) {
		this.allocations = allocations;
	}

	public int getNetAmountInvested() {
		return netAmountInvested;
	}

	public void setNetAmountInvested(int netAmountInvested) {
		this.netAmountInvested = netAmountInvested;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int AnalyzeUserInputs(int[] myInputs) {
		risk = RiskAnalyzer.getInstance().AnalyzeUserInputs(myInputs,netAmountInvested);
		setRisk(risk);
		setAllocations(myInputs);
		return risk;
	}
	public void commit() {
		// write to backend database and complete the transaction
	}
}
