package com.example.crudwithvaadin.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("hello")
public class HelloWorldView extends VerticalLayout {

    public HelloWorldView() {
        add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
    }
}
