package com.xy.util;

import com.xiuye.util.cls.XType;

/**
 * Promise 设计纲要
 * 1.Promise 必须有 结果，即使计算过程中有异常错误，结果应该为null， 并传递给 下一个新的Promise，有 错误
 * 就处理 错误
 * 2.then 处理Promise的结果
 * 3.except处理Promise或者then中的异常错误等
 * 4.Promise 调用了 then
 * 处理结果 中，遇到错误， 应可以延迟 处理！新的错误 可以 覆盖旧的异常 error public Promise(RESULT
 * result,Throwable error) 可以把上一个 Promise未处理的错误继承和传递下来再处理
 * 5.Result值不具有继承效果，一调用
 * then就处理掉，没有延迟处理效果
 * 6.Promise每调用一次 then except 等，都会返回新的 Promise对象。 这样有利于线程安全。
 * 7.except 表示处理异常，运行过程中没有新的异常产生不应将上次的异常继续 传递下去，应及时清理掉 7.except 和 then 的互相下次调用
 * 应该毫 无任何约束，十分流畅
 * 8.内部变量result保存callback的结果，error保存callback运行中产生的异常 或者未处理的异常
 * 9.世界上，每次执行Promise都是直接执行的！
 * 10.this promise is only single thread
 * 11.result不仅是当前Promise计算的结果，
 * 更是传递给下一个的入参，其作用带有next属性
 *
 * @param <RESULT> 结果类型
 */
public class Promise<RESULT> {


    // 一般是这步的计算结果，传递给下一步
    private RESULT result;
    // 这步产生的错误
    private Throwable error;

    // 异常必须处理后才能进行下一步
    private static boolean exIndeed = true;

    /**
     * 直接传入结果的Promise 其实就相当于上一个结果传递给下一个！
     *
     * @param result
     */
    public Promise(RESULT result) {
        this.result = result;
    }

    /**
     * 传入callback返回的结果，并传入callback运行中产生error 或者未处理的error
     *
     * @param result 新的callback计算的结果
     * @param error  传入callback调用产生的error 或者继承上一个的Promise未处理的error
     */
    public Promise(RESULT result, Throwable error) {
        this.result = result;
        this.error = error;
    }

    /**
     * 直接传递错误
     *
     * @param error
     */
    public Promise(Throwable error) {
        this.error = error;
    }

    /**
     * 直接调用构造函数的返回新的Promise T call()
     *
     * @param callback
     */
    public Promise(Callback<RESULT> callback) {
        catchExec(() -> result = callback.call());
    }

    /**
     * R call(I)
     *
     * @param callback
     * @param in
     * @param <INPUT>
     */
    public <INPUT> Promise(CallbackWithParam<RESULT, INPUT> callback, INPUT in) {
        catchExec(() -> result = callback.call(in));
    }

    /**
     * void call(I)
     *
     * @param callback
     * @param in
     */
    public Promise(RunnableWithParam<RESULT> callback, RESULT in) {
        catchExec(() -> callback.run(in));
    }

    /**
     * void call()
     *
     * @param callback
     */
    public Promise(Runnable callback) {
        catchExec(() -> callback.run());
    }


    /**
     * 不处理上一个Promise返回的结果，
     * 并返回新的结果给 下一个Promise
     * void call()
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> then(Callback<R> callback) {
        return new Promise<>(catchExec(() -> callback.call()), error);
    }

    /**
     * 处理上一个Promise返回的结果,
     * 并返回新的结果给 下一个Promise
     * R call(I)
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> then(CallbackWithParam<R, RESULT> callback) {
        return new Promise<>(catchExec(() -> callback.call(result)), error);
    }

    // 没有返回类型的,（相当于）继承了RESULT 类型！

    /**
     * 处理上一个Promise返回的结果,
     * 不返回新的结果给 下一个Promise
     * void call(I)
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> then(RunnableWithParam<RESULT> callback) {
        return new Promise<>(catchExec(() -> callback.run(result)), error);
    }

    /**
     * 不处理上一个Promise返回的结果,
     * 不返回新的结果给 下一个Promise
     * void call()
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> then(Runnable callback) {
        return new Promise<>(catchExec(() -> callback.run()), error);
    }

    /**
     * 最终
     * equals finally
     * void call()
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> lastly(Callback<R> callback) {
        return then(callback);
    }

    /**
     * 最终
     * equals finally
     * R call(I)
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> lastly(CallbackWithParam<R, RESULT> callback) {
        return then(callback);
    }

    // 没有返回类型的,（相当于）继承了RESULT 类型！

    /**
     * 最终
     * equals finally
     * void call(I)
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> lastly(RunnableWithParam<RESULT> callback) {
        return then(callback);
    }

    /**
     * 最终
     * equals finally
     * void call()
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> lastly(Runnable callback) {
        return then(callback);
    }

    /**
     * 判断result是否不等于null
     * 也就是存在啊
     *
     * @return
     */
    public boolean exist() {
        return result != null;
    }


