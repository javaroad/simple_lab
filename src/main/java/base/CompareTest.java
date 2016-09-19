package base;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class CompareTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompareTest.class);

    public static class A {
        private int id;
        private int qty;

        public A(int id, int qty) {
            this.id = id;
            this.qty = qty;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    public static void main(String[] args) {
        try {
            List<A> list = Lists.newArrayList(new A(1, 0), new A(2, 9), new A(3, 0), new A(4, 1), new A(5, 0));
            Collections.sort(list, new Comparator<A>() {

                @Override
                public int compare(A o1, A o2) {
                    if (o1.qty == 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            LOGGER.debug("{}", list);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
}
