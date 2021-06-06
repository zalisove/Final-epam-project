package com.zalisove.web.comands.page_command;

import com.zalisove.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RegistrationPageCommandTest {

    @Test
    void execute() throws ServletException {
        RegistrationPageCommand registrationPageCommand = new RegistrationPageCommand();
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        assertEquals(Path.PAGE__REGISTRATION,registrationPageCommand.execute(mockRequest,mockResponse));
    }
}