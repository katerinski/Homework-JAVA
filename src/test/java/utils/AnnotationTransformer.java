package test.java.utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import test.java.DataProvider1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        iTestAnnotation.setRetryAnalyzer(RetryAnalyzer.class); //повторный прогон упавших тестов
        if (method.getName().equals("producerFiltersCheck")) {
            iTestAnnotation.setDataProviderClass(DataProvider1.class);//С помощью AnnotationTransformer реализовать возможность подкладывать дата провайдер через testng.xml файл
            iTestAnnotation.setDataProvider("getDP1");
        }

    }
}
