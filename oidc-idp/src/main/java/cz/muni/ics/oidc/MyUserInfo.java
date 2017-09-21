package cz.muni.ics.oidc;

import com.google.gson.JsonObject;
import org.mitre.openid.connect.model.DefaultUserInfo;

/**
 * Implements UserInfo by inheriting from DefaultUserInfo and adds some custom claims.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@SuppressWarnings("WeakerAccess")
public class MyUserInfo extends DefaultUserInfo {

	private String myclaim1;
	private String myclaim2;

	public String getMyclaim1() {
		return myclaim1;
	}

	public void setMyclaim1(String myclaim1) {
		this.myclaim1 = myclaim1;
	}

	public String getMyclaim2() {
		return myclaim2;
	}

	public void setMyclaim2(String myclaim2) {
		this.myclaim2 = myclaim2;
	}

	@Override
	public JsonObject toJson() {
		JsonObject obj = super.toJson();
		obj.addProperty("myclaim1", this.getMyclaim1());
		obj.addProperty("myclaim2", this.getMyclaim2());
		return obj;
	}
}
