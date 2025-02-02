package swag.lab.test.plp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import swag.lab.test.utility.coverage.BeforeAll;
import swag.lab.test.utility.fun.LogInFun;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSortingTest extends BeforeAll {

    @Test(priority = 1)
    public void sortLowToHigh() throws IOException {

        LogInFun.logInValidAccount(driver, wait);
        List<WebElement> beforeFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for (WebElement price : beforeFilterPrice){
            beforeFilterPriceList.add(Double.valueOf(price.getText().replace("$", "")));
        }

        Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
        dropdown.selectByVisibleText("Price (low to high)");
        List<WebElement> afterFilterPrice = driver.findElements(By.className("inventory_item_price"));
        List<Double> afterFilterPriceList = new ArrayList<>();
        for (WebElement p: afterFilterPrice){
            afterFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        Collections.sort(beforeFilterPriceList);
        Assert.assertEquals(beforeFilterPriceList, afterFilterPriceList);
    }
}
