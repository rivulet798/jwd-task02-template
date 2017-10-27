package by.tc.task02.main;

import by.tc.task02.entity.Entity;

public class PrintEntityInfo {
    public static void print(Entity entity)
    {
        if(entity != null){
        System.out.println(entity.toString());
        if(!entity.getNestedEntities().isEmpty()){
            for(Entity en: entity.getNestedEntities()){
                print(en);
            }
        }
    }
    }
}
