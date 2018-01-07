import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Aksesoria {

    String marka="";
    String model="";
    String dodatkoweUwagi="";
    String typ = "";
    String wady = "";
    String zalety= "";
    String podsumowanie = "";
    String liczbaGwiazdek = "";
    String data = "";
    String autor = "";
    String polecam = "";
    String przydatna = "";
    String nierzydatan = "";


    public void extract(int id){

        try {
            Document doc = Jsoup.connect("https://www.ceneo.pl/" + id).get();

            Elements dodatkoweUwagiEl = doc.select("div.wrapper").select("div.product-content");
            Elements markaModel = doc.select("div.wrapper").select("nav.breadcrumbs");
            Elements opinie = doc.select("ol.product-reviews li.review-box");

            for (Element element : dodatkoweUwagiEl) {
                dodatkoweUwagi = element.select("div.ProductSublineTags").text();
            }
            for (Element element : markaModel) {
                model = element.select("nav.breadcrumbs strong").text();

                typ = element.select("span.breadcrumb span").eq(1).text();
            }

            for(Element element: opinie) {
                wady = element.select("div.cons-cell ul li").text();
                zalety = element.select("div.pros-cell ul li").text();
                podsumowanie = element.select("p.product-review-body").first().text();
                autor = element.select("div.reviewer-name-line").text();
                data = element.select("span.review-time time").attr("datetime");
                przydatna = element.select("button.vote-yes span").text();
                nierzydatan = element.select("button.vote-no span").text();


            }


        }catch(Exception e){e.printStackTrace();}

    }

    public void transform () {
        data = data.replace(".", "-");
        if(autor == null){
            autor="anonim";

        }
    }


    public void load()
    {
        DBConnection conn = new DBConnection();
        conn.insert("1", marka, model, dodatkoweUwagi, typ, wady, zalety, podsumowanie, liczbaGwiazdek, data, autor, polecam, przydatna,nierzydatan);
    }

    public void parse (int id){
        String marka="";
        String model="";
        String dodatkoweUwagi="";
        String typ = "";
        String wady = "";
        String zalety= "";
        String podsumowanie = "";
        String liczbaGwiazdek = "";
        String data = "";
        String autor = "";
        String polecam = "";
        String przydatna = "";
        String nierzydatan = "";
        try {
            Document doc = Jsoup.connect("https://www.ceneo.pl/" + id).get();

            Elements dodatkoweUwagiEl = doc.select("div.wrapper").select("div.product-content");;
            Elements markaModel = doc.select("div.wrapper").select("nav.breadcrumbs");
            Elements opinie = doc.select("ol.product-reviews li.review-box");

            for (Element element : dodatkoweUwagiEl) {
                dodatkoweUwagi = element.select("div.ProductSublineTags").text();
            }
            for (Element element : markaModel) {
                model = element.select("nav.breadcrumbs strong").text();

                typ = element.select("span.breadcrumb span").eq(1).text();
            }

            for(Element element: opinie) {
                wady = element.select("div.cons-cell ul li").text();
                zalety = element.select("div.pros-cell ul li").text();
                podsumowanie = element.select("p.product-review-body").first().text();
                autor = element.select("div.reviewer-name-line").text();
                data = element.select("span.review-time time").attr("datetime");
                przydatna = element.select("button.vote-yes span").text();
                nierzydatan = element.select("button.vote-no span").text();




                DBConnection conn = new DBConnection();
                conn.insert("1", marka, model, dodatkoweUwagi, typ, wady, zalety, podsumowanie, liczbaGwiazdek, data, autor, polecam, przydatna,nierzydatan);
            }


        }catch(Exception e){e.printStackTrace();}


    }
}
