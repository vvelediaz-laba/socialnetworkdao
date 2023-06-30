package com.solvd.socialnetworkdao.facade;

import com.solvd.socialnetworkdao.User;
import com.solvd.socialnetworkdao.services.IService;
import com.solvd.socialnetworkdao.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetFromDBFacade {
    private static final Logger logger = LogManager.getLogger(GetFromDBFacade.class.getName());

    public void printUser(long id, String type){
        IService<User> userService = ServiceFactory.createUserService(type);
        assert userService != null;
        User user = userService.getById(id);
        logger.info(user);
    }
}
