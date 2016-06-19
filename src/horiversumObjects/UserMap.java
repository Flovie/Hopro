package horiversumObjects;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.adapters.UserMapAdapter;

@XmlRootElement(name="userMap")
public class UserMap {
	
	@XmlAttribute(name="created", required=false)
	Calendar lastSaved;
	
	@XmlElement(name="users")
	@XmlJavaTypeAdapter(UserMapAdapter.class)	
	private Map<String,User> us = new HashMap<String,User>();
	
	public void addUser(User u){
		this.us.put(u.getName(), u);
	}
	
	public boolean userExists(String id){
		String wildcard = "*";
		if (id.contains(wildcard)){
			id = id.substring(0, id.indexOf("*"));
			boolean found = false;
			for (String key: this.us.keySet()){
				if(key.contains(id)){
					found = true;
				}
			}
			if(found){
				return true;
			}else{
				return false;
			}
		}else{
			return this.us.containsKey(id);
		}		
	}
	
	public User getUser(String id){
		id = id.replace("...", "*");
		if (userExists(id)){
			if(id.contains("*")){
				id = id.substring(0, id.indexOf("*"));
				boolean foundCorrectName = false;
				String oldId = id + "*";
				for(String key: us.keySet()){
					if((key.contains(id)) && !(key.contains("*"))){
						id = key;
						foundCorrectName=true;
						break;
					}
				}
				if (foundCorrectName){
					this.removeUser(oldId);
				}else{
					id = oldId;
				}
			}						
			return this.us.get(id);
		}else{
			return User.getInstance(id);
		}		
	}
	
	public void removeUser(String id){
		if (us.containsKey(id)){
			this.us.remove(id);	
		}		
	}
	
	public void updateLastSaved(){
		this.lastSaved = Calendar.getInstance();
	}
	
	public String toString(){
		return this.us.values().toString();
	}

}
