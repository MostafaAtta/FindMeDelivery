package com.atta.findmedelivery.login;

public interface LoginContract {

    interface View{

        void showError(String error);

        void showViewError(String view, String error);

        void showMessage();

        void navigateToMain();

        void setDialog();

        void skipToMain();

        void dismissProgressDialog();

        void navigateToMenu();
    }

    interface Presenter{

        void login(String email, String password);

        void adminLogin(String email, String password);

        boolean validate(String email, String password);
    }
}
