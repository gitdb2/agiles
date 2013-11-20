package edu.ncsu.monopoly.logic.cell.visitor.property.collector;

import edu.ncsu.monopoly.logic.cell.PropertyCell;
import edu.ncsu.monopoly.logic.cell.RailRoadCell;
import edu.ncsu.monopoly.logic.cell.UtilityCell;

public interface IVisitorCollectCells {
	void visit(PropertyCell visitor);
	void visit(RailRoadCell visitor);
	void visit(UtilityCell visitor);
}
