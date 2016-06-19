package xml.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import horiversumObjects.User;
import xml.XmlUser;

public class UserAdapter extends XmlAdapter<XmlUser,User> {
	
	public XmlUser marshal(User u) throws Exception {
		XmlUser xml = new XmlUser();
		xml.transferDataToXml(u);
		return xml;
	}
	

	@Override
	public User unmarshal(XmlUser xml) throws Exception {
		User u = User.getInstance(xml.userId);
		u.transferDataFromXml(xml);
		return u;
	}

}
