package cn.hxp.service;

import java.util.HashMap;


public interface BolgUserBiz {
	

    HashMap<String, String> selectImgandName(int userId);
    

    int selectUsertoConfim(int userId);
}
