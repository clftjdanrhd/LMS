package com.lms.controller;

import com.lms.controller.action.Action;
import com.lms.controller.action.LabAdminListAction;
import com.lms.controller.action.LabExcelAction;
import com.lms.controller.action.LabLeaveAction;
import com.lms.controller.action.LabLeaveAllAction;
import com.lms.controller.action.LabLeaveReturnAction;
import com.lms.controller.action.LabListAction;
import com.lms.controller.action.LabRegisterAction;
import com.lms.controller.action.LabUpdateAction;


public class LabActionFactory {
	private static LabActionFactory instance = new LabActionFactory();

	private LabActionFactory() {
		super();
	}

	public static LabActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("LabActionFactory :" + command);
		
		if (command.equals("lab_list")) {
			action = new LabListAction();
		} else if (command.equals("lab_reg")) {
			action = new LabRegisterAction();
		} else if (command.equals("lab_update")) {
			action = new LabUpdateAction();
		} else if (command.equals("lab_del")) {
			action = new LabLeaveAction();
		}else if (command.equals("lab_alldel")) {
			action = new LabLeaveAllAction();
		}else if (command.equals("lab_return")) {
			action = new LabLeaveReturnAction();
		}else if (command.equals("lab_admin_list")) {
			action = new LabAdminListAction();
		}else if (command.equals("lab_list_excel")) {
			action = new LabExcelAction();
		}
		
		return action;
	}
}