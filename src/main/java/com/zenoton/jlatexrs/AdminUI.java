package com.zenoton.jlatexrs;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI(path = "/admin")
@Theme("valo")
public class AdminUI extends UI{
    @Override
    protected void init(VaadinRequest request) {

    }
}
