package com.ba;

import java.beans.XMLEncoder;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SerializeToXml {
    public SerializeToXml() {


    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
       DVD dvd = new DVD();
        ArrayList<String> dic = new ArrayList<>();
        ArrayList<Movie> movies= new ArrayList<>();
        dic.add("lily");
        dic.add("lana");
        Movie movie = new Movie(1,"Spiderman", 2014,dic);
        Movie movie1 = new Movie(2,"Matrix",1999,dic);
        movies.add(movie);
        movies.add(movie1);
        dvd.setMovies(movies);

        People people = new People(4, "ibrahim", "koc", 22, "mühendis");
        People people1 = new People(1,"ibrahim2","koc2",122,"muhendis2");
        SerializeToXml xml = new SerializeToXml();
        xml.WriteXml(movie);
        xml.WriteGenericXml(dvd,DVD.class);
    }

    public void WriteXml(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        String classname1 = object.getClass().getName().substring(object.getClass().getPackage().getName().length() + 1);
        Method[] m = object.getClass().getDeclaredMethods();
        Field[] f = object.getClass().getDeclaredFields();
        ArrayList<String> method = new ArrayList<>();
        ArrayList<String> methodName = new ArrayList<>();

        for (int i = 0; i < m.length; i++) {
            if (m[i].getName().contains("get")) {
                method.add(m[i].invoke(object).toString());
                methodName.add(m[i].getName());
            }
        }
        String result = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

        String firstTag = "<" + classname1 + ">";
        result += firstTag;
      for(int i=0; i<f.length; i++){
          if(!f[i].getType().getTypeName().contains("List")) {
              String fields = f[i].getName();
              for (int j = 0; j < methodName.size(); j++) {
                  String Key = "";
                  String method1 = methodName.get(j).toLowerCase().substring(3).replace('ı','i');
               //   method1 = method1.replace('ı', 'i');

                  if (fields.equals(method1)) {
                      Method m1 = object.getClass().getDeclaredMethod(methodName.get(j));
                      Object obj = m1.invoke(object);
                      Key = "<" + f[i].getName() + ">" + obj + "</" + f[i].getName() + ">";
                      String a = methodName.get(j);
                      result += Key;
                      break;
                  }
              }

          }
          else{
              String field= f[i].getName().toLowerCase().replace('ı','i');
              for(int j=0; j<methodName.size();j++){
                  String Key ="";
                  String method1 = methodName.get(j).toLowerCase().substring(3).replace('ı','i');
                  if(field.equals(method1)){
                      Method m1 = object.getClass().getDeclaredMethod(methodName.get(j));
                      Object obj = m1.invoke(object);
                      ArrayList<?> list = new ArrayList<>();
                      list = (ArrayList<?>) obj;
                      for(int k=0; k< list.size(); k++){
                          result+="<"+field+">"+list.get(k)+"</"+field+">";
                      }

                  break;
                  }

              }


          }
        }
        String endKey = "</" + classname1 + ">";
        result += endKey;
        System.out.println(result);

        File file = new File("movie.xml");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        bWriter.write(result);
        bWriter.close();
    }


    public void WriteGenericXml(Object object,Class clz){
        String classname = clz.getName().substring(object.getClass().getPackage().getName().length() + 1);
        Method[] method = clz.getDeclaredMethods();
        Field[] fields= clz.getDeclaredFields();
    

    }
}