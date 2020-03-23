package PROZ.PROZ.server;

import PROZ.PROZ.client.Calculation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
  
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class MsgBodyWriter implements MessageBodyWriter<Calculation> {
   
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == Calculation.class;
    }
   
    @Override
    public long getSize(Calculation calc, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        return 0;
    }
   
    @Override
    public void writeTo(Calculation calc,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream)
                        throws IOException, WebApplicationException {
    	Gson gson = new GsonBuilder().create();
    	PrintWriter printWriter = new PrintWriter(entityStream); 
    	String json;
		try {
			json = gson.toJson(calc);  
			printWriter.write(json);
			printWriter.flush(); 
		} finally {
			printWriter.close(); 
		}
    }
}