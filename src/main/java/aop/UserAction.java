package aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author zxf
 * 演示aop测试类
 */
@Controller
public class UserAction {
	
	@MethodIntercept(second=30)
    public void queryUsers(){

        System.out.println("查询所有用户【all users list】");
      //  throw new RuntimeException("aaa");
    }

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");

        UserAction userAction = (UserAction)ctx.getBean("userAction");
        userAction.queryUsers();

        ctx.destroy();
    }
}