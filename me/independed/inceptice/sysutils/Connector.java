/*
 * Decompiled with CFR 0.150.
 */
package me.independed.inceptice.sysutils;

import java.sql.Connection;
import java.sql.SQLException;
import me.independed.inceptice.sysutils.ConnectionConfig;

public class Connector
extends ConnectionConfig {
    String status;
    static Connection connection;

    public void setStatus(String string) {
        this.status = string;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException;

    public String getStatus() {
        return this.status;
    }
}

