package by.htp.courses.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.courses.controller.command.Command;
import by.htp.courses.controller.command.impl.Localization;
import by.htp.courses.controller.command.impl.MainPage;
import by.htp.courses.controller.command.impl.SignIn;
import by.htp.courses.controller.command.impl.SignOut;
import by.htp.courses.controller.command.impl.SignUp;
import by.htp.courses.controller.command.impl.SubjectsEdit;
import by.htp.courses.controller.command.impl.SubjectsPage;
import by.htp.courses.controller.command.impl.TeachersPage;
import by.htp.courses.controller.command.impl.UsersEdit;
import by.htp.courses.controller.command.impl.UsersSearch;

class CommandProvider {
	
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.MAIN_PAGE, new MainPage());
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.SIGN_UP, new SignUp());
		commands.put(CommandName.SIGN_OUT, new SignOut());
		commands.put(CommandName.USERS_EDIT, new UsersEdit());
		commands.put(CommandName.USERS_SEARCH, new UsersSearch());
		commands.put(CommandName.SUBJECTS_EDIT, new SubjectsEdit());
		commands.put(CommandName.LOCALIZATION, new Localization());
		commands.put(CommandName.TEACHERS_PAGE, new TeachersPage());
		commands.put(CommandName.SUBJECTS_PAGE, new SubjectsPage());
	}

	Command getCommand(String commandName) {
		Command command;

		command = commands.get(CommandName.valueOf(commandName.toUpperCase()));

		return command;

	}



}
