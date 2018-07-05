package com.finance.financialengine;

public class PortfolioManager {

	public PortfolioManager() {
		
	}
	public Portfolio getMyAssets(int risk, int accountNo) {
		if (accountNo < 0 || accountNo > 1) {
			return null; // not serving any additional accounts for now...this could be time and load based, keeping it simple for now....
		}
		return (new Portfolio(risk)); // Return a default portfolio with specified risk
	}
	public Portfolio getMyAssets(int risk, int accountNo, int assets) {
		if (accountNo < 0 || accountNo > 1) {
			return null; // not serving any additional accounts for now...this could be time and load based, keeping it simple for now....
		}
		// only risk is used, invested amount is default ///
			return(new Portfolio(risk, assets)); // Return a default portfolio with specified risk and assets
	}
	public Portfolio suggestNewAllocation(int[] myInputs, int accountNo, int risk, int assets) {
		if (accountNo < 0 || accountNo > 1) {
			return null; // not serving any additional accounts for now...this could be time and load based, keeping it simple for now....
		}
		Portfolio account = new Portfolio(risk, assets);
		int newrisk = account.AnalyzeUserInputs(myInputs);
        System.out.println("Output Risk:"+ newrisk);
        account.setAllocations(RiskAnalyzer.getInstance().returnAllocation(newrisk - 1, assets));
		return account;	 
	}
}

