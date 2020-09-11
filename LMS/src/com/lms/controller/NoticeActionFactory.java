package com.lms.controller;

import com.lms.controller.action.Action;
import com.lms.controller.action.NoticeCheckPassAction;
import com.lms.controller.action.NoticeCheckPassFormAction;
import com.lms.controller.action.NoticeDeleteAction;
import com.lms.controller.action.NoticeListAction;
import com.lms.controller.action.NoticeUpdateAction;
import com.lms.controller.action.NoticeUpdateFormAction;
import com.lms.controller.action.NoticeViewAction;
import com.lms.controller.action.NoticeWriteAction;
import com.lms.controller.action.NoticeWriteFormAction;


public class NoticeActionFactory {
	private static NoticeActionFactory instance = new NoticeActionFactory();

	private NoticeActionFactory() {
		super();
	}

	public static NoticeActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory :" + command);
		if (command.equals("notice_list")) {//noti_list占쏙옙占� 占쏙옙占쏙옙占쏙옙求째占� 占싣닌곤옙
			action = new NoticeListAction();
		} else if (command.equals("notice_write_form")) {
			action = new NoticeWriteFormAction();
		} else if (command.equals("notice_write")) {
			action = new NoticeWriteAction();
		} else if (command.equals("notice_view")) {
			action = new NoticeViewAction();
		}	else if (command.equals("notice_check_pass_form")) {
				action = new NoticeCheckPassFormAction();
		} else if (command.equals("notice_check_pass")) {
				action = new NoticeCheckPassAction();
		} else if (command.equals("notice_update_form")) {
			action = new NoticeUpdateFormAction();
		} else if (command.equals("notice_update")) {
			action = new NoticeUpdateAction();
		} else if (command.equals("notice_delete")) {
			action = new NoticeDeleteAction();
		}
		return action;
	}
}