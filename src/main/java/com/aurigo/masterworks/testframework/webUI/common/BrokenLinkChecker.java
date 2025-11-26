package com.aurigo.masterworks.testframework.webUI.common;
import com.aurigo.masterworks.testframework.utilities.http.HttpUtility;
import com.aurigo.masterworks.testframework.utilities.http.IHttpUtility;
import com.aurigo.masterworks.testframework.webUI.generic.GenericForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BrokenLinkChecker extends GenericForm {

    private final IHttpUtility httpUtility = new HttpUtility();
    List<Integer> acceptedStatusCodeList = new ArrayList<>();

    private final By helpPageNavbarBrand;
    private final By linksInMainContent;

    public BrokenLinkChecker(WebDriver driver) {
        super(driver);
        Collections.addAll(acceptedStatusCodeList, 200, 301, 302, 403);
        helpPageNavbarBrand=By.xpath(".//a[@class='navbar__brand']");
        linksInMainContent=By.xpath(".//main//a");
    }


    /**
     * Validates all the links in the page and lists out the broken links
     *
     * @return true if there are no broken links in the page
     */
    public boolean validateBrokenLinks() {
        waitHelper.waitForPageToLoad(helpPageNavbarBrand);
        logger().info("Links Available in this Page");
        var result = elementHelper.getElements(linksInMainContent)
                .stream()
                .map(element -> element.getAttribute("href"))
                .filter(Objects::nonNull) ////filter the not null links.
                .filter(link -> !link.isEmpty()) //filter the non-empty links.
                .filter(link -> !link.contains("javascript") && !link.contains("*&")) //Filter other link related patterns.
                .filter(link -> link.startsWith("http") || link.startsWith("https")) //Filter links started http and https.
                .distinct() //remove duplicate links
                .peek(link -> logger().info(link ))
                .filter(link -> !acceptedStatusCodeList.contains(httpUtility.getResponseCode(link)))
                .peek(link -> logger().fail(String.format("Broken Link : %s",link))).count();
        return result==0;
    }

}
