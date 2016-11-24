package main.java.bntu.command.utils;

import main.java.bntu.command.Command;
import main.java.bntu.commandImpl.AddCar;
import main.java.bntu.commandImpl.AddCarcar;
import main.java.bntu.commandImpl.ChangeStatus;
import main.java.bntu.commandImpl.LogOut;
import main.java.bntu.commandImpl.Main;
import main.java.bntu.commandImpl.OrderService;
import main.java.bntu.commandImpl.OrderUserService;
import main.java.bntu.commandImpl.SignIn;
import main.java.bntu.commandImpl.SignUp;
import main.java.bntu.commandImpl.admin.AddNewService;
import main.java.bntu.commandImpl.admin.AddService;
import main.java.bntu.commandImpl.admin.AddUserPage;
import main.java.bntu.commandImpl.admin.AddUsersAdmin;
import main.java.bntu.commandImpl.admin.AppointMaster;
import main.java.bntu.commandImpl.admin.AppointMasterPage;
import main.java.bntu.commandImpl.admin.DeleteUserPage;
import main.java.bntu.commandImpl.admin.DeleteUsersAdmin;
import main.java.bntu.commandImpl.admin.UpdateUserPage;
import main.java.bntu.commandImpl.admin.UpdateUsersAdmin;

/**
 * Defines commands and corresponding attributes names
 * 
 * @author Veronika
 *
 */

public enum CommandType {

	DELETE {
		@Override
		public Command getCommand() {
			return new DeleteUsersAdmin();
		}
	},
	DELETE_USER {
		@Override
		public Command getCommand() {
			return new DeleteUserPage();
		}
	},
	ADD {
		@Override
		public Command getCommand() {
			return new AddUsersAdmin();
		}
	},
	ADD_USER {
		@Override
		public Command getCommand() {
			return new AddUserPage();
		}
	},
	UPDATE {
		@Override
		public Command getCommand() {
			return new UpdateUsersAdmin();
		}
	},
	UPDATE_USER {
		@Override
		public Command getCommand() {
			return new UpdateUserPage();
		}
	},
	APPOINT {
		@Override
		public Command getCommand() {
			return new AppointMaster();
		}
	},
	CHANGE_MASTER {
		@Override
		public Command getCommand() {
			return new AppointMasterPage();
		}
	},
	SIGNIN {
		@Override
		public Command getCommand() {
			return new SignIn();
		}
	},
	SIGNUP {
		@Override
		public Command getCommand() {
			return new SignUp();
		}
	},
	ADD_CAR {
		@Override
		public Command getCommand() {
			return new AddCar();
		}
	},
	MAIN {
		@Override
		public Command getCommand() {
			return new Main();
		}
	},
	ADD_CARS {
		@Override
		public Command getCommand() {
			return new AddCarcar();
		}
	},
	ADD_SERVICE {
		@Override
		public Command getCommand() {
			return new AddService();
		}
	},
	ORDER_SERVICE {
		@Override
		public Command getCommand() {
			return new OrderService();
		}
	},
	ORDER {
		@Override
		public Command getCommand() {
			return new OrderUserService();
		}
	},
	CHANGE {
		@Override
		public Command getCommand() {
			return new ChangeStatus();
		}
	},
	OK {
		@Override
		public Command getCommand() {
			return new AddNewService();
		}

	},
	LOGOUT {
		@Override
		public Command getCommand() {
			return new LogOut();
		}

	};

	public abstract Command getCommand();

}
