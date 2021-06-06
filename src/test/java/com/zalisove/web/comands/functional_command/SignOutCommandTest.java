package com.zalisove.web.comands.functional_command;

import com.zalisove.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


class SignOutCommandTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void executeIfSessionNotNull() throws ServletException, IOException {

        SignOutCommand signOutCommand = new SignOutCommand();
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpSession mockSession = mock(HttpSession.class);

        when(mockRequest.getSession(false)).thenReturn(mockSession);
        when(mockRequest.getContextPath()).thenReturn("");
        
        signOutCommand.execute(mockRequest,mockResponse);

        verify(mockSession,times(1)).invalidate();
        verify(mockResponse).sendRedirect(Path.COMMAND__START_PAGE);
    }

    @Test
    void executeIfSessionNot() throws ServletException, IOException {

        SignOutCommand signOutCommand = new SignOutCommand();
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        when(mockRequest.getSession(false)).thenReturn(null);
        when(mockRequest.getContextPath()).thenReturn("");

        signOutCommand.execute(mockRequest,mockResponse);

        verify(mockResponse).sendRedirect(Path.COMMAND__START_PAGE);
    }
}