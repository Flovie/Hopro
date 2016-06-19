package xml.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import horiversumObjects.User;

public class UserMapAdapter extends XmlAdapter<UserMapAdapter.AdaptedList,Map<String,User>>{
	
	public static class AdaptedList{
		@XmlJavaTypeAdapter(UserAdapter.class)
		public List<User> user = new ArrayList<User>();
	}

	@Override
	public AdaptedList marshal(Map<String, User> arg0) throws Exception {
		AdaptedList result = new AdaptedList();
		result.user.addAll(arg0.values());
		Collections.sort(result.user);
		return result;
	}

	@Override
	public Map<String, User> unmarshal(AdaptedList v) throws Exception {
		Map<String,User> userMap = new HashMap<String,User>();
		if(v!=null){
			for(User u: v.user){
				userMap.put(u.getName(), u);
			}
		}				
		return userMap;
	}

}
