package com.xy.activity;

import com.xiuye.util.log.XLog;
import com.xy.util.Promise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        new Promise(() -> {
            return 99999;
        }).then(d -> {
            XLog.lg(d);
            XLog.lg("void call(I in)");

        }).then(d -> {
            XLog.lg(d);
            XLog.lg("R call(I in)");

            return 999;
        }).then(() -> {
            XLog.lg("R call()");
            return "ABC";
        }).then(() -> {
            XLog.lg("void call()");
        }).then(() -> {
            XLog.lg("End");
        }).then(() -> {
            throw new RuntimeException("My Exception deliberately");
        }).except(e -> {
            XLog.lg(e);
            return e;
        }).except(e -> {
            XLog.lg(e);
        }).except(e -> {
            XLog.lg(e);
        }).except(() -> {
            throw new RuntimeException("Nothing");
        }).except(e -> {
            XLog.lg(e);
            return 100;
        }).except(d -> {
            XLog.log(d);
        }).except(() -> {
            XLog.lg("handle exception 7");
        }).except(() -> {
            XLog.lg("handle exception 8");
        });
        new Promise<String>(() -> {
            XLog.lg("right now execute immediately!");
        });
        new Promise<String>((d) -> {
            XLog.lg("right now execute immediately!", d);
        }, "ABC");
        new Promise<String>((d) -> {
            XLog.lg("right now execute immediately!", d);
            return d;
        }, "ABC").then((d) -> {
            XLog.lg(d);
        });
    }


}