package test.kompas.ux;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class testUX {

    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver",".\\driver\\chromedriver88.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.kompas.id/baca/opini/2021/01/14/krisis-lingkungan-dan-bencana-pandemi/");

    }

    @Test (priority = 1, description = "Check Berlangganan button")
    public void checkURL(){
        WebElement btnLangganan = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div/article/div[4]/div[4]/div[2]/div[2]/div[4]/a"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",btnLangganan);
        String urlLangganan = btnLangganan.getAttribute("href");
        Assert.assertTrue(urlLangganan.contains("https://gerai.kompas.id/belanja/harian-kompas/kompas-digital-premium/"));
    }

    @Test (priority = 2, description = "Check font list size")
    public void listFont(){
        WebElement btnFontSize = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/div/div/div[2]/div[1]/div"));
        btnFontSize.click();
        WebElement listFontSize = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/div/div/div[2]/div[1]/div/nav"));
        Assert.assertTrue(listFontSize.isDisplayed());
    }

    @Test (priority = 3, description = "Check Facebook button")
    public void checkFacebook(){
        WebElement btnShare = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/article/div[2]/div[2]/h1"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",btnShare);
        WebElement btnShareFB = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div/article/div[2]/div[4]/div[2]/div[2]/div/a[1]"));
        btnShareFB.click();
        String MainWindow = driver.getWindowHandle();
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();

        while(i1.hasNext()){
            String ChildWindow = i1.next();
            if(!MainWindow.equalsIgnoreCase(ChildWindow)){
                driver.switchTo().window(ChildWindow);
                WebElement facebook = driver.findElement(By.id("homelink"));
                String textFB = facebook.getText();
                assertEquals(textFB, "Facebook");
                driver.close();
            }
        }
        driver.switchTo().window(MainWindow);
    }

    @Test (priority = 4, description = "Check Twitter button")
    public void checkTwitter(){
        WebElement btnShareTwt = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div/article/div[2]/div[4]/div[2]/div[2]/div/a[2]"));
        btnShareTwt.click();
        String MainWindow = driver.getWindowHandle();
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();

        while(i1.hasNext()){
            String ChildWindow = i1.next();
            if(!MainWindow.equalsIgnoreCase(ChildWindow)){
                driver.switchTo().window(ChildWindow);
                WebElement twitter = driver.findElement(By.xpath("//*[@id=\"react-root\"]"));
                Assert.assertTrue(twitter.isDisplayed());
                driver.close();
            }
        }
        driver.switchTo().window(MainWindow);
    }

    @Test (priority = 5, description = "Check Whatsapp button")
    public void checkWhatsapp(){
        WebElement btnShareWA = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[1]/div/article/div[2]/div[4]/div[2]/div[2]/div/a[3]"));
        btnShareWA.click();
        String MainWindow = driver.getWindowHandle();
        Set<String> s1=driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();

        while(i1.hasNext()){
            String ChildWindow = i1.next();
            if(!MainWindow.equalsIgnoreCase(ChildWindow)){
                driver.switchTo().window(ChildWindow);
                WebElement whatsapp = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div[1]/h1"));
                Assert.assertTrue(whatsapp.isDisplayed());
                driver.close();
            }
        }
        driver.switchTo().window(MainWindow);
    }

    @Test(priority = 6, description = "Check 5 Artikel Terkait")
    public void checkArtikelTerkait() {
        WebElement artikelTerkait = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/section/div[1]/div/div[1]/div/div/div/div[2]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", artikelTerkait);
//        List<WebElements> results = driver.findElements(By.className("strength"));
        int countArtikelTerkait = driver.findElements(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/section/div[1]/div/div[1]/div/div/div/div[2]/div[1]/div/div")).size();
        assertEquals(countArtikelTerkait, 4);
    }

    @Test(priority = 7, description = "Check 5 Artikel Terpopuler")
    public void checkArtikelTerpopuler() {
        int countArtikelTerpopuler = driver.findElements(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/section/div[1]/div/div[2]/div/div/div[2]/ul/li")).size();
        assertEquals(countArtikelTerpopuler, 5);
    }

    @Test(priority = 8, description = "Check 5 Artikel Terbaru")
    public void checkArtikelTerbaru() {
        int countArtikelTerbaru = driver.findElements(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/section/div[2]/div/div[2]/div/div/div[2]/ul/li")).size();
        assertEquals(countArtikelTerbaru, 5);
    }

    @Test(priority = 9, description = "Check 5 Artikel Lainnya")
    public void checkArtikelLainnya() {
        int countArtikelLainnya = driver.findElements(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/section/div[2]/div/div[1]/div/div/div/div[2]/div[1]/div/div")).size();
        assertEquals(countArtikelLainnya, 4);
    }

    @Test(priority = 10, description = "Check Font family")
    public void checkFont() {
        WebElement fontArtikel = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/article/div[2]/div[3]/p"));
        String fontArtikelFamily = fontArtikel.getCssValue("font-family");
        assertEquals(fontArtikelFamily, "Georgia, \"Times New Roman\", Times, serif");
    }

    @Test(priority = 11, description = "Check posisi artikel ditengah")
    public void checkPosisiArtikel() {
        WebElement posisiArtikel = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/article/div[2]"));
        String posisiKiriArtikel = posisiArtikel.getCssValue("padding-left");
        assertEquals(posisiKiriArtikel, "0px");
        String posisiKananArtikel = posisiArtikel.getCssValue("padding-right");
        assertEquals(posisiKananArtikel, "0px");
    }

    @Test(priority = 12, description = "Check format tanggal dan waktu ISO 8601")
    public void checkFormat() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String kalender = date.format(formatter);
        WebElement tanggalwaktu = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div/div[1]/div/article/div[2]/div[4]/div[1]/div[2]/time"));
        assertTrue(kalender.equals(tanggalwaktu));
    }

    @AfterClass
    public void close(){
        driver.close();
        driver.quit();
    }

}
