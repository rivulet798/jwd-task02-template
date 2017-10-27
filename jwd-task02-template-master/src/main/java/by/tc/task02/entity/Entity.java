package by.tc.task02.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Entity {

    private String name;
    private String value;
    private int level;
    private Map<String, String> attributes = new HashMap<String, String>();
    private ArrayList<Entity> nestedEntities;
    private Entity parentEntity;

    public Entity(){
        nestedEntities = new ArrayList<Entity>();}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {this.attributes=attributes;}

    public ArrayList<Entity> getNestedEntities() {
        return nestedEntities;
    }

    public void setNestedEntities(ArrayList<Entity> nestedEntities) {
        this.nestedEntities = nestedEntities;
    }

    public Entity getParentEntity() {return parentEntity;}

    public void setParentEntity(Entity parentEntity) {this.parentEntity = parentEntity;}

    public void addAttributes(String nameOfAttribute, String value) {
        attributes.put(nameOfAttribute, value);
    }

    public String mapToString(){
        String attribute=new String();
        int i=0;
        for (Map.Entry<String, String> map : attributes.entrySet()) {
            if(i==0){attribute=attribute.concat("(");}
            attribute=attribute.concat(" "+map.getKey().toString() + "=\"" + map.getValue().toString() + "\" ");
            i++;
            if(i==attributes.size()){attribute=attribute.concat(")");}
        }
        return attribute;
    }

    @Override
    public String toString(){
    String resultString=new String();
    String value2="";
    for(int i=1; i<level; i++){
        resultString=resultString.concat("    ");
    }
    if(value!=null) value2=value2.concat(value);
        return resultString.concat(name+mapToString()+": "+value2);
    }
    }
