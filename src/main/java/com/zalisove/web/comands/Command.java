package com.zalisove.web.comands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Main abstract class for the Command pattern extended.
 *
 * @author O.S.Kyrychenko
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 8879403039606311780L;

    /**
     * Execution method for command.
     * @return Address to go once the command is executed.
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
