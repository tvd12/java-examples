package com.tvd12.example.scrollview;

import static com.tvd12.example.scrollview.Array.CC_INVALID_INDEX;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tvd12.ezyfox.io.EzyLists;

public class TableView extends ScrollView implements ScrollViewDelegate {

    protected TableViewCell _touchedCell;
    protected VerticalFillOrder _vordering;
    protected Set<Integer> _indices;
    protected List<Float> _vCellsPositions;
    protected List<TableViewCell> _cellsUsed;
    protected List<TableViewCell> _cellsFreed;
    protected TableViewDataSource _dataSource;
    protected TableViewDelegate _tableViewDelegate;
    protected Direction _oldDirection;
    protected boolean _isUsedCellsDirty;

    public TableView() {
        this._indices = new HashSet<>();
        this._vordering = VerticalFillOrder.BOTTOM_UP;
        this._vCellsPositions = new ArrayList<>();
        this._cellsUsed = new ArrayList<>();
        this._cellsFreed = new ArrayList<>();
    }

    public static TableView create(TableViewDataSource dataSource, Size size, Node container) {
        TableView table = new TableView();
        table.initWithViewSize(size, container);
        table.autorelease();
        table.setDataSource(dataSource);
        table._updateCellPositions();
        table._updateContentSize();

        return table;
    }

    protected boolean initWithViewSize(Size size, Node container) {
        if (super.initWithViewSize(size, container)) {
            setDirection(Direction.VERTICAL);

            setDelegate(this);
            return true;
        }
        return false;
    }

    public void setDataSource(TableViewDataSource dataSource) {
        this._dataSource = dataSource;
    }

    public VerticalFillOrder getVerticalFillOrder() {
        return _vordering;
    }

    public void setVerticalFillOrder(VerticalFillOrder fillOrder) {
        if (_vordering != fillOrder) {
            _vordering = fillOrder;
            if (!_cellsUsed.isEmpty()) {
                this.reloadData();
            }
        }
    }

    public void reloadData() {
        _oldDirection = Direction.NONE;

        for (TableViewCell cell : _cellsUsed) {
            if (_tableViewDelegate != null) {
                _tableViewDelegate.tableCellWillRecycle(this, cell);
            }

            _cellsFreed.add(cell);

            cell.reset();
            if (cell.getParent() == this.getContainer()) {
                this.getContainer().removeChild(cell, false);
            }
        }

        _indices.clear();
        _cellsUsed.clear();

        this._updateCellPositions();
        this._updateContentSize();
        if (_dataSource.numberOfCellsInTableView(this) > 0) {
            this.scrollViewDidScroll(this);
        }
    }

    public TableViewCell cellAtIndex(int idx) {
        if (_indices.contains(idx)) {
            for (TableViewCell cell : _cellsUsed) {
                if (cell.getIdx() == idx) {
                    return cell;
                }
            }
        }

        return null;
    }

    public void updateCellAtIndex(int idx) {
        if (idx == CC_INVALID_INDEX) {
            return;
        }
        long countOfItems = _dataSource.numberOfCellsInTableView(this);
        if (0 == countOfItems || idx > countOfItems - 1) {
            return;
        }

        TableViewCell cell = this.cellAtIndex(idx);
        if (cell != null) {
            this._moveCellOutOfSight(cell);
        }
        cell = _dataSource.tableCellAtIndex(this, idx);
        this._setIndexForCell(idx, cell);
        this._addCellIfNecessary(cell);
    }

    public void insertCellAtIndex(int idx) {
        if (idx == CC_INVALID_INDEX) {
            return;
        }

        long countOfItems = _dataSource.numberOfCellsInTableView(this);
        if (0 == countOfItems || idx > countOfItems - 1) {
            return;
        }

        int newIdx = 0;

        TableViewCell cell = cellAtIndex(idx);
        if (cell != null) {
            newIdx = _cellsUsed.indexOf(cell);
            // Move all cells behind the inserted position
            for (int i = newIdx; i < _cellsUsed.size(); i++) {
                cell = _cellsUsed.get(i);
                this._setIndexForCell(cell.getIdx() + 1, cell);
            }
        }

        // insert a new cell
        cell = _dataSource.tableCellAtIndex(this, idx);
        this._setIndexForCell(idx, cell);
        this._addCellIfNecessary(cell);

        this._updateCellPositions();
        this._updateContentSize();
    }

    public void removeCellAtIndex(int idx) {
        if (idx == CC_INVALID_INDEX) {
            return;
        }

        long uCountOfItems = _dataSource.numberOfCellsInTableView(this);
        if (0 == uCountOfItems || idx > uCountOfItems - 1) {
            return;
        }

        int newIdx = 0;

        TableViewCell cell = this.cellAtIndex(idx);
        if (cell == null) {
            return;
        }

        newIdx = _cellsUsed.indexOf(cell);

        // remove first
        this._moveCellOutOfSight(cell);

        _indices.remove(idx);
        this._updateCellPositions();

        for (int i = _cellsUsed.size() - 1; i > newIdx; i--) {
            cell = _cellsUsed.get(i);
            this._setIndexForCell(cell.getIdx() - 1, cell);
        }
    }

