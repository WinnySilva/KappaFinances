package XMLHandler;

import Financas.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para manipulação do arquivo XML.
 * 
 * @author Simão Martin
 */
public class FileHandler {
    
    private final String filepath;
    private String xml;
    private final File fileXML;
    
    public FileHandler()
    {
        this.filepath = "finances.xml";
        fileXML = new File(this.filepath);
        
        // Verifica se o arquivo XML já existe
        if(!fileXML.exists()){
            System.out.println("\nXML ainda não existe. Criando...");
            
            try
            {
                // Cria e adiciona a primeira linha ao XML (linha obrigatória)
                new File("finances.xml").createNewFile();

                BufferedWriter buffWrite = new BufferedWriter(new FileWriter(this.filepath));
                Scanner in = new Scanner(System.in);
                buffWrite.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<list>\n</list>");
                buffWrite.close();
            }
            catch(IOException e)
            {
                System.err.println("Error in create file");
            }
        }
        
        this.xml = this.readXML();  // Carrega o arquivo xml
    }
    
    /**
     * Este método adiciona uma finança ao mês corrente no XML.
     * 
     * @param financa   A finança que será adicionada
     * @return Retorna a ArrayList do mês atualizada
     */
    public ArrayList<Financa> addFinance(Financa financa)
    {
        //Pega a data atual
        Calendar date = financa.getDate();
        String month = this.intToMonth(date.get(Calendar.MONTH));
        int year = date.get(Calendar.YEAR);
        
        //Carrega e atualiza o vetor de financas do mês corrente
        ArrayList<Financa> array;
        array = this.loadMonth(month, year);
        array.add(financa);
        
        //Salva o mês atualizado no XML
        this.saveLastMonth(array);
        
        return array;
    }
    
    /**
     * Este método remove uma finança do mês corrente no XML.
     * 
     * @param i   indice da finança que será removida
     * @return Retorna a ArrayList do mês atualizada
     */
    public ArrayList<Financa> removeFinance(int i)
    {
        //Pega a data atual
        Calendar date = Calendar.getInstance();
        String month = this.intToMonth(date.get(Calendar.MONTH));
        int year = date.get(Calendar.YEAR);
        
        //Carrega e atualiza o vetor de financas do mês corrente, removendo o item
        ArrayList<Financa> array = this.loadMonth(month, year);
        array.remove(i);
        
        //Salva o mês atualizado no XML
        this.saveLastMonth(array);
        
        return array;
    }
    
    /**
     * Método para retornar a lista de Finanças de um mês de um
     * determinado ano
     * 
     * @param month Mês desejado
     * @param year Ano do mês desejado
     * @return Caso exista o mês e o ano: Array com as Finanças
     *         Caso não exista: ArrayList vazio
     */
    public ArrayList<Financa> loadMonth(String month, int year)
    {
        //Cria stream para o XML
        XStream stream = new XStream(new DomDriver());
        stream.alias("financas", List.class);
        stream.alias("receita", Receita.class);
        stream.alias("despesa", Despesa.class);
           
        //Modifica formato de escrita da data
        stream.registerConverter(new DateConverterTo());
        
        //Procura pela posição do mês e do ano especificado
        //e pega a substring que fica entre a tag dessa data 
        //e o fechamento dessa tag, retornando ela como XML String
        int x = xml.indexOf("<"+month+year+">");
        if(x==-1)
        {
            //Não encontrou a data
            ArrayList<Financa> retArray = new ArrayList<>();
            return retArray;
        }
        int y = xml.indexOf("</"+month+year+">");
        String financesXML = xml.substring(x+10, y);
        ArrayList<Financa> retArray = (ArrayList<Financa>) stream.fromXML(financesXML);
        
        return retArray;
    }
    
    /**
     * Método auxiliar para carregar o XML em uma String
     * 
     * @return String com o conteúdo do XML
     */
    private String readXML()
    {
        String allLines = "";
        try 
        { 
            FileReader arq = new FileReader(fileXML);
            BufferedReader lerArq = new BufferedReader(arq);
            String line = lerArq.readLine();  // lê a primeira linha
        
            // a variável "linha" recebe o valor "null" quando o processo 
            // de repetição atingir o final do arquivo texto
            while (line != null) 
            { 
                allLines = allLines.concat(line+"\n");
                line = lerArq.readLine();
            } 
            arq.close();
        }
        catch (IOException e) 
        { 
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); 
        }

        return allLines;
    }
    
    /**
     * Método auxiliar que transforma um inteiro em um mês do ano
     * 
     * 
     * @param m Mês (inteiro)
     * @return Mês em inglês, só as 3 primeiras letras em maiúsculo
     */
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
        else
        {
            try
            {
                Exception e = new Exception("Month don't exists");
                throw e;
            }
            catch (Exception e)
            {
                System.err.println(e);
            }
        }
        return ret;
    }
    
    /**
     * Salva o último mês de finanças no XML.
     * 
     * 
     * @param finances Vetor de finanças do mês corrente
     */
    public void saveLastMonth(ArrayList<Financa> finances)
    {
        // Cria o Xstream e gera apelidos para as tags do XML
        XStream stream = new XStream(new DomDriver());
        stream.alias("financas", List.class);
        stream.alias("receita", Receita.class);
        stream.alias("despesa", Despesa.class);
           
        //Modifica formato de escrita da data
        stream.registerConverter(new DateConverterTo());
        
        //Gera mês e ano atual para atualizar o XML
        Calendar date = Calendar.getInstance();
        String month;
        String year;
        month = this.intToMonth(date.get(Calendar.MONTH));
        year = String.valueOf(date.get(Calendar.YEAR));
        
        
        //Verifica se o mês e o ano já existem no XML
        //Caso exista: Atualiza o mês/ano
        //Caso não exista: Cria o mês/ano e já adiciona a finança
        int position = xml.indexOf(month+year);
        if (position == -1)
        {
            xml = xml.replaceFirst("<list>\n", "<list>\n<"+month+year+">\n"+
                   stream.toXML(finances)+"\n"+"</"+month+year+">\n" );
        }
        else
        {
            xml = xml.substring(0,xml.indexOf("<"+month+year+">")) 
                    +"<"+month+year+">" +"\n"
                    +stream.toXML(finances) +"\n"
                    +xml.substring(xml.indexOf("</"+month+year+">"),xml.length())
                    +"\n";        
        }
        try
        {
            //Atualiza o arquivo XML
            FileWriter fw = new FileWriter(fileXML);  
            BufferedWriter bw = new BufferedWriter(fw);  
            bw.write(xml);  
            bw.flush();  
            bw.close(); 
        }
        catch(Exception e)
        {
            System.err.println("CAUTION! Error in update XML");
        }
        
    }
    
    /**
     * Método para pegar o ArrrayList de Financas do mês corrente
     * 
     * @return Caso não exista ainda o mês corrente no XML: arrayListVazio
     *         Caso exista: Array com as Finanças
     */
    public ArrayList<Financa> loadCurrentMonth ()
    {
        Calendar date = Calendar.getInstance();
        return this.loadMonth(this.intToMonth(date.get(Calendar.MONTH)),
                (date.get(Calendar.YEAR)) );
    }
}
