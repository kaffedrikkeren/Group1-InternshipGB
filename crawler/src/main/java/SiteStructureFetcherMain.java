import sitefetcher.LinkChecker;

public class SiteStructureFetcherMain {
    public static void main(String[] args) throws Exception {
        //SiteStructureFetcher.updateSiteStructure("https://www.gazeta.ru");
        Downloader downloader = new Downloader();
        System.out.println(downloader.downloadGz("https://lenta.ru/sitemap.xml.gz"));

        LinkChecker.addSite("https://ria.ru/");
        SiteStructureFetcher siteStructureFetcher = new SiteStructureFetcher();
        siteStructureFetcher.updateSiteStructure("https://ria.ru/", null);
    }
}