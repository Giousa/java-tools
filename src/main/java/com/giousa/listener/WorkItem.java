package com.giousa.listener;

public class WorkItem {

    private String title;

    private ClickListener clickListener;

    public WorkItem(String title) {
        this.title = title;
    }

    public void hello(){
        clickListener.onClick();
    }

    public void onClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    interface ClickListener{
        void onClick();
    }
}
