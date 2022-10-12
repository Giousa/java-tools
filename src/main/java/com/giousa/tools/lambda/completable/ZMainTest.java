package com.giousa.tools.lambda.completable;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZMainTest {

    public static void main(String[] args) {
        System.out.println("开始请求：");
        long startTime = System.currentTimeMillis();

        test06();

        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println("请求结束！");
    }


    /**
     * 方式1： 传统方式  耗时: 6785
     */
    private static void test01() {
        int i1 = JDManager.getPrice() - JDManager.getDiscounts();
        int i2 = PDDManager.getPrice() - PDDManager.getDiscounts();
        int i3 = TBManager.getPrice() - TBManager.getDiscounts();
        Integer minPrice = Stream.of(i1, i2, i3).min(Comparator.comparingInt(Integer::new)).get();
        System.out.println("总价：" + (i1 + i2 + i3) + ", 最低价：" + minPrice);
    }

    /**
     * 方式2： 多线程+Future  耗时: 2506
     * Future模式是多线程设计常用的一种设计模式。
     * Future模式可以理解成：我有一个任务，提交给了Future，Future替我完成这个任务。期间我自己可以去做任何想做的事情。一段时间之后，我就便可以从Future那儿取出结果。
     * Future提供了三种功能：
     * 1、判断任务是否完成
     * 2、能够中断任务
     * 3、能够获取任务执行的结果
     */
    private static void test02() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        Future<PriceResult> future1 = threadPool.submit(() -> PriceManager.computeRealPrice(JDManager.getPrice(), JDManager.getDiscounts(), "JD"));
        Future<PriceResult> future2 = threadPool.submit(() -> PriceManager.computeRealPrice(PDDManager.getPrice(), PDDManager.getDiscounts(), "PDD"));
        Future<PriceResult> future3 = threadPool.submit(() -> PriceManager.computeRealPrice(TBManager.getPrice(), TBManager.getDiscounts(), "TB"));

        // 等待所有线程结果都处理完成，然后从结果中计算出最低价
        //三段式的编程：1.启动多线程任务2.处理其他事3.收集多线程任务结果
        PriceResult priceResult = Stream.of(future1, future2, future3).map(it -> {
            try {
                //多线程执行子任务时，倘若超时5秒，就直接返回,防止无限时间的等待
                return it.get(5L, TimeUnit.SECONDS);
            } catch (Exception e) {
                try {
                    System.out.println("超时......" + it.get().getProductName());
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (ExecutionException ex) {
                    throw new RuntimeException(ex);
                }
                return null;
            }
        }).filter(Objects::nonNull).min(Comparator.comparingInt(PriceResult::getRealPrice)).get();

        System.out.println("最低价格: " + priceResult);
    }

    /**
     * 方式3： CompletableFuture方式  耗时: 1305
     */
    private static void test03() {
        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> jdFuture = CompletableFuture.supplyAsync(() -> JDManager.getPrice()).thenCombine(CompletableFuture.supplyAsync(() -> JDManager.getDiscounts()), PriceManager::computeRealPrice);
        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> pddFuture = CompletableFuture.supplyAsync(() -> PDDManager.getPrice()).thenCombine(CompletableFuture.supplyAsync(() -> PDDManager.getDiscounts()), PriceManager::computeRealPrice);
        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> tbFuture = CompletableFuture.supplyAsync(() -> TBManager.getPrice()).thenCombine(CompletableFuture.supplyAsync(() -> TBManager.getDiscounts()), PriceManager::computeRealPrice);

        // 排序并获取最低价格
        PriceResult priceResult = Stream.of(jdFuture, pddFuture, tbFuture).map(CompletableFuture::join).sorted(Comparator.comparingInt(PriceResult::getRealPrice)).findFirst().get();

        System.out.println("最低价格: " + priceResult);
    }

    /**
     * 方法名称	    作用描述
     * thenApply	对CompletableFuture的执行后的具体结果进行追加处理，并将当前的CompletableFuture泛型对象更改为处理后新的对象类型，返回当前CompletableFuture对象。
     * thenCompose	与thenApply类似。区别点在于：此方法的入参函数返回一个CompletableFuture类型对象。
     * thenAccept	与thenApply方法类似，区别点在于thenAccept返回void类型，没有具体结果输出，适合无需返回值的场景。
     * thenRun	    与thenAccept类似，区别点在于thenAccept可以将前面CompletableFuture执行的实际结果作为入参进行传入并使用，但是thenRun方法没有任何入参，只能执行一个Runnable函数，并且返回void类型。
     */
    private static void test04() {
        try {
            CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(TBManager::getDiscounts);
            Integer result = integerCompletableFuture.get();
            System.out.println("获取结果：" + result);

            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(TBManager::getDiscounts);


            CompletableFuture<PriceResult> priceResultCompletableFuture = CompletableFuture.supplyAsync(() -> {
                        System.out.println("获取TBManager的折扣");
                        return TBManager.getDiscounts();
                    })
                    .thenApply(it -> {
                        System.out.println("打印结果： " + it);
                        return 10000 + it;
                    }).thenCombine(CompletableFuture.supplyAsync(() -> {
                        System.out.println("获取PDDManager的价格");
                        return PDDManager.getPrice();
                    }), PriceManager::computeRealPrice);

            //倘若不调用get阻塞方法，上面会打印：priceResultCompletableFuture：：java.util.concurrent.CompletableFuture@7c3df479[Not completed]
            PriceResult priceResult = priceResultCompletableFuture.get();

            System.out.println("priceResult = " + priceResult);
        } catch (Exception e) {

        }

    }

    /**
     * 并发执行-普通方式 耗时：4235
     */
    private static void test05() {
        List<String> list = Lists.newArrayList("猫", "大熊猫", "企鹅", "罗斯福麋鹿");

        PriceResult priceResult = list.stream()
                .map(product ->
                        CompletableFuture.supplyAsync(() -> ProductManager.getPrice(product))
                                .thenCombine(
                                        CompletableFuture.supplyAsync(() -> ProductManager.getPrice(product)),
                                        PriceManager::computeRealPrice))
                .map(CompletableFuture::join)
                .sorted(Comparator.comparingInt(PriceResult::getRealPrice))
                .findFirst()
                .get();

        System.out.println("priceResult = " + priceResult);
    }

    /**
     * 并发执行-分开执行 耗时：2156
     *
     * 因为Stream的操作具有延迟执行的特点，且只有遇到终止操作（比如collect方法）的时候才会真正的执行。
     * 所以遇到这种需要并行处理且需要合并多个并行处理流程的情况下，需要将并行流程与合并逻辑放到两个Stream中，这样分别触发完成各自的处理逻辑，就可以了。
     */
    private static void test06() {
        List<String> list = Lists.newArrayList("猫", "大熊猫", "企鹅", "罗斯福麋鹿");

        // 先触发各自平台的并行处理
        List<CompletableFuture<PriceResult>> completableFutures = list.stream()
                .map(product ->
                        CompletableFuture.supplyAsync(() -> ProductManager.getPrice(product))
                                .thenCombine(
                                        CompletableFuture.supplyAsync(() -> ProductManager.getDiscounts(product)),
                                        PriceManager::computeRealPrice))
                .collect(Collectors.toList());

        // 在独立的流中，等待所有并行处理结束，做最终结果处理
        PriceResult priceResult = completableFutures.stream()
                .map(CompletableFuture::join)
                .sorted(Comparator.comparingInt(PriceResult::getRealPrice))
                .findFirst()
                .get();

        System.out.println("priceResult = " + priceResult);
    }


}
