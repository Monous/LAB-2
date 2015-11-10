package domain;

import java.util.UUID;

import org.hibernate.annotations.Table;






public class PlayerDomain {
	private String name;
	private UUID playerId;
	
	public PlayerDomain(){
		this.playerId = UUID.randomUUID();
	}
	
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void generateNewId(){
		this.playerId = UUID.randomUUID();
	}
}
