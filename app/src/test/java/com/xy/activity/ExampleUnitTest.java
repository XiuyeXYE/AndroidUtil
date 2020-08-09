package com.xy.activity;

import com.xiuye.sharp.X;
import com.xiuye.util.cls.XType;
import com.xiuye.util.code.XCode;
import com.xiuye.util.log.XLog;

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

        new X(99999).THEN(d -> {
            XLog.lg(d);
            XLog.lg("void call(I in)");

        }).THEN(d -> {
            XLog.lg(d);
            XLog.lg("R call(I in)");

            return 999;
        }).THEN(() -> {
            XLog.lg("R call()");
            return "ABC";
        }).THEN(() -> {
            XLog.lg("void call()");
        }).THEN(() -> {
            XLog.lg("End");
        }).THEN(() -> {
            throw new RuntimeException("My EXion deliberately");
        }).EX(e -> {
            XLog.lg(e);
            return e;
        }).EX(e -> {
            XLog.lg(e);
        }).EX(e -> {
            XLog.lg(e);
        }).EX(() -> {
            throw new RuntimeException("Nothing");
        }).EX(e -> {
            XLog.lg(e);
            return 100;
        }).EX(d -> {
            XLog.log(d);
        }).EX(() -> {
            XLog.lg("handle EXion 7");
        }).EX(() -> {
            XLog.lg("handle EXion 8");
        });
//        new X<String>(() -> {
//            XLog.lg("right now execute immediately!");
//        });
//        new X<String>((d) -> {
//            XLog.lg("right now execute immediately!", d);
//        }, "ABC");
//        new X<String>((d) -> {
//            XLog.lg("right now execute immediately!", d);
//            return d;
//        }, "ABC").THEN((d) -> {
//            XLog.lg(d);
//        });
    }

    @Test
    public void testPool() throws RuntimeException, InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<Integer> pl = new ExecutorCompletionService<>(pool);
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

//    X<Integer> pro = new X<>(() -> 100);

    @Test
    public void multiThreads() throws InterruptedException {

        Thread t1 = new Thread(() -> {
//            pro = pro.THEN((d) -> {
//                XLog.lg(d);
////                for(;;){}
//                return 101;
//            });
        });
        Thread t2 = new Thread(() -> {
//            pro = pro.THEN((d) -> {
//                XLog.lg(d);
//                return 102;
//            });
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();

        for (int i = 0; i < 100; i++) {
            int j = i;
            //java thread every not deamon will all be called!
            Thread t = new Thread(() -> {
//                pro = pro.THEN((d) -> {
//                    XLog.lg(d);
//                    return j;
//                });
            });
            t.start();
//            t.join();// will be ordered!
//            XLog.lg("join:",i);
        }
//        pro.THEN((d) -> {
//            XLog.lg("end", d);
//        });

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
    public void alotofX() {

        XCode.runS(() -> {
            X<Integer> pro = X.resolve(100);
            for (int i = 0; i < 10000000; i++) {
                int j = i;
                pro = pro.THEN((d) -> {
                    XLog.lg(d);
                    return 200 + j;
                });
            }
            pro.FINALLY(d -> {
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
        X.resolve(f1()).E(d -> {
            XLog.lg(d);
        });
        X.resolve(f2());
        XLog.lg(null == null);
        XLog.lg(Boolean.parseBoolean(null));
        Boolean b = null;
        XLog.lg(b);
    }

    @Test
    public void testNewAPI1() {
        X.resolve(123).EX(e -> {
            e.printStackTrace();
        }).THEN(d -> {
            XLog.lg(d);
        }).THEN(() -> {
            throw new RuntimeException("Demo");
        }).EX(e -> {
            XLog.lg("printTrace:");
            e.printStackTrace();
            return "ABC";
        }).EX(e -> {
            XLog.lg("eeeeee");
            return 567;
        }).THEN(d -> {
            XLog.lg(d);
        });

        X.resolve(123).THEN(d -> {
            XLog.lg(d);
            return d;
        }).EX(e -> {
//            return "ABC";//error , muse be same type as the previous
            return 111;
        }).THEN(d -> {
            XLog.lg(d);
            return "ABC";
        }).THEN(d -> {
            XLog.lg(d);
        });
    }

    @Test
    public void testXLogicAPI() {
        X.resolve(true).THEN(d -> {
            XLog.lg(256, d);
//           return true;
            return "ABC";//<=> true
        }).THEN(d -> {

            XLog.lg(261, d);
        }).THEN(d -> {
            XLog.lg(266, d);
        });

//        X.resolve(true).and(true).truely(d -> {
//            XLog.lg("truely", d);
//        });
//        X.resolve(true).and(false).falsely(d -> {
//            XLog.lg("falsely", d);
//        });
    }

    @Test
    public void testXIfElse() {
        X.resolve(true).T(d -> {
            XLog.lg(d);
        });
//        .ef(true).truely(d->{
//            XLog.lg(d);
//        });
        if (true) {
            XLog.lg("if:", true);
        } else if (true) {
            XLog.lg("else if:", true);
        }

        Runnable r = () -> {
        };

        Object o = r;
        XLog.lg(null instanceof Object);
        XLog.lg(null instanceof String);
    }

    @Test
    public void testIfElse() {
        X.resolve("ABC").begin().IF(true).THEN(() -> {
            XLog.lg("ef do");
        }).ELIF(true).THEN(() -> {
            XLog.lg("else if do");
        }).end().THEN(d -> {
            XLog.lg("end", d);
        }).begin().IF(true).THEN(() -> {

        }).end().THEN(d -> {
            XLog.lg(d);
        });
    }

    @Test
    public void testXThreadTask() {
        X.taskS(() -> "ABC")
                .task(d -> {
                    XLog.ln(d, d.get(), d.getError());
                    return "ABC";
                }).task(d -> {
            XLog.ln(d, d.get(), d.getError());
            return "ABC";
        }).task(d -> {
            XLog.ln(d, d.get(), d.getError());
            return "ABC";
        }).task(d -> {
            XLog.ln(d, d.get(), d.getError());
            return "ABC";
        }).task(d -> {
            XLog.ln(d, d.get(), d.getError());
            return "ABC";
        }).THEN(d -> {
            XLog.ln(d, d.get(), d.getError());
//            return d;
        })
                .ln()
        ;
    }
}