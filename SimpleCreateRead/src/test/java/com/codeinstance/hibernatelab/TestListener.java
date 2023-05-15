package com.codeinstance.hibernatelab;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class TestListener extends RunListener {



    public void testStarted(Description description) {
        System.out.println("\n\nStarting .... " + description.getMethodName()+"\n\n");
    }

    public void testFinished(Description description) {
        System.out.println("\n\nFinished .... "+ description.getMethodName()+"\n\n");
    }

    public void testRunFinished(Result result) {
        System.out.println("\n");
    }
}
