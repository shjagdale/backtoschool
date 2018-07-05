package com.finance.financialengine;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
	
	/**
	 * Root resource (exposed at "myportfolio" path)
	 */
	@Path("myportfolio")
	public class MyPortfolioManager {
		PortfolioManager _mgr = null;
	    /**
	     * Method handling HTTP GET requests. The returned object will be sent
	     * to the client as "text/plain" media type.
	     *
	     * @return String that will be returned as a text/plain response.
	     */  
		// INPUT#1 in the problem statement - get the portfolio with expected risk
		// Amount invested is currently hardcoded in the portfolio 
	    // Get primary portfolio information
		// OUTPUT#1 is the portfolio recommended as per expected risk factor
		// return as XML for client consumption
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("{accountNo}")
	    public Portfolio getMyAccount(@PathParam("accountNo") int accountNo, @QueryParam("risk") int risk, @QueryParam("assets") int assets) {
			return _mgr.getMyAssets(risk, accountNo, assets);
	    	
	    }	
	    // INPUT #2 Clients can suggest a new allocation and analyze
	    // OUTPUT #2 is a new allocation which determines the closes risk factor and # of transactions for specific risk factor
	    @PUT
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Path ("{accountNo}/suggestallocation")
	    public Portfolio suggestNewAllocation(@PathParam("accountNo") int accountNo,
	    									@QueryParam("risk") int risk,
	    									@QueryParam("assets") int assets,
	    									@QueryParam("Bonds") int Bonds,
	    									@QueryParam("LargeCap") int LargeCap,
	    									@QueryParam("MidCap") int MidCap,
	    									@QueryParam("Foreign") int Foreign,
	    									@QueryParam("SmallCap") int SmallCap)
	    	 {
	    	 if((Bonds + LargeCap + MidCap + Foreign + SmallCap) != assets)
	    	 {
	    		 //System.out.println("The allocations do not sum up to the asset value");
	    		 return null;
	    	 }
	    	 else
	    	 {
	    		 int[] intArray = {Bonds, LargeCap, MidCap, Foreign, SmallCap};
	    		 return _mgr.suggestNewAllocation(intArray, accountNo, risk, assets);
	    	 }
	     }
	    public MyPortfolioManager () {
	    	_mgr = new PortfolioManager();
	    } 
}
