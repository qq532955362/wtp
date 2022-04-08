package com.wtp.base.common.exception;

/**
 * @author wangtaiping
 * 2022/4/8 14:10
 */
public class ExceptionExtend {

    public static class Annoyance extends Exception {
    }

    public static class Sneeze extends Annoyance {
    }

    public static void main(String[] args) throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance annoyance) {
                System.out.println("Caught Annoyance");
                throw annoyance;
            }
        } catch (Sneeze sneeze) {
            System.out.println("Caught Sneeze");
            return;
        } finally {
            System.out.println("Hello world");
        }
    }

}
