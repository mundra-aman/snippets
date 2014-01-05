package info.aea.drawer;

public class NavDrawerItem {
	
	private String title;
	private String tag;
	private int icon;
	private String count = "0";
	// boolean to set visibility of the counter
	private boolean isCounterVisible = false;
	
	public NavDrawerItem(){}

	public NavDrawerItem(String title, String tag, int icon){
		this.title = title;
		this.tag = tag;
		this.icon = icon;
	}
	
	public NavDrawerItem(String title, String tag, int icon, boolean isCounterVisible, String count){
		this.title = title;
		this.tag = tag;
		this.icon = icon;
		this.isCounterVisible = isCounterVisible;
		this.count = count;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getTag(){
		return this.tag;
	}
	
	public int getIcon(){
		return this.icon;
	}
	
	public String getCount(){
		return this.count;
	}
	
	public boolean getCounterVisibility(){
		return this.isCounterVisible;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setTag(String tag){
		this.tag = tag;
	}
	
	public void setIcon(int icon){
		this.icon = icon;
	}
	
	public void setCount(String count){
		this.count = count;
	}
	
	public void setCounterVisibility(boolean isCounterVisible){
		this.isCounterVisible = isCounterVisible;
	}
}
