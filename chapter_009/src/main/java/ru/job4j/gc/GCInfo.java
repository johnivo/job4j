package ru.job4j.gc;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 28.08.2019
 */
public class GCInfo {

    public static void main(String[] args) {
        try {
            List<GarbageCollectorMXBean> gcMxBeans = ManagementFactory.getGarbageCollectorMXBeans();

            for (GarbageCollectorMXBean gcMxBean : gcMxBeans) {
                System.out.println(gcMxBean.getName());
                System.out.println(gcMxBean.getObjectName());
                System.out.println();
            }

        } catch (RuntimeException re) {
            throw re;
        } catch (Exception exp) {
            throw new RuntimeException(exp);
        }
    }
}
