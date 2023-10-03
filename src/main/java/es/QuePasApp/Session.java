package es.QuePasApp;

public class Session {
    private static String currentUser;

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        Session.currentUser = currentUser;
    }
}

