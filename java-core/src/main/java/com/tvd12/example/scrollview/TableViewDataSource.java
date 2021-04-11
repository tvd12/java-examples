package com.tvd12.example.scrollview;

public interface TableViewDataSource {

	int numberOfCellsInTableView(TableView tableView);

	TableViewCell tableCellAtIndex(TableView tableView, int idx);
	
	default Size tableCellSizeForIndex(TableView table, int idx) {
	    return cellSizeForTable(table);
	}

	default Size cellSizeForTable(TableView table) {
	    return Size.ZERO;
	}

}
