package com.zenoton.jlatexrs;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI(path = "/reports")
@Theme("valo")
public class ReportBrowserUI extends UI{
    @Override
    protected void init(VaadinRequest request) {

    }
}
