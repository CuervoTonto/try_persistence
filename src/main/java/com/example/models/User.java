package com.example.models;

import com.example.contracts.IModel;

public class User implements IModel<User>
{
    private String email;
    private String password;

    public User() {}

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (obj == this) return true;
        if (getClass() != obj.getClass()) return false;

        User other = (User) obj;

        return (
            other.email != null
            && other.password != null
            && other.email.equals(email)
            && other.password.equals(password)
        );
    }

    @Override
    public void fromPersistence(String[] data)
    {
        this.email = data[0];
        this.password = data[1];
    }

    @Override
    public String[] toPersistence()
    {
        return new String[] {
            this.email,
            this.password
        };
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}