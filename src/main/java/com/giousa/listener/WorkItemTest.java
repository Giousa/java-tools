package com.giousa.listener;

public class WorkItemTest {

    public static void main(String[] args) {
        WorkItem workItem = new WorkItem("title");
        workItem.onClickListener(() -> System.out.println("点击了！！！"));

        workItem.hello();
    }
}
