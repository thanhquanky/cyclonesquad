package edu.gatech.cs2340.whereismystuff.models;

import java.util.ArrayList;

public abstract class Item {
	public String name;
	public User owner;
	public ArrayList<String> tags;
	private String location; 
	
	
public Item(String name, User owner, String location, String...s){
	this.name=name;
	this.owner=owner;
	this.location=location;
	for(String st: s)
		tags.add(st);
}

public String getName() {
	return name;
}

/**
 * @param name
 */
public void setName(String name) {
	this.name = name;
}

/** 
 * @return current owner of object
 */
public User getOwner() {
	return owner;
}


/**
 * @return tags decribing object
 */
public ArrayList<String> getTags() {
	return tags;
}

/**
 * @param tags describing the object
 */
public void setTags(ArrayList<String> tags) {
	this.tags = tags;
}

/**
 * @return location
 */
public String getLocation() {
	return location;
}

/**
 * @param location
 */
public void setLocation(String location) {
	this.location = location;
}




}
