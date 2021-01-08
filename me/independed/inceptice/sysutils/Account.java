/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.sysutils;

public class Account {
    private int id;
    private String password;
    private String hwid;
    private String login;

    public String getPassword() {
        return this.password;
    }

    public void setId(int n) {
        this.id = n;
    }

    public void setHwid(String string) {
        this.hwid = string;
    }

    public String getLogin() {
        return this.login;
    }

    public String getHwid() {
        return this.hwid;
    }

    public void setLogin(String string) {
        this.login = string;
    }

    public int getId() {
        return this.id;
    }

    public void setPassword(String string) {
        this.password = string;
    }
}