    /**
     * 上一个Promise结果存在（!=null）则执行！
     * 不处理上一个Promise返回的结果，
     * 并返回新的结果给 下一个Promise
     * void call()
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> exist(Callback<R> callback) {
        //为null就不必再次传入result了
        return exist() ? then(callback) : new Promise<>(error);
    }

    /**
     * 上一个Promise结果存在（!=null）则执行！
     * 处理上一个Promise返回的结果,
     * 并返回新的结果给 下一个Promise
     * R call(I)
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> exist(CallbackWithParam<R, RESULT> callback) {
        return exist() ? then(callback) : new Promise<>(error);
    }

    // 没有返回类型的,（相当于）继承了RESULT 类型！

    /**
     * 上一个Promise结果存在（!=null）则执行！
     * 处理上一个Promise返回的结果,
     * 不返回新的结果给 下一个Promise
     * void call(I)
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> exist(RunnableWithParam<RESULT> callback) {
        return exist() ? then(callback) : new Promise<>(error);
    }

    /**
     * 上一个Promise结果存在（!=null）则执行！
     * 不处理上一个Promise返回的结果,
     * 不返回新的结果给 下一个Promise
     * void call()
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> exist(Runnable callback) {
        return exist() ? then(callback) : new Promise<>(error);
    }

    /**
     * 存在result并且是boolean类型，
     * 并进行转化
     *
     * @return
     */
    public boolean truely() {
        boolean b = false;
        if (exist() && result instanceof Boolean) {
            b = XType.cast(result);
        }
        return false;
    }

    /**
     * if result == true
     * void call()
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> truely(Callback<R> callback) {
        return truely() ? then(callback) : new Promise<>(error);
    }

    /**
     * if result == true
     * R call(I)
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> truely(CallbackWithParam<R, RESULT> callback) {
        return truely() ? then(callback) : new Promise<>(error);
    }

    // 没有返回类型的,（相当于）继承了RESULT 类型！

    /**
     * if result == true
     * void call(I)
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> truely(RunnableWithParam<RESULT> callback) {
        return truely() ? then(callback) : new Promise<>(error);
    }

    /**
     * if result == true
     * void call()
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> truely(Runnable callback) {
        return truely() ? then(callback) : new Promise<>(error);
    }

    /**
     * if result == false
     * void call()
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> falsely(Callback<R> callback) {
        return !truely() ? then(callback) : new Promise<>(error);
    }

    /**
     * if result == false
     * R call(I)
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> falsely(CallbackWithParam<R, RESULT> callback) {
        return !truely() ? then(callback) : new Promise<>(error);
    }

    // 没有返回类型的,（相当于）继承了RESULT 类型！

    /**
     * if result == false
     * void call(I)
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> falsely(RunnableWithParam<RESULT> callback) {
        return !truely() ? then(callback) : new Promise<>(error);
    }

    /**
     * if result == false
     * void call()
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> falsely(Runnable callback) {
        return !truely() ? then(callback) : new Promise<>(error);
    }

//    private <R> Promise<R> thenInherit(Promise<R> pro){
//        if(pro.error == null && error != null){
//            //继承上一个的Promise未处理的error
//            pro.error = error;
//
//        }
//
//        return pro;
//    }

    // 传入都是空！所以随便返回什么类型

    /**
     * R call() 处理上一个Promise的错误！ 不接受上一个Promise的错误并返回新的Promise！
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> except(Callback<R> callback) {
        return new Promise<>(errorHandler(() -> {
            R r = callback.call();
            // 捕获异常后将上一步的异常 清理掉
            // 能到这一步，表示编译完美无误的处理异常！
            // error 是 类中局部变量，好处是不会被 final 约束了
            error = null;
            return r;
        }), error);
    }

    // input and return 都有；传入进去后，再次有error的话就传给下一个新的

    /**
     * R call(I) 处理上一个Promise的错误！ 接受上一个Promise的错误并返回新的Promise！
     *
     * @param callback lambda代码
     * @param <R>      处理异常后返回的结果
     * @return 新的Promise对象
     */
    public <R> Promise<R> except(CallbackWithParam<R, Throwable> callback) {
        return new Promise<>(errorHandler(() -> {
            R r = callback.call(error);
            // 能到这一步，表示编译完美无误的处理异常！
            error = null;
            return r;
        }), error);
    }

