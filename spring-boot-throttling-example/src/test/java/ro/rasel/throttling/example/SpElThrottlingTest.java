package ro.rasel.throttling.example;

import ro.rasel.throttling.ThrottlingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpElThrottlingTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void computeWithSpElThrottling() throws Exception {
        Model model1 = new Model("Misha");
        Model model2 = new Model("Vasya");

        demoService.computeWithSpElThrottling(model1);
        demoService.computeWithSpElThrottling(model1);
        demoService.computeWithSpElThrottling(model1);
        try {
            demoService.computeWithSpElThrottling(model1);
        } catch (RuntimeException e) {
            Assert.isTrue(e instanceof ThrottlingException, "ThrottlingException should be thrown!");
        }

        demoService.computeWithSpElThrottling(model2);
        demoService.computeWithSpElThrottling(model2);
        demoService.computeWithSpElThrottling(model2);
        try {
            demoService.computeWithSpElThrottling(model2);
        } catch (RuntimeException e) {
            Assert.isTrue(e instanceof ThrottlingException, "ThrottlingException should be thrown!");
        }

        Thread.sleep(60 * 1000 + 10);

        demoService.computeWithSpElThrottling(model1);
        demoService.computeWithSpElThrottling(model1);
        demoService.computeWithSpElThrottling(model1);
        try {
            demoService.computeWithSpElThrottling(model1);
        } catch (RuntimeException e) {
            Assert.isTrue(e instanceof ThrottlingException, "ThrottlingException should be thrown!");
        }

        demoService.computeWithSpElThrottling(model2);
        demoService.computeWithSpElThrottling(model2);
        demoService.computeWithSpElThrottling(model2);
        try {
            demoService.computeWithSpElThrottling(model2);
        } catch (RuntimeException e) {
            Assert.isTrue(e instanceof ThrottlingException, "ThrottlingException should be thrown!");
        }
    }
}
