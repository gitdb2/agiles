package edu.ncsu.monopoly.logic.cell.visitor.property.collector;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.monopoly.logic.cell.PropertyCell;
import edu.ncsu.monopoly.logic.cell.RailRoadCell;
import edu.ncsu.monopoly.logic.cell.UtilityCell;


public class VisitorCollectCells implements IVisitorCollectCells{
	List<PropertyCell> properties = new ArrayList<PropertyCell>();
	List<RailRoadCell> railRoads = new ArrayList<RailRoadCell>();
	List<UtilityCell> utilities = new ArrayList<UtilityCell>();
	
	public List<PropertyCell> getProperties() {
		return properties;
	}
	
	
	
	public List<RailRoadCell> getRailRoads() {
		return railRoads;
	}



	public List<UtilityCell> getUtilities() {
		return utilities;
	}



	public void visit(PropertyCell visitor){
		properties.add( (PropertyCell) visitor);
	}
	
	public void visit(RailRoadCell visitor){
		railRoads.add( (RailRoadCell) visitor);
	}
	public void visit(UtilityCell visitor){
		utilities.add( (UtilityCell) visitor);
	}
}
