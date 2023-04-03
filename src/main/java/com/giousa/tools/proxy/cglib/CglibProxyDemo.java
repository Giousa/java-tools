//package com.giousa.tools.proxy.cglib;
//
///**
// * cglib代理是通过父子继承关系创建代理
// * 代理对象自身是作为一个子类型的存在
// */
//public class CglibProxyDemo {
//
//    static class Target {
//        public void foo() {
//            System.out.println(" target foo");
//        }
//    }
//
//    public static void main(String[] args) {
//        Target target = new Target();
//        //Enhancer Spring框架有依赖
//        Target proxy = (Target)Enhancer.create(Target.class, new MethodInterceptor() {
//            // o : 代理类对象自身
//            //method : 代理类执行的方法
//            //args : 方法执行参数
//            //methodProxy :  方法代理【采用这个参数，可以避免使用方法反射进行调用，但是内部未使用反射】
//            @Override
//            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//
//               /* System.out.println("before");
//                Object result = method.invoke(target, args);
//                System.out.println("after");
//                return result;*/
//
//                //methodProxy 使用 ，使用目标进行代理
//                /*System.out.println("before");
//                Object result = methodProxy.invoke(target,args);
//                System.out.println("after");
//                return result;*/
//
//                //methodProxy 使用 ，使用自身进行代理
//                System.out.println("before");
//                Object result = methodProxy.invokeSuper(o,args);
//                System.out.println("after");
//                return result;
//
//            }
//        });
//        proxy.foo();
//    }
//
//}
