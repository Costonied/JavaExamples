package ru.costonied.examples.lombook;

import lombok.With;
import lombok.Getter;

public class AnnotationWithExample {
    public static void main(String[] args) {
        TestWithAnnotation testWithAnnotation = new TestWithAnnotation(1, 2);
        // Create clone but with another value in the field "two"
        TestWithAnnotation clone = testWithAnnotation.withTwo(3);

        System.out.println(testWithAnnotation.getTwo());
        System.out.println(clone.getTwo());
    }
}

@Getter
class TestWithAnnotation {
    @With private int one;
    @With private int two;

    public TestWithAnnotation(int one, int two) {
        this.one = one;
        this.two = two;
    }
}
