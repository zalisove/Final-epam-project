package com.zalisove.web.comands;

import com.zalisove.web.comands.page_command.StartPageCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandContainerTest {

    @Test
    void get() {
        assertEquals(StartPageCommand.class,CommandContainer.get("start_page").getClass());
    }
}