package info.aea.database;

public class Algorithms_Table {
	private String  AlgoID;
	private String AlgoTitle;
	

	// constructor
    public Algorithms_Table(String algoID, String algoTitle){
        this.AlgoID= algoID;
        this.AlgoTitle = algoTitle;
             }

    public Algorithms_Table(){

    }


    public void setAlgoID(String id){
        this.AlgoID = id;
    }
    
    public void setAlgoTitle(String title){
        this.AlgoTitle = title;
    }


    
    
    public String getAlgoID(){
        return this.AlgoID;
    }
    
    public String getAlgoTitle(){
        return this.AlgoTitle;
    }

    
 }
