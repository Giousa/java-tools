package com.giousa.swing.base.checkbox;

import javax.swing.table.AbstractTableModel;

public class BtnTableModel extends AbstractTableModel {
    private Object[] columnNames;
    private Object[][] rowData;

    public BtnTableModel(Object[] columnNames, Object[][] rowData) {
        this.columnNames = columnNames;
        this.rowData = rowData;
    }

    @Override
    public int getRowCount() {
        return rowData.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column].toString();
    }

    /**
     * 这里以及紧接着的下边的那个函数必须要有，这里就让这个复选框居中和展现出来，
     * 就不用去写渲染器了，也不用别的方法
     */
    Class<?>[] columnTypes = new Class<?>[]{
            Object.class, Boolean.class
    };

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        //选择框和自定义按钮允许编辑和点击
        if (column == 1 || column == 2) {
            return true;
        } else {
            return false;
        }
    }

    //不必在意，可有可无这个
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        rowData[rowIndex][columnIndex] = aValue;
        //只需要更新对应的位置
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }
}
