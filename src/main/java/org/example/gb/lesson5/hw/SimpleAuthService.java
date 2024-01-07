package org.example.gb.lesson5.hw;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {
    private List<Entry> loginPassData = new ArrayList<>();

    {
        loginPassData.add(new Entry("1 1", Role.Admin));
        loginPassData.add(new Entry("2 2", Role.User));
        loginPassData.add(new Entry("3 3", Role.User));
    }

    @Override
    public Role checkLoginPassword(String str) {
        for (Entry loginPassDatum : loginPassData) {
            if (loginPassDatum.getLoginPass().equals(str)) {
                System.out.println(loginPassDatum.getRole());
                 return loginPassDatum.getRole();
            }
        }
        return Role.None;
    }

    @Getter
    private class Entry {
        private String loginPass;
        private Role role;

        public Entry(String loginPass, Role role) {
            this.loginPass = loginPass;
            this.role = role;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "loginPass='" + loginPass + '\'' +
                    ", role=" + role +
                    '}';
        }
    }
}
