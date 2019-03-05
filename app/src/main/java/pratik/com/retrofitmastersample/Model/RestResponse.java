package pratik.com.retrofitmastersample.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RestResponse{

	@SerializedName("result")
	private Result result;

	@SerializedName("messages")
	private List<String> messages;

	public void setResult(Result result){
		this.result = result;
	}

	public Result getResult(){
		return result;
	}

	public void setMessages(List<String> messages){
		this.messages = messages;
	}

	public List<String> getMessages(){
		return messages;
	}

	@Override
 	public String toString(){
		return 
			"RestResponse{" + 
			"result = '" + result + '\'' + 
			",messages = '" + messages + '\'' + 
			"}";
		}
}