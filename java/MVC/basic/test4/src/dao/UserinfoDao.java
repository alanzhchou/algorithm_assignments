package dao;

import bean.UserinfoBean;

public interface UserinfoDao {
    int login(String username,String password) throws Exception;
    int registerUserinfo(UserinfoBean userinfoBean) throws Exception;
}
