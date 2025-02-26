package com.alibou.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
//@PropertySource("classpath:custom.properties")
@PropertySources({
        @PropertySource("classpath:custom.properties"),
        @PropertySource("classpath:custom2.properties")
})
public class MyFirstService {

    private final MyFirstClass myFirstClass;

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    // Field Injection
//    @Autowired
//    @Qualifier("mySecondBean")
//    private MyFirstClass myFirstClass;


    // Constructor Injection
    // @Autowired -> If we want to use the constructor injection there is no need of autowired
    public MyFirstService(
           @Qualifier("bean1")
            MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }


  //  private MyFirstClass myFirstClass;

    // Method Injection
//    @Autowired
//    public void injectDependencies(
//          @Qualifier("bean1")  MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }


    // Setter Injection
//    @Autowired
//    public void setMyFirstClass(
//            @Qualifier("bean1")  MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }

    public String tellAStory() {
        return "The Dependency is saying : " + myFirstClass.sayHello();
    }

    public String getJavaVersion(){
        return environment.getProperty("java.version");
    }

    public String getOsName(){
        return environment.getProperty("os.name");
    }

    public String readProperty(){
        return environment.getProperty("my.custom.property");
    }


    @Value("${my.custom.property}")
    private String customProperty;
    @Value("${my.prop}")
    private String customPropertyFromAnotherFile;
    @Value("${my.prop.2}")
    private String customPropertyFromAnotherFile2;
    @Value("${my.custom.property.int}")
    private Integer customPropertyInt;

    public String getCustomProperty() {
        return customProperty;
    }

    public String getCustomPropertyFromAnotherFile() {
        return customPropertyFromAnotherFile;
    }

    public String getCustomPropertyFromAnotherFile2() {
        return customPropertyFromAnotherFile2;
    }

    public Integer getCustomPropertyInt() {
        return customPropertyInt;
    }
}
