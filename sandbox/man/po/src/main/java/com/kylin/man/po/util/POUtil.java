package com.kylin.man.po.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.kylin.man.po.Event;
import com.kylin.man.po.Friend;
import com.kylin.man.po.Man;
import com.kylin.man.po.Pet;
import com.kylin.man.po.Property;
import com.kylin.man.po.UserCard;
import com.kylin.man.po.Wife;

public class POUtil {

	public static Man newMan() {
		
		Man man = new Man();
		man.setName("Kobe Bryant");
		
		Calendar createdDate = Calendar.getInstance();
		createdDate.setTime(new Date());
		man.setCreatedDate(createdDate);
		
		UserCard card = new UserCard();
		card.setCardNumber("1102200000000000");
		man.setUserCard(card);
		
		Wife wife = new Wife();
		wife.setName("Vanessa Bryant");
		UserCard wcard = new UserCard();
		wcard.setCardNumber("1102200000000001");
		wife.setUserCard(wcard);
		List<Pet> pets = new ArrayList<Pet>();
		Pet dog = new Pet();
		dog.setName("Akitas");
		List<Property> dprops = new ArrayList<Property> ();
		Property p1 = new Property();
		p1.setKey("color");
		p1.setValue("brown");
		dprops.add(p1);
		Property p2 = new Property();
		p2.setKey("weight");
		p2.setValue("18");
		dprops.add(p2);
		Property p3 = new Property();
		p3.setKey("gender");
		p3.setValue("male");
		dprops.add(p3);
		dog.setProperties(dprops);
		pets.add(dog);
		Pet cat = new Pet();
		dog.setName("Akitas");
		List<Property> cprops = new ArrayList<Property> ();
		Property p4 = new Property();
		p4.setKey("color");
		p4.setValue("white");
		cprops.add(p4);
		Property p5 = new Property();
		p5.setKey("weight");
		p5.setValue("3");
		cprops.add(p5);
		Property p6 = new Property();
		p6.setKey("gender");
		p6.setValue("male");
		cprops.add(p6);
		cat.setProperties(cprops);
		pets.add(cat);
		wife.setPets(pets);
		man.setWife(wife);
		
		List<Event> events = new ArrayList<Event>();
		Event e1 = new Event();
		e1.setName("Write Book");
		List<Property> e1props = new ArrayList<Property> ();
		Property p7 = new Property();
		p7.setKey("bookName");
		p7.setValue("Java EE Application Cluster");
		e1props.add(p7);
		Property p8 = new Property();
		p8.setKey("deadline");
		p8.setValue("2013-8");
		e1props.add(p8);
		Property p9 = new Property();
		p9.setKey("status");
		p9.setValue("Pending");
		e1props.add(p9);
		e1.setProperties(e1props);
		events.add(e1);
		Event e2 = new Event();
		e2.setName("Do Sports");
		List<Property> e2props = new ArrayList<Property> ();
		Property p10 = new Property();
		p10.setKey("name");
		p10.setValue("BasketBall");
		e2props.add(p10);
		Property p11 = new Property();
		p11.setKey("start date");
		p11.setValue("2013-4");
		e2props.add(p11);
		Property p12 = new Property();
		p12.setKey("status");
		p12.setValue("Scheduled");
		e2props.add(p12);
		e2.setProperties(e2props);
		events.add(e2);
		Event e3 = new Event();
		e3.setName("Travel");
		List<Property> e3props = new ArrayList<Property> ();
		Property p13 = new Property();
		p13.setKey("plan");
		p13.setValue("go YunNan");
		e3props.add(p13);
		Property p14 = new Property();
		p14.setKey("start date");
		p14.setValue("2013-4");
		e3props.add(p14);
		Property p15 = new Property();
		p15.setKey("status");
		p15.setValue("Scheduled");
		e3props.add(p15);
		e3.setProperties(e3props);
		events.add(e3);
		man.setEvents(events);
		
		List<Friend> friends = new ArrayList<Friend>();
		Friend f1 = new Friend();
		f1.setName("Jerry West");
		UserCard uc1 = new UserCard();
		uc1.setCardNumber("1102200000000003");
		f1.setUserCard(uc1);
		friends.add(f1);
		Friend f2 = new Friend();
		f2.setName("Magic Johnson");
		UserCard uc2 = new UserCard();
		uc2.setCardNumber("1102200000000004");
		f2.setUserCard(uc2);
		friends.add(f2);
		Friend f3 = new Friend();
		f3.setName("Kareem-Adual Jabber");
		UserCard uc3 = new UserCard();
		uc3.setCardNumber("1102200000000005");
		f3.setUserCard(uc3);
		friends.add(f3);
		man.setFriends(friends);
		
		return man;
	}
	
	public static String convertToString(Man man){
		
		StringWriter writer = new StringWriter();
		try {
			JAXBUtil.getInstance().getMarshaller().marshal(man, writer);
		} catch (JAXBException e) {
			throw new ManMarshallerException(e);
		}
		return writer.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(POUtil.convertToString(POUtil.newMan()));
	}
}
