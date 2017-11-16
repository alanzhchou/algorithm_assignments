package service;

import bean.UserinfoBean;
import dao.UserinfoDao;
import dao.UserinfoDaoImpl;

public class UserinfoServiceImpl implements UserinfoService {
    UserinfoDao	userinfoDao = new UserinfoDaoImpl();
    @Override
    public int login(String	username,String	password)	{
        int result = 0;
        try{
            result=userinfoDao.login(username,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int registerUserinfo(UserinfoBean userinfoBean) {
        int result=0;
        try{
            result=userinfoDao.registerUserinfo(userinfoBean);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
