package com.giousa.swing.base.checkbox;

import javax.swing.table.AbstractTableModel;

/**
 * 此类不用单独创建，可以当一个内部类使用，写到你生成JTable的那个类里，
 * 然后用你创建的JTable.setModel(new MyTableMode)来实现JTable插入复选框
 */
public class MyTableModel extends AbstractTableModel {
    //这是每列名字，有几个写几个
    private Object[] columnNames = {"name", "choose"};

    /*这是插入的数据就按着我写的说了，第一列不用照着我的，可以用中英文，第二列就得用到boolean值，
    这里要设置成一个变量的boolean值，后边要更新它达到限制效果，而且这个要设置成全局变量放到这个类的外边，
    再次声明，我用的内部类写法*/
    private Object[][] rowData = {{"曹操", false},
            {"孙权", false},
            {"刘备", false},
            {"袁绍", false},
            {"袁术", false}};

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

    //因为我是第二列需要编辑就设置了column=1，column是列的索引，这里也是必须要设置的
    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 1) {
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
