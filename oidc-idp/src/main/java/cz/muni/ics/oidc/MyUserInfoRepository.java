package cz.muni.ics.oidc;

import org.mitre.openid.connect.model.Address;
import org.mitre.openid.connect.model.DefaultAddress;
import org.mitre.openid.connect.model.DefaultUserInfo;
import org.mitre.openid.connect.model.UserInfo;
import org.mitre.openid.connect.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Provides data about a user. Extends the default set with custom claims.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
public class MyUserInfoRepository implements UserInfoRepository {

	private final static Logger log = LoggerFactory.getLogger(MyUserInfoRepository.class);

	@Override
	public UserInfo getByUsername(String username) {
		log.info("getByUsername({})", username);
		MyUserInfo ui = new MyUserInfo();
		//the standard set of claims
		ui.setSub(username);
		ui.setPreferredUsername(username);
		ui.setEmail(username+"@muni.cz");
		ui.setEmailVerified(true);
		ui.setName("Josef Novák");
		ui.setFamilyName("Novák");
		ui.setMiddleName("W.");
		ui.setGivenName("Pepa");
		ui.setNickname("Pepan");
		ui.setGender("male");
		ui.setPhoneNumber("603123456");
		ui.setPhoneNumberVerified(true);
		ui.setProfile("profil pepy");
		ui.setLocale("cs");
		ui.setBirthdate("1975-01-01");
		ui.setPicture("https://secure.gravatar.com/avatar/f320c89e39d15da1608c8fc31210b8ca");
		ui.setWebsite("http://www.seznam.cz/");
		ui.setUpdatedTime(DateTimeFormatter.ISO_DATE_TIME.format(ZonedDateTime.now()));
		ui.setZoneinfo(TimeZone.getDefault().getID());
		//structured claim
		Address address = new DefaultAddress();
		address.setCountry("CZ");
		address.setLocality("Brno");
		address.setPostalCode("61200");
		address.setStreetAddress("Šumavská 15");
		ui.setAddress(address);
		//my extended claims
		ui.setMyclaim1("myclaim1 hodnota");
		ui.setMyclaim2("myclaim2 hodnota");
		return ui;
	}

	@Override
	public UserInfo getByEmailAddress(String email) {
		log.info("getByEmailAddress({})", email);
		return null;
	}
}
