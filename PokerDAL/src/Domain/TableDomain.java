package Domain;

import java.util.UUID;

import org.hibernate.annotations.Table;

@Table(appliesTo="MOPOKERTABLE")
public class TableDomain {
	private UUID tableId;
	private int numOfPlayers;
	
	public TableDomain(){
		
	}
}
