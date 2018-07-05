package com.finance.financialengine;

 public class RiskAnalyzer 
{
     int  [][] InvestmentOptions = {{80, 20, 0, 0, 0},
            {70, 15, 15, 0,0},
            {60, 15, 15, 10,0},
            {50, 20, 20, 10, 0},
            {40, 20, 20, 20, 0},
            {35, 25, 5, 30, 5},
            {20, 25, 25, 25, 5},
            {10, 20, 40, 20, 10},
            {5, 15, 40, 25, 15},
            {0, 5, 25, 30, 40}}; 
    // This is two dimensional array outlining current investment options and its breakup across different risk levels
    // It can be further embellished into a ENUM array for more clarity and readable code. Keeping it simple here.
    
     
    static RiskAnalyzer analyzer = null;
    
    public static RiskAnalyzer getInstance() {
        if(analyzer == null)
        	analyzer = new RiskAnalyzer();
        return analyzer;
    }
    // This function analyzes user inputs based on default recommendations and determines the risk factor involved
    public  int AnalyzeUserInputs(int [] uservalues, int asset)
    {
    	int comparisonarray[] = new int[10];
    	int transactions[] = new int[10]; //
    	
    	
    	
    	// Convert the allocation in $$ to % for risk analysis
    	for (int i = 0; i < uservalues.length; i++) {
    		uservalues[i] = (uservalues[i] * 100 )/ asset;
    	}
    	
    	// Go across the two dimensional investment options (5 x 10) to see what is the closest match
    	// Logic below calculates the difference of each risk option and sums it up for risk by risk comparision
    	// one with the lowest sum is a closest match
        for(int indexX = 0; indexX < 10; indexX++)
        {
            for(int indexY = 0; indexY < 5; indexY++)
            {
                int difference = Math.abs(InvestmentOptions[indexX][indexY] - uservalues[indexY]);
                if(difference != uservalues[indexY])
                {
                    transactions[indexX]= transactions[indexX] + 1;
                    // also count the # of transactions per user input and corresponding risk
                    // this value is NOT used anywhere else but used for analysis purposes only
                }
                comparisonarray[indexX]= comparisonarray[indexX] + difference; // store the sum of difference for all options for a specific risk 
            }  
        }
        int min = comparisonarray[0]; // start with first item in the list of differences summed up
        int risk = 0;
        
        for(int index = 0; index< comparisonarray.length; index++)
        {
            if(comparisonarray[index] < min)
            {
                min = comparisonarray[index];
                risk = index; // this is the closet risk index
            }
        }
        System.out.print(risk + " " + (risk + 1)); 
         // Advancing array index location to actual risk level i.e. index 0 implies risk 1
        return (risk + 1); // actual risk value = array index + 1
     }

    public   int[]  returnAllocation (int risk,int asset)
    {
        int[] allocations = new int [5];
        for(int index = 0; index < 5; index++)
        {
            allocations[index] = InvestmentOptions[risk][index] * (asset)/100;
        }
        return allocations;
    }
}  




