package com.zalisove.web.comands;

import com.zalisove.web.comands.functional_command.*;
import com.zalisove.web.comands.page_command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.
 *
 * @author O.S.Kyrychenko
 */
public class CommandContainer {
    private static final Logger LOG = LogManager.getLogger(CommandContainer.class);

    private static final Map<String, Command> commands = new TreeMap<>();


    private CommandContainer() { }

    static {
        // page commands
        commands.put("start_page",new StartPageCommand());
        commands.put("login_page",new LoginPageCommand());
        commands.put("registration_page",new RegistrationPageCommand());
        commands.put("test_page",new TestPageCommand());
        commands.put("test_result_page",new ResultPageCommand());
        commands.put("create_test_page",new CreateTestPageCommand());
        commands.put("update_test_page",new UpdateTestPageCommand());
        commands.put("change_user_page",new ChangeUserPageCommand());
        commands.put("user_page",new UserPageCommand());
        commands.put("admin_users_page",new AdminUsersPageCommand());

        // functional commands
        commands.put("login",new LoginCommand());
        commands.put("registration",new RegistrationCommand());
        commands.put("sign_out",new SignOutCommand());
        commands.put("test_check",new TestCommand());
        commands.put("create_test",new CreateTestCommand());
        commands.put("update_test",new UpdateTestCommand());
        commands.put("delete_test",new DeleteTestCommand());
        commands.put("delete_user",new DeleteUserCommand());
        commands.put("update_user_data",new UpdateUserDataCommand());
        commands.put("set_locale",new SetLocaleCommand());
        commands.put("get_top_users",new GetTopUsersCommand());
        commands.put("top_users_page",new TopUserPageCommand());


        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("start_page");
        }

        return commands.get(commandName);
    }
}
