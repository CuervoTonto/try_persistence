package com.example.models;

import com.example.contracts.IModel;

public class Notification implements IModel<Notification>
{
    private User user;
    private String message;

    public Notification() {}

    public Notification(User user, String message)
    {
        this.user = user;
        this.message = message;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (obj == this) return true;
        if (getClass() != obj.getClass()) return false;

        Notification other = (Notification) obj;

        return (
            other.user != null
            && other.message != null
            && other.user.equals(user)
            && other.message.equalsIgnoreCase(message)
        );
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void fromPersistence(String[] data) {
        // TODO Auto-generated method stub
    }

    @Override
    public String[] toPersistence() {
        // TODO Auto-generated method stub
        return null;
    }

    
}