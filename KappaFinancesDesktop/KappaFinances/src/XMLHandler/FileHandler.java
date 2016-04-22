package XMLHandler;

import newpackage.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Simão Martin
 */
public class FileHandler {
    
    private String filepath;
    private DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder;
    Document doc;
    
    
    FileHandler() throws IOException, ParserConfigurationException, SAXException
    {
        this.docBuilder = docFactory.newDocumentBuilder();
        filepath = "finances.xml";
        
        
        System.out.println("Hello KIKO");
        File fileXML = new File(this.filepath);
        
        // Verifica se o arquivo XML já existe
        if(!fileXML.exists()){
            System.out.println("\nNAO EXISTE");
            
            // Cria e adiciona a primeira linha ao XML
            new File("finances.xml").createNewFile();
            
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(this.filepath));
            Scanner in = new Scanner(System.in);
            buffWrite.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<list>\n</list>");
            buffWrite.close();
            
            
        }
        
        doc = docBuilder.parse(filepath);
        
        if(fileXML.exists()){
            System.out.println("\nAGORA EXISTE");

        }
    }
    
    /**
     * Este método adiciona uma finança ao XML.
     * 
     * @param financa   A finança que será adicionada
     * @throws SAXException
     * @throws IOException 
     */
    public void addFinanca(Financa financa) throws SAXException, IOException
    {
        Document doc = docBuilder.parse(filepath);
        Calendar date = financa.date;
        
        // Pega o ano da financa
        //Node root = doc.getFirstChild();
        //Node root2 = root.getFirstChild();
        
        //NodeList nodeList = root.getChildNodes();
        //Node no = nodeList.item(0);
        
        //System.out.println(no.getNodeName() + "\n:)\n" +nodeList.toString()); 
        XStream stream = new XStream(new DomDriver());
        stream.autodetectAnnotations(true);
        stream.alias("financa", Despesa.class);
        List<Financa> array = new ArrayList<>();
        
        Financa financa2 = new Despesa(2222, null);
        
        array.add(financa);
        array.add(financa2);
        array.add(financa);
        
        System.out.println(stream.toXML(array));
        
        
        //if(no==null)
        //{
         //   System.out.println("NAO EXISTE 2016");
        //}
    }
       
    
    public void loadMonth(String month, int Year)
    {
        
    }
    
    private String readXML()
    {
        File fileXML = new File(this.filepath);
        String linhaCat = "";
        
        try { FileReader arq = new FileReader(fileXML);
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine(); 
        // lê a primeira linha
        
        // a variável "linha" recebe o valor "null" quando o processo 
        // de repetição atingir o final do arquivo texto
        while (linha != null) { 
            linhaCat = linhaCat.concat(linha+"\n");
            linha = lerArq.readLine();
        } // lê da segunda até a última linha 
            arq.close();
        System.out.println(linhaCat);
        }
    catch (IOException e) { 
    System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); }
        
    return linhaCat;
    }
    
    private String intToMonth(int m)
    {
        String ret=null;
        if (m==0)
        {
            ret="JAN";
        }
        else if (m==1)
        {
            ret="FEB";
        }
        else if (m==2)
        {
            ret="MAR";
        }
        else if (m==3)
        {
            ret="APR";
        }
        else if (m==4)
        {
            ret="MAY";
        }
        else if (m==5)
        {
            ret="JUN";
        }
        else if (m==6)
        {
            ret="JUL";
        }
        else if (m==7)
        {
            ret="AUG";
        }
        else if (m==8)
        {
            ret="SEP";
        }
        else if (m==9)
        {
            ret="OCT";
        }
        else if (m==10)
        {
            ret="NOV";
        }
        else if (m==11)
        {
            ret="DEC";
        }
        return ret;
    }
    
    public void saveLastMonth(ArrayList<Financa> finances) throws FileNotFoundException
    {
        Calendar date = Calendar.getInstance();
        String month;
        String year;
        month = this.intToMonth(date.get(Calendar.MONTH));
        year = String.valueOf(date.get(Calendar.YEAR));
        
        String xml = this.readXML();
        int position = xml.indexOf(month+year);
        
        if (position == -1)
        {
            System.out.println("ESSE MES NAO EXISTE");
        }
        else
        {
            System.out.println("ESSE MES EXISTE na posicao " + position);
            xml = xml.replaceAll("<"+month+year+">(\n|.)*</"+month+year+">\n", "");
            System.out.println(xml);
        }
    }
}
