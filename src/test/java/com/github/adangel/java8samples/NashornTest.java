package com.github.adangel.java8samples;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Test;

public class NashornTest {


    @Test
    public void embeddedNashorn() throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        engine.eval("function sum(a, b) { return a + b; }");
        System.out.println(engine.eval("sum(1, 2);"));
    }
}
