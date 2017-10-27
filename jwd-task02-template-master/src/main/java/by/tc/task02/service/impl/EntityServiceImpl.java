package by.tc.task02.service.impl;

import by.tc.task02.service.EntityService;
import by.tc.task02.entity.Entity;
import by.tc.task02.dao.DAOFactory;
import by.tc.task02.dao.EntityDAO;


public class EntityServiceImpl implements EntityService {

    @Override
    public Entity parseXML(){

        DAOFactory factory = DAOFactory.getInstance();
        EntityDAO entityDAO = factory.getEntityDAO();

        Entity entities = entityDAO.parseXML();

        return entities;
    }
}
