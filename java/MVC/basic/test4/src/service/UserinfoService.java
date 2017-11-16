package service;

import bean.UserinfoBean;

public interface UserinfoService {
    int login(String username, String password);
    int registerUserinfo(UserinfoBean userinfoBean);
}
