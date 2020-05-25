package com.xy.activity;

import com.xiuye.util.cls.XType;
import com.xiuye.util.code.XCode;
import com.xiuye.util.log.XLog;
import com.xy.util.Promise;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

    @Test
    public void testPool() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<Integer> pl = new ExecutorCompletionService<Integer>(pool);
        for (int i = 0; i < 100; i++) {
            int j = i;
            Future<Integer> k = pl.submit(() -> {
                return j;
            });
            XLog.lg(k.get());

        }
        for (int i = 0; i < 100; i++) {
            Future<Integer> k = pl.take();
            XLog.lg(k.get());
        }
//        XLog.lg(pl.take());


    }

    Promise<Integer> pro = new Promise<>(() -> 100);

    @Test
    public void multiThreads() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            pro = pro.then((d) -> {
                XLog.lg(d);
//                for(;;){}
                return 101;
            });
        });
        Thread t2 = new Thread(() -> {
            pro = pro.then((d) -> {
                XLog.lg(d);
                return 102;
            });
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        for (int i = 0; i < 100; i++) {
            int j = i;
            //java thread every not deamon will all be called!
            Thread t = new Thread(() -> {
                pro = pro.then((d) -> {
                    XLog.lg(d);
                    return j;
                });
            });
            t.start();
//            t.join();// will be ordered!
//            XLog.lg("join:",i);
        }
        pro.then((d) -> {
            XLog.lg("end", d);
        });

    }

    @Test
    public void booleanTest() {
        XLog.log(null instanceof Boolean);
        boolean b = false;
        Object o = b;
        XLog.log(o instanceof Boolean);
        Boolean c = b;
        boolean d = c;
        XLog.lg(d);
        XLog.lg(null instanceof Object);
        XLog.lg(null instanceof String);

        boolean e = XType.cast(o);
        XLog.lg(o instanceof Boolean, "cast:", e);
//        e = XType.cast(null);
//        XLog.lg("null value to boolean:", e);
    }

    @Test
    public void alotofPromise() {

        XCode.runS(() -> {
            Promise<Integer> pro = Promise.resolve(100);
            for (int i = 0; i < 10000000; i++) {
                int j = i;
                pro = pro.then((d) -> {
                    XLog.lg(d);
                    return 200 + j;
                });
            }
            pro.lastly(d -> {
                XLog.lg(d);
            });
        });

    }

    private String f1() {
        return "ABC";
    }

    interface CallbackTest<T> {
        T call();
    }

    class A implements CallbackTest {

        @Override
        public String call() {
            return "CallbackTest";
        }
    }

    class B extends A {

    }

    private A f2() {
        return new B();
    }

    @Test
    public void testAmbiguous() {
        Promise.resolve(f1()).exist(d -> {
            XLog.lg(d);
        });
        Promise.resolve(f2());

    }

    @Test
    public void testNewAPI1() {
        Promise.resolve(123).except(e -> {
            e.printStackTrace();
        }).then(d -> {
            XLog.lg(d);
        }).then(() -> {
            throw new RuntimeException("Demo");
        }).except(e -> {
            XLog.lg("printTrace:");
            e.printStackTrace();
            return "ABC";
        }).except(e -> {
            XLog.lg("eeeeee");
            return 567;
        }).then(d -> {
            XLog.lg(d);
        });

        Promise.resolve(123).then(d -> {
            XLog.lg(d);
            return d;
        }).except(e -> {
//            return "ABC";//error , muse be same type as the previous
            return 111;
        }).then(d -> {
            XLog.lg(d);
            return "ABC";
        }).then(d -> {
            XLog.lg(d);
        });
    }
}