    public TableViewCell dequeueCell() {
        TableViewCell cell;

        if (_cellsFreed.isEmpty()) {
            cell = null;
        } else {
            cell = _cellsFreed.get(0);
            cell.retain();
            _cellsFreed.remove(0);
            cell.autorelease();
        }
        return cell;
    }

    public void _addCellIfNecessary(TableViewCell cell) {
        if (cell.getParent() != this.getContainer()) {
            this.getContainer().addChild(cell);
        }
        _cellsUsed.add(cell);
        _indices.add(cell.getIdx());
        _isUsedCellsDirty = true;
    }

    public void _updateContentSize() {
        Size size = Size.ZERO;
        int cellsCount = _dataSource.numberOfCellsInTableView(this);

        if (cellsCount > 0) {
            float maxPosition = _vCellsPositions.get(cellsCount);

            switch (this.getDirection()) {
                case HORIZONTAL:
                    size = new Size(maxPosition, _viewSize.height);
                    break;
                default:
                    size = new Size(_viewSize.width, maxPosition);
                    break;
            }
        }

        this.setContentSize(size);

        if (_oldDirection != _direction) {
            if (_direction == Direction.HORIZONTAL) {
                this.setContentOffset(new Vec2(0, 0));
            } else {
                this.setContentOffset(new Vec2(0, this.minContainerOffset().y));
            }
            _oldDirection = _direction;
        }
    }

    public Vec2 _offsetFromIndex(int index) {
        Vec2 offset = this.__offsetFromIndex(index);

        Size cellSize = _dataSource.tableCellSizeForIndex(this, index);

        if (_vordering == VerticalFillOrder.TOP_DOWN) {
            offset.y = this.getContainer().getContentSize().height - offset.y - cellSize.height;
        }
        return offset;
    }

    public Vec2 __offsetFromIndex(int index) {
        Vec2 offset = new Vec2();

        switch (this.getDirection()) {
            case HORIZONTAL:
                offset.set(_vCellsPositions.get(index), 0.0f);
                break;
            default:
                offset.set(0.0f, _vCellsPositions.get(index));
                break;
        }

        return offset;
    }

    public int _indexFromOffset(Vec2 offset) {
        int index = 0;
        int maxIdx = _dataSource.numberOfCellsInTableView(this) - 1;

        if (_vordering == VerticalFillOrder.TOP_DOWN) {
            offset.y = this.getContainer().getContentSize().height - offset.y;
        }
        index = this.__indexFromOffset(offset);
        if (index != -1) {
            index = Math.max(0, index);
            if (index > maxIdx) {
                index = CC_INVALID_INDEX;
            }
        }

        return index;
    }

    public int __indexFromOffset(Vec2 offset) {
        int low = 0;
        int high = _dataSource.numberOfCellsInTableView(this) - 1;
        float search;
        switch (this.getDirection()) {
            case HORIZONTAL:
                search = offset.x;
                break;
            default:
                search = offset.y;
                break;
        }

        while (high >= low) {
            int index = low + (high - low) / 2;
            float cellStart = _vCellsPositions.get(index);
            float cellEnd = _vCellsPositions.get(index + 1);

            if (search >= cellStart && search <= cellEnd) {
                return index;
            } else if (search < cellStart) {
                high = index - 1;
            } else {
                low = index + 1;
            }
        }

        if (low <= 0) {
            return 0;
        }

        return -1;
    }

    public void _moveCellOutOfSight(TableViewCell cell) {
        if (_tableViewDelegate != null) {
            _tableViewDelegate.tableCellWillRecycle(this, cell);
        }

        _cellsFreed.add(cell);
        _cellsUsed.remove(cell);
        _isUsedCellsDirty = true;

        _indices.remove(cell.getIdx());
        cell.reset();

        if (cell.getParent() == this.getContainer()) {
            this.getContainer().removeChild(cell, false);
        }
    }

    public void _setIndexForCell(int index, TableViewCell cell) {
        cell.setAnchorPoint(new Vec2(0.0f, 0.0f));
        cell.setPosition(this._offsetFromIndex(index));
        cell.setIdx(index);
    }

    public void _updateCellPositions() {
        int cellsCount = _dataSource.numberOfCellsInTableView(this);
        EzyLists.resize(_vCellsPositions, cellsCount + 1, 0.0F);

        if (cellsCount > 0) {
            float currentPos = 0;
            Size cellSize;
            for (int i = 0; i < cellsCount; i++) {
                _vCellsPositions.set(i, currentPos);
                cellSize = _dataSource.tableCellSizeForIndex(this, i);
                switch (this.getDirection()) {
                    case HORIZONTAL:
                        currentPos += cellSize.width;
                        break;
                    default:
                        currentPos += cellSize.height;
                        break;
                }
            }
            _vCellsPositions.set(cellsCount, currentPos);// 1 extra value allows us to get right/bottom of the last cell
        }

    }

