package XMLHandler;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Essa classe serve para alterar o formato da data que será passada ao XML
 * Antes ela era armazenada em milissegundos a partir de 1/1/1970. Agora ela
 * está mais supimpa, bonita, elegante e charmosa.
 * 
 * @author Simão
 */
public class DateConverterTo implements Converter {

    private final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd");

    /**
     * Método que recebe a classe na qual a classe DateConverterTo irá
     * funcionar.
     *  
     * @param clazz Classe que será serializada
     */
    @Override
    public boolean canConvert(Class clazz) 
    {
        // Converte só a classe calendário
        return Calendar.class.isAssignableFrom(clazz);
    }

    /**
     * Método para serealizar o tipo Calendar
     * 
     * @param value Objeto da classe Calendar
     * @param writer Escritor do tipo HieranchicalStreamWriter
     * @param context Contexto
     */
    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) 
    {
        Calendar calendar = (Calendar) value;
        Date date = calendar.getTime();
        writer.setValue(formatter.format(date));
    }
    
    /**
     * Método para desserealizar o tipo Calendar
     * 
     * @param reader Leitor da classe HierarchicalStreamReader
     * @param context Contexto
     * @return  Objeto desserealizado
     */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) 
    {
        GregorianCalendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(formatter.parse(reader.getValue()));
        } catch (ParseException e) {
            throw new ConversionException(e.getMessage(), e);
        }
        return calendar;
    }

}
