package PROZ.PROZ.server;

import PROZ.PROZ.client.Calculation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder; 
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class MsgBodyReader implements MessageBodyReader<Calculation> {


    @Override
    public boolean isReadable(Class<?> type, Type genericType,
                              Annotation[] annotations, MediaType mediaType) {
        return type == Calculation.class;
    }

    @Override
    public Calculation readFrom(Class<Calculation> type, Type genericType, Annotation[] annotations,
                         MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
                         InputStream entityStream) throws IOException, WebApplicationException {
    	Gson gson = new GsonBuilder().create();
    	
    	InputStreamReader reader = new InputStreamReader(entityStream,"UTF-8"); 
        try {
        	return gson.fromJson(reader, type);
        }
        finally {
        	reader.close();
        
        }
    }
}