    @Override
    public void scrollViewDidZoom(ScrollView view) {
    }

    @Override
    public void scrollViewDidScroll(ScrollView view) {
        int countOfItems = _dataSource.numberOfCellsInTableView(this);
        if (0 == countOfItems) {
            return;
        }

        if (_isUsedCellsDirty) {
            _isUsedCellsDirty = false;
            _cellsUsed.sort((a, b) -> a.getIdx() - b.getIdx());
        }

        int startIdx = 0, endIdx = 0, idx = 0, maxIdx = 0;
        Vec2 offset = this.getContentOffset().multipleNew(-1);
        maxIdx = Math.max(countOfItems - 1, 0);

        if (_vordering == VerticalFillOrder.TOP_DOWN) {
            offset.y = offset.y + _viewSize.height / this.getContainer().getScaleY();
        }
        startIdx = this._indexFromOffset(offset);
        if (startIdx == CC_INVALID_INDEX) {
            startIdx = countOfItems - 1;
        }

        if (_vordering == VerticalFillOrder.TOP_DOWN) {
            offset.y -= _viewSize.height / this.getContainer().getScaleY();
        } else {
            offset.y += _viewSize.height / this.getContainer().getScaleY();
        }
        offset.x += _viewSize.width / this.getContainer().getScaleX();

        endIdx = this._indexFromOffset(offset);

        if (endIdx == CC_INVALID_INDEX) {
            endIdx = countOfItems - 1;
        }

        if (!_cellsUsed.isEmpty()) {
            TableViewCell cell = _cellsUsed.get(0);
            idx = cell.getIdx();

            while (idx < startIdx) {
                this._moveCellOutOfSight(cell);
                if (!_cellsUsed.isEmpty()) {
                    cell = _cellsUsed.get(0);
                    idx = cell.getIdx();
                } else {
                    break;
                }
            }
        }
        if (!_cellsUsed.isEmpty()) {
            TableViewCell cell = _cellsUsed.get(_cellsUsed.size() - 1);
            idx = cell.getIdx();

            while (idx <= maxIdx && idx > endIdx) {
                this._moveCellOutOfSight(cell);
                if (!_cellsUsed.isEmpty()) {
                    cell = _cellsUsed.get(_cellsUsed.size() - 1);
                    idx = cell.getIdx();
                } else {
                    break;
                }
            }
        }

        for (int i = startIdx; i <= endIdx; i++) {
            if (!_indices.contains(i)) {
                continue;
            }
            this.updateCellAtIndex(i);
        }

        if (_tableViewDelegate != null) {
            _tableViewDelegate.scrollViewDidScroll(this);
        }
    }

    @Override
    public void onTouchEnded(Touch pTouch, Event pEvent) {
        if (!this.isVisible()) {
            return;
        }

        if (_touchedCell != null) {
            Rect bb = this.getBoundingBox();
            bb.origin = _parent.convertToWorldSpace(bb.origin);

            if (bb.containsPoint(pTouch.getLocation()) && _tableViewDelegate != null) {
                _tableViewDelegate.tableCellUnhighlight(this, _touchedCell);
                _tableViewDelegate.tableCellTouched(this, _touchedCell);
            }

            _touchedCell = null;
        }

        super.onTouchEnded(pTouch, pEvent);
    }

    @Override
    public boolean onTouchBegan(Touch pTouch, Event pEvent) {
        for (Node c = this; c != null; c = c.getParent()) {
            if (!c.isVisible()) {
                return false;
            }
        }

        boolean touchResult = super.onTouchBegan(pTouch, pEvent);

        if (_touches.size() == 1) {
            int index;
            Vec2 point;

            point = this.getContainer().convertTouchToNodeSpace(pTouch);

            index = this._indexFromOffset(point);
            if (index == CC_INVALID_INDEX) {
                _touchedCell = null;
            } else {
                _touchedCell = this.cellAtIndex(index);
            }

            if (_touchedCell != null && _tableViewDelegate != null) {
                _tableViewDelegate.tableCellHighlight(this, _touchedCell);
            }
        } else if (_touchedCell != null) {
            if (_tableViewDelegate != null) {
                _tableViewDelegate.tableCellUnhighlight(this, _touchedCell);
            }

            _touchedCell = null;
        }

        return touchResult;
    }

    @Override
    public void onTouchMoved(Touch pTouch, Event pEvent) {
        super.onTouchMoved(pTouch, pEvent);

        if (_touchedCell != null && isTouchMoved()) {
            if (_tableViewDelegate != null) {
                _tableViewDelegate.tableCellUnhighlight(this, _touchedCell);
            }

            _touchedCell = null;
        }
    }

    @Override
    public void onTouchCancelled(Touch pTouch, Event pEvent) {
        super.onTouchCancelled(pTouch, pEvent);

        if (_touchedCell != null) {
            if (_tableViewDelegate != null) {
                _tableViewDelegate.tableCellUnhighlight(this, _touchedCell);
            }

            _touchedCell = null;
        }
    }

}