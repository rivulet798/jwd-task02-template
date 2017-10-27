package by.tc.task02.dao;

import by.tc.task02.entity.Entity;

import java.io.*;

public class XMLWorker {

    private final static String OPENING_TAG = "<[^/>||?]+>";
    private final static String CLOSING_TAG = "</[^>]+>";
    private final static String SPACE = "\\s";
    private static final String SPACES="\\s{2,}";
    private final static String SIGN="<|>|\"";
    private static final String SIGN_MORE=">\\s*";
    private static final String SIGN_MORE_WITH_SEPARATOR=">&";
    private static final String SIGN_LESS="\\s*</";
    private static final String SIGN_LESS_WITH_SEPARATOR="&</";
    private static final String SEPARATOR="&";
    private static Entity entity = new Entity();

    public Entity parse(File file) {
        int level=1;
        normalizeFile(file);
        try {
            BufferedReader in = new BufferedReader(new FileReader("formattedFile.xml"));
            try {
                String line;
                line = in.readLine();
                if (line.matches(OPENING_TAG)) {
                    makeRootEntity(line,level);
                }
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.matches(OPENING_TAG)) {
                        Entity nestedEntity = new Entity();
                        String lineWithoutSign=line.replaceAll(SIGN,"");
                        String []masFromLineWithoutSign=lineWithoutSign.split(SPACE);
                        setEntity(masFromLineWithoutSign,++level,nestedEntity);
                        entity.getNestedEntities().add(nestedEntity);
                        nestedEntity.setParentEntity(entity);
                        entity = nestedEntity;
                    }
                    else if (line.matches(CLOSING_TAG)) {
                        level--;
                        entity = entity.getParentEntity();
                    }
                    else{
                        entity.setValue(line);}
                }
            } finally {
                in.close();

            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private void setEntity(String[] masFromLineWithoutSign, int level, Entity entity){
        entity.setName(masFromLineWithoutSign[0]);
        String[] values=new String[masFromLineWithoutSign.length-1];
        for(int i=0;i<values.length;i++){
            String[] keyValuePair=masFromLineWithoutSign[i+1].split("=");
            entity.addAttributes(keyValuePair[0],keyValuePair[1]);
        }
        entity.setLevel(level);
    }

    private void makeRootEntity(String line, int level){
        line = line.trim();
        String lineWithoutSign=line.replaceAll(SIGN,"");
        String []masFromLineWithoutSign=lineWithoutSign.split(SPACE);
        setEntity(masFromLineWithoutSign,level,entity);
        entity.setParentEntity(entity);
    }

    public void normalizeFile(File file){
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            FileWriter fileWriter=new FileWriter("formattedFile.xml",false)){
            String line;
            while((line=bufferedReader.readLine())!=null){
                line=line.replaceAll(SIGN_MORE,SIGN_MORE_WITH_SEPARATOR);
                line=line.replaceAll(SIGN_LESS,SIGN_LESS_WITH_SEPARATOR);
                line=line.replaceAll(SPACES," ");
                line=line.trim();
                String[] arrayOfLines=line.split(SEPARATOR);

                for(String value:arrayOfLines){
                    if(!value.isEmpty()) {
                        fileWriter.write(value + "\n");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

