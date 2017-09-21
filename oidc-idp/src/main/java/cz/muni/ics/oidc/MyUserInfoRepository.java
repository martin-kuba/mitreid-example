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
		//the standard set of claims as defined in http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims
		ui.setSub(username);  // Subject - Identifier for the End-User at the Issuer.
		ui.setName("Josef Novák"); // End-User's full name
		ui.setGivenName("Pepa"); //  Given name(s) or first name(s) of the End-User
		ui.setFamilyName("Novák"); // Surname(s) or last name(s) of the End-User
		ui.setMiddleName("W."); //  Middle name(s) of the End-User
		ui.setNickname("Pepan"); // Casual name of the End-User
		ui.setPreferredUsername(username); // Shorthand name by which the End-User wishes to be referred to at the RP
		ui.setProfile("https://plus.google.com/111085807076049784065"); //  URL of the End-User's profile page.
		ui.setPicture("https://secure.gravatar.com/avatar/f320c89e39d15da1608c8fc31210b8ca"); // URL of the End-User's profile picture.
		ui.setWebsite("http://www.seznam.cz/"); // URL of the End-User's Web page or blog.
		ui.setEmail(username+"@muni.cz"); // End-User's preferred e-mail address.
		ui.setEmailVerified(true); // True if the End-User's e-mail address has been verified
		ui.setGender("male"); // End-User's gender. Values defined by this specification are female and male.
		ui.setBirthdate("1975-01-01");//End-User's birthday, represented as an ISO 8601:2004 [ISO8601‑2004] YYYY-MM-DD format.
		ui.setZoneinfo(TimeZone.getDefault().getID());//String from zoneinfo [zoneinfo] time zone database, For example, Europe/Paris
		ui.setLocale("cs-CZ"); //  For example, en-US or fr-CA.
		ui.setPhoneNumber("+420 603123456"); //[E.164] is RECOMMENDED as the format, for example, +1 (425) 555-121
		ui.setPhoneNumberVerified(true); // True if the End-User's phone number has been verified
		ui.setUpdatedTime(Long.toString(System.currentTimeMillis()/1000L));// value is a JSON number representing the number of seconds from 1970-01-01T0:0:0Z as measured in UTC until the date/time
		Address address = new DefaultAddress();
		address.setStreetAddress("Šumavská 15");
		address.setLocality("Brno");
		address.setPostalCode("61200");
		address.setCountry("Czech Republic");
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
