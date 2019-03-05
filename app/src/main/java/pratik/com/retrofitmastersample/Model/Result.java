package pratik.com.retrofitmastersample.Model;

import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("name")
	private String name;

	@SerializedName("alpha3_code")
	private String alpha3Code;

	@SerializedName("alpha2_code")
	private String alpha2Code;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAlpha3Code(String alpha3Code){
		this.alpha3Code = alpha3Code;
	}

	public String getAlpha3Code(){
		return alpha3Code;
	}

	public void setAlpha2Code(String alpha2Code){
		this.alpha2Code = alpha2Code;
	}

	public String getAlpha2Code(){
		return alpha2Code;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"name = '" + name + '\'' + 
			",alpha3_code = '" + alpha3Code + '\'' + 
			",alpha2_code = '" + alpha2Code + '\'' + 
			"}";
		}
}