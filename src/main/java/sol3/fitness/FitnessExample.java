package sol3.fitness;

import fitnesse.responders.run.SuiteResponder;
import fitnesse.wiki.*;

public class FitnessExample {
    public String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        if (pageData.hasAttribute("Test"))
            wrapWithSetupAndTearDown(pageData, includeSuiteSetup);

        return pageData.getHtml();
    }

    private void wrapWithSetupAndTearDown(PageData pageData, boolean includeSuiteSetup) throws Exception {
        WikiPage wikiPage = pageData.getWikiPage();
        StringBuffer buffer = new StringBuffer();

        if (includeSuiteSetup) {
            getPage(wikiPage, buffer, "!include -setup .", SuiteResponder.SUITE_SETUP_NAME);
        }
        getPage(wikiPage, buffer, "!include -setup .", "SetUp");
        buffer.append(pageData.getContent());
        getPage(wikiPage, buffer, "TearDown", "\n" + "!include -page .");
        if (includeSuiteSetup) {
            getPage(wikiPage, buffer, "!include -page .", SuiteResponder.SUITE_TEARDOWN_NAME);
        }

        pageData.setContent(buffer.toString());
    }

    private void getPage(WikiPage wikiPage, StringBuffer buffer, String includeSetup, String name) throws Exception {
        WikiPage page = PageCrawlerImpl.getInheritedPage(name, wikiPage);
        if (page != null) {
            WikiPagePath pagePath = wikiPage.getPageCrawler().getFullPath(page);
            String pagePathName = PathParser.render(pagePath);
            buffer.append(includeSetup).append(pagePathName).append("\n");
        }
    }
}