    /**
     * void call(I) 处理上一个Promise的错误！ 接受上一个Promise的错误并返回新的Promise！
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> except(RunnableWithParam<Throwable> callback) {
        return new Promise<>(errorHandler(() -> {
            callback.run(error);
            // 能到这一步，表示编译完美无误的处理异常！
            error = null;
        }), error);
    }

    /**
     * void call() 处理上一个Promise的错误！ 接受上一个Promise的错误并返回新的Promise！
     *
     * @param callback lambda代码
     * @return 新的Promise对象
     */
    public Promise<RESULT> except(Runnable callback) {
        return new Promise<>(errorHandler(() -> {
            callback.run();
            // 能到这一步，表示编译完美无误的处理异常！
            error = null;
        }), error);
    }

//    public Promise<RESULT> exceptInherit(Promise<RESULT> pro){
//        if(pro.result == null && result!= null){
//            pro.result = result;
//            //捕获异常后将上一步的异常 清理掉
//            error = null;
//        }
//        return pro;
//    }


    /**
     * 捕获代码执行过程中的异常！
     *
     * @param run
     * @return
     */
    private RESULT catchExec(Runnable run) {
        ifError();
        try {
            run.run();
        } catch (Throwable e) {
            error = e;
        }
        // error 的时候 ，result是不存在的！所有返回null是正确的！
        return null;
    }

    /**
     * 捕获代码执行过程中的异常！
     *
     * @param run
     * @param <R>
     * @return
     */
    private <R> R catchExec(Callback<R> run) {
        ifError();
        try {
            return run.call();
        } catch (Throwable e) {
            error = e;
        }
        return null;
    }

    /**
     * 捕获代码执行过程中的异常！ for except 的 异常处理代码
     *
     * @param run
     * @return
     */
    private RESULT errorHandler(Runnable run) {
        try {
            run.run();
        } catch (Throwable e) {
            error = e;
        }
        // error 的时候 ，result是不存在的！所有返回null是正确的！
        return null;
    }

    /**
     * 捕获代码执行过程中的异常！ for except 的 异常处理代码
     *
     * @param run
     * @param <R>
     * @return
     */
    private <R> R errorHandler(Callback<R> run) {
        try {
            return run.call();
        } catch (Throwable e) {
            error = e;
        }
        return null;
    }

    /**
     * 检查错误，用于错误是否必须显示、及时 调用except 处理掉 如果错误存在并且及时错误开启的话
     *
     * @param callback
     */
    private void ifError(RunnableWithParam<Throwable> callback) {
        if (exIndeed && error != null) {
            callback.run(error);
        }
    }

    private void ifError() {
        ifError((e) -> {
            throw new RuntimeException("Some errors occur!" + "Please use \"except\" to handle error next step!",
                    error);
        });
    }

    /**
     * R call (I)
     *
     * @param <R>
     * @param <I>
     */
    public interface CallbackWithParam<R, I> {
        R call(I in);
    }

    /**
     * void run(I)
     *
     * @param <I>
     */
    public interface RunnableWithParam<I> {
        void run(I in);
    }

    /**
     * R call()
     *
     * @param <R>
     */
    public interface Callback<R> {
        R call();
    }

    public Throwable getError() {
        return error;
    }

    public RESULT getResult() {
        return result;
    }

    public static void implyErrorHandler(boolean implicit) {
        exIndeed = !implicit;
    }

    /**
     * give value promise
     *
     * @param r
     * @param <R>
     * @return
     */
    public static <R> Promise<R> resolve(R r) {
        return new Promise<>(r);
    }

    /**
     * exception result promise
     *
     * @param e
     * @return
     */
    public static Promise<Throwable> reject(Throwable e) {
        return new Promise<>(e);
    }
}
