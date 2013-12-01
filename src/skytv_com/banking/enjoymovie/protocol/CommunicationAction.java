package skytv_com.banking.enjoymovie.protocol;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * 一条消息，所有消息的父类。消息驱动模式
 * @author bankzhang
 *
 */
public class CommunicationAction implements Serializable{//abstract 
	int tag;
	String content = ""; //传递的内容
	int selected = -1;//for category and movie1-9,10,11
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	public static CommunicationAction generateConnectedAction(){
		CommunicationAction a = new CommunicationAction();
		a.setTag(ProtocolTag.TYPE_CONNECT_TAG);
		return a;
	}
	public static CommunicationAction generateTextInputAction(String text){
		CommunicationAction a = new CommunicationAction();
		a.setTag(ProtocolTag.TYPE_TEXT_INPUT_TAG);
		a.setContent(text);
		return a;
	}
	public static CommunicationAction generateVoiceInputAction(String voiceText){
		CommunicationAction a = new CommunicationAction();
		a.setTag(ProtocolTag.TYPE_VOICE_INPUT_TAG);
		a.setContent(voiceText);
		return a;
	}
	public static CommunicationAction generateSubmitAction(){
		CommunicationAction a = new CommunicationAction();
		a.setTag(ProtocolTag.TYPE_CLICK_SUBMIT_TAG);
		return a;
	}
	public static CommunicationAction generateCategoryAction(int selected){
		CommunicationAction a = new CommunicationAction();
		a.selected = selected;
		a.setTag(ProtocolTag.TYPE_CLICK_CATEGORY_TAG);
		return a;
	}
	public static CommunicationAction generateSelectMovieAction(int index){
		CommunicationAction a = new CommunicationAction();
		a.selected = index;
		a.setTag(ProtocolTag.TYPE_SELECT_MOVIE_TAG);
		return a;
	}
	
	
	public static JSONObject objectToJson(CommunicationAction action){
		JSONObject object = new JSONObject();
		try {
			object.put("tag", action.tag);
			object.put("content", action.content);
			object.put("selected", action.selected);
			Log.d("banking", "objectToJson"+object);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	public static CommunicationAction jsonToObject(JSONObject objectJson){
		CommunicationAction action = new CommunicationAction();
		try {
			action.setTag(objectJson.getInt("tag"));
			action.setContent(objectJson.getString("content"));
			action.setSelected(objectJson.getInt("selected"));
			Log.d("banking", "jsonToObject"+action.getTag()+" "+action.getContent());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return action;
	}
	
	/**
	 * 根据属性生成 jsonObject
	 */
	//public abstract void generateJsonObject();
	
	/**
	 * 根据jsonObject生成属性
	 */
	//public abstract void generateAttribute();
	
	
}
