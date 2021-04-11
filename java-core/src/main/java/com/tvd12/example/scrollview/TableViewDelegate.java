package com.tvd12.example.scrollview;

public interface TableViewDelegate {

	void tableCellHighlight(TableView table, TableViewCell cell);

	void tableCellUnhighlight(TableView table, TableViewCell cell);

	void tableCellWillRecycle(TableView table, TableViewCell cell);

	void tableCellTouched(TableView tableView, TableViewCell _touchedCell);
	
	void scrollViewDidScroll(TableView tableView);

}
