package com.zalisove.web.comands.functional_command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class SetLocaleCommandTest {

    @Test
    void execute() throws IOException, ServletException {
        SetLocaleCommand setLocaleCommand = new SetLocaleCommand();
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpSession mockSession = mock(HttpSession.class);

        when(mockRequest.getHeader("referer")).thenReturn("http://localhost:8080/Gradle___org_example___FinalProject_1_0_SNAPSHOT_war/command");
        when(mockRequest.getContextPath()).thenReturn("/Gradle___org_example___FinalProject_1_0_SNAPSHOT_war/");

        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getParameter("localeToSet")).thenReturn("ua");


        setLocaleCommand.execute(mockRequest,mockResponse);

        verify(mockResponse,times(1)).sendRedirect("/Gradle___org_example___FinalProject_1_0_SNAPSHOT_war/command");
        verify(mockSession,times(1)).setAttribute("defaultLocale", "ua");
    }
}