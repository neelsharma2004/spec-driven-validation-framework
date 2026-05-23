package com.learnings.projects.customer.app.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContactMediumValue {

    private String type;
    private boolean preferred;
    private String medium;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
