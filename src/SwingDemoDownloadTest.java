import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SwingDemoDownloadTest {
public static void main(String[] args) throws Exception {
	Document jsoup=Jsoup.parse(new URL("https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html"), 1000);
	Elements elements=jsoup.select("html body div.MainFlow_wide div#PageContent table tbody tr td a");
	for (Element element : elements) {
		String str=element.attr("href");
		if(str.endsWith(".zip")) {
//			https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-ComboBoxDemoProject.zip
			System.out.println(str.replace("..", "https://docs.oracle.com/javase/tutorial/uiswing/examples/"));
		}
	}
}
}
