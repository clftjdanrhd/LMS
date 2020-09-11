package com.lms.controller;

import com.lms.controller.action.Action;
import com.lms.controller.action.BoardCheckPassAction;
import com.lms.controller.action.BoardCheckPassFormAction;
import com.lms.controller.action.BoardDeleteAction;
import com.lms.controller.action.BoardListAction;
import com.lms.controller.action.BoardUpdateAction;
import com.lms.controller.action.BoardUpdateFormAction;
import com.lms.controller.action.BoardViewAction;
import com.lms.controller.action.BoardWriteAction;
import com.lms.controller.action.BoardWriteFormAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {
		super();
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory :" + command);
		/* �߰��� �κ� */
		if (command.equals("board_list")) {
			action = new BoardListAction();
		} else if (command.equals("board_write_form")) {
			action = new BoardWriteFormAction();
		} else if (command.equals("board_write")) {
			action = new BoardWriteAction();
		} else if (command.equals("board_view")) {
			action = new BoardViewAction();
		} else if (command.equals("board_check_pass_form")) {
			action = new BoardCheckPassFormAction();
		} else if (command.equals("board_check_pass")) {
			action = new BoardCheckPassAction();
		} else if (command.equals("board_update_form")) {
			action = new BoardUpdateFormAction();
		} else if (command.equals("board_update")) {
			action = new BoardUpdateAction();
		} else if (command.equals("board_delete")) {
			action = new BoardDeleteAction();
		}
		return action;
	}
}
