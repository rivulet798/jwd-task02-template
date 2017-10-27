package by.tc.task02.dao.impl;

import by.tc.task02.dao.EntityDAO;
import by.tc.task02.dao.XMLWorker;
import by.tc.task02.entity.Entity;

import java.io.File;

public class EntityDAOImpl implements EntityDAO {
    private File file;

    public EntityDAOImpl() {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("task02.xml").getFile());
    }

    @Override
    public Entity parseXML(){
        XMLWorker xmlWorker=new XMLWorker();
        Entity entity=xmlWorker.parse(file);
        return entity;
    }

}
