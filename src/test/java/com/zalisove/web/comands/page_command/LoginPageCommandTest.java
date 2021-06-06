package com.zalisove.web.comands.page_command;

import com.zalisove.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LoginPageCommandTest {

    @Test
    void execute() throws ServletException {
        LoginPageCommand loginPageCommand = new LoginPageCommand();
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        assertEquals(Path.PAGE__LOGIN,loginPageCommand.execute(mockRequest,mockResponse));
    }
}