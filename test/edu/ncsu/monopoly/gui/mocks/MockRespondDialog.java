package edu.ncsu.monopoly.gui.mocks;

import edu.ncsu.monopoly.interfaces.RespondDialog;
import edu.ncsu.monopoly.logic.TradeDeal;

public class MockRespondDialog implements RespondDialog {
    public MockRespondDialog(TradeDeal deal) {
    }

    public boolean getResponse() {
        return true;
    }
}
