

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ParseHTML {

    public Map<Integer, String> metas = new HashMap();



    public String parse(int id){
        String category = null;
        try
        {
             Document doc = Jsoup.connect("https://www.ceneo.pl/" + id).get();

             System.out.println("https://www.ceneo.pl/" + id);

            Elements elements = doc.select("div.wrapper").select("nav.breadcrumbs");



            for(Element el: elements){
                category = el.select("span.breadcrumb span").eq(1).text();
                System.out.println(category);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return category;
    }

    Komputery komputery = new Komputery();
    Fotografia fotografia = new Fotografia();
    Aksesoria aksesoria = new Aksesoria();
    public static ParseHTML ph = new ParseHTML();

    public void extract(int id){

        String categiry =
                ph.parse(id);

        ph.metas.put(1, "Komputery");
        ph.metas.put(2, "Fotografia");
        ph.metas.put(3, "Telefony i akcesoria");

        if(categiry=="Komputery")
        {System.out.println("Komputery");

            komputery.extract(id);
        }
        if(categiry == "Fotografia"){
            System.out.println("Fotografia");

            fotografia.extract(id);
        }
        if(categiry=="Telefony i akcesoria")
        {System.out.println("Telefony i akcesoria");

            aksesoria.extract(id);
        }

    }

    public void trnsform (int id){

        String categiry =
                ph.parse(id);

        ph.metas.put(1, "Komputery");
        ph.metas.put(2, "Fotografia");
        ph.metas.put(3, "Telefony i akcesoria");

        if(categiry=="Komputery")
        {System.out.println("Komputery");
            Komputery komputery = new Komputery();
            komputery.transform();
        }
        if(categiry == "Fotografia"){
            System.out.println("Fotografia");
            Fotografia fotografia = new Fotografia();
            fotografia.transform();
        }
        if(categiry=="Telefony i akcesoria")
        {System.out.println("Telefony i akcesoria");
            Aksesoria aksesoria = new Aksesoria();
            aksesoria.transform();
        }
    }

    public void load (int id){

        String categiry =
                ph.parse(id);

        ph.metas.put(1, "Komputery");
        ph.metas.put(2, "Fotografia");
        ph.metas.put(3, "Telefony i akcesoria");

        if(categiry=="Komputery")
        {System.out.println("Komputery");
            Komputery komputery = new Komputery();
            komputery.load();
        }
        if(categiry == "Fotografia"){
            System.out.println("Fotografia");
            Fotografia fotografia = new Fotografia();
            fotografia.load();
        }
        if(categiry=="Telefony i akcesoria")
        {System.out.println("Telefony i akcesoria");
            Aksesoria aksesoria = new Aksesoria();
            aksesoria.load();
        }
    }

    public void etl(int id){
        String categiry =
                ph.parse(id);

        ph.metas.put(1, "Komputery");
        ph.metas.put(2, "Fotografia");
        ph.metas.put(3, "Telefony i akcesoria");

        if(categiry=="Komputery")
        {System.out.println("Komputery");
            Komputery komputery = new Komputery();
            komputery.parse(id);
            System.out.println(":)");
        }
        if(categiry == "Fotografia"){
            System.out.println("Fotografia");
            Fotografia fotografia = new Fotografia();
            fotografia.parse(id);
        }
        if(categiry=="Telefony i akcesoria")
        {System.out.println("Telefony i akcesoria");
            Aksesoria aksesoria = new Aksesoria();
            aksesoria.parse(id);
        }
    }

    int id;

    public void setId(int _id){
        id=_id;
    }

    public int getId(){
        return id;
    }







    public static void main(String[] args)
    {


        JFrame frame = new JFrame("ETL");
        final JButton extract = new JButton();
        extract.setText("E");
        final JButton transform = new JButton();
        transform.setText("T");
        final JButton load = new JButton();
        load.setText("L");
        final JButton etl = new JButton();
        etl.setText("ETL");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton ok = new JButton("OK");
        JButton delete = new JButton("Clear database");

        final JTextField idField = new JTextField("Proszę wprowadzić id");
        idField.setSize(10,5);
        final JLabel info = new JLabel();
        JButton writeIntofile = new JButton("Write data to file");
        extract.setEnabled(false);
        etl.setEnabled(false);
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DBConnection db = new DBConnection();
                db.delete();
                info.setText("Data deleted");

            }
        });
        writeIntofile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DBConnection db = new DBConnection();
                db.selectAll();

            }
        });
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                info.setText("Id Towaru: "+id);

                ph.setId(Integer.parseInt(id));
                extract.setEnabled(true);
                etl.setEnabled(true);
            }
        });
        //idd.add(idField);
       // idd.add(ok);
        transform.setEnabled(false);
        load.setEnabled(false);
        extract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ph.extract(ph.getId());
                info.setText("Extract is runnnig...");
                extract.setEnabled(false);
                transform.setEnabled(true);
            }
        });
        transform.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ph.trnsform(ph.getId());
                info.setText("Transformvis running...");
                load.setEnabled(true);
                transform.setEnabled(false);

            }
        });
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ph.load(ph.getId());
                info.setText("Load is running...");
                extract.setEnabled(true);
                load.setEnabled(false);
            }
        });

        etl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ph.etl(ph.getId());
                info.setText("ETL started...");
            }
        });

        panel.add(idField);
        panel.add(ok);
        panel.add(info);
        panel.add(delete);
        frame.add(panel);
       // frame.add(idd);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // JLabel textLabel = new JLabel("I'm a label in the window",SwingConstants.CENTER); textLabel.setPreferredSize(new Dimension(300, 100));
       // frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        panel.add(extract);
        panel.add(transform);
        panel.add(load);
        panel.add(etl);
        frame.pack();
        frame.setVisible(true);





    }
}




