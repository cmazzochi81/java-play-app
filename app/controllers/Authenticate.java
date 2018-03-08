package controllers;
import play.data.validation.Valid;
import play.mvc.Controller;
import models.*;
// import notifiers.Emails;

public class Authenticate extends Controller {

    private static void doLoginLogic(String username) {
        session.put("user", username);

    }

    public static void register() {
        render();
    }

    public static void doRegister(@Valid User user) {
        //if there are errors, redisplay the registration form, 
        //otherwise save the user
        if (!user.validateAndSave()) {
            params.flash();
            validation.keep();
            register();
        }

        //if no errors, log in and redirect to the index page
        doLoginLogic(user.username);
        // Emails.welcome(user);
        Application.index();
    }

    public static void login() {
        render();
    }

    public static void doLogin(String username, String password) {
        if (User.isValidLogin(username, password)) {
            doLoginLogic(username);
            Application.index();
        } else {
            validation.addError("username", "Username/Password combination was incorrect.");
            validation.keep();
            login();
        }
    }

    public static void logout() {
        session.remove("user");
        Application.index();
    }
    
   public static User getLoggedInUser(){
        return User.find("byUsername", session.get("user")).first();
  }
